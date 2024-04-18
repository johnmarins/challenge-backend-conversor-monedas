import com.jems.challenge.api.APIExchange;
import com.jems.challenge.api.TasaDeCambio;
import com.jems.challenge.calculos.ConversorMonedas;
import static com.jems.challenge.utilidades.FormatearMoneda.formatearMoneda;
import com.jems.challenge.utilidades.Sleep;

import java.util.Map;
import java.util.Scanner;

public class PrincipalConversorMonedas {
    public static void main(String[] args) {

        int opcion = 0;

        String opciones = """
                ***************************************
                Seleccione una opción:
                [1] Ingresar valor
                [2] Seleccionar otras monedas
                [9] Salir
                [0] Ayuda
                ***************************************
                """;

        String ayuda = """
                            Por ejemplo: ARS | BRL | COP | MXN | USD | ...otras
                            Puede convertir cualquier moneda del mundo.
                            """;

        System.out.println("***************************************");
        System.out.println("Bienvenid@ al Conversor de Monedas");
        System.out.println("***************************************");
        System.out.println("\n>>> Por favor ingrese los códigos de las monedas que desea cambiar.");
        System.out.println(ayuda);
        Scanner sc = new Scanner(System.in);
        System.out.println("--> Primero ingrese la moneda para tomar como base: ");
        String cambiarDe = sc.nextLine().toUpperCase();
        System.out.println("--> Ahora ingrese la segunda moneda (a la que desea cambiar): ");
        String cambiarA = sc.nextLine().toUpperCase();

        ConversorMonedas cm = new ConversorMonedas();
        APIExchange consulta = new APIExchange();
        TasaDeCambio tc = consulta.buscaTasaCambio(cambiarDe);
        Map<String, Double> mapTasaDeCambio = (Map<String, Double>) tc.conversion_rates();
        Double tasaCambio = mapTasaDeCambio.get(cambiarA);

        while (opcion != 9){
            System.out.println("***************************************");
            System.out.format("Cambiar de %s a %s", cambiarDe, cambiarA);
            System.out.println("\n" + opciones);

            opcion = sc.nextInt();

            if (opcion > 2 && opcion < 9) {
                System.out.println("Esta opción no se encuentra disponible");
                Sleep.sleep();
            }

            switch (opcion){
                case 1:
                    System.out.format("¿Cuál es el valor en %s que desea cambiar a %s?\n", cambiarDe, cambiarA);
                    Scanner scValorACambiar = new Scanner(System.in);
                    Double valorACambiar = scValorACambiar.nextDouble();
                    String valor = cm.getValorCambio(tasaCambio, valorACambiar);
                    String fValorACambiar = formatearMoneda(valorACambiar);
                    System.out.format("%s %s es igual a %s %s \n", fValorACambiar, cambiarDe, valor, cambiarA);
                    System.out.format("El valor de cambio de $ 1 %s es %s %s\n", cambiarDe, formatearMoneda(tasaCambio), cambiarA);
                    Sleep.sleep();
                    break;

                case 2:
                    System.out.println("--> Primero ingrese la moneda para tomar como base: ");
                    Scanner scNuevaMoneda = new Scanner(System.in);
                    cambiarDe = scNuevaMoneda.nextLine().toUpperCase();
                    System.out.println("--> Ahora ingrese la segunda moneda (a la que desea cambiar): ");
                    cambiarA = scNuevaMoneda.nextLine().toUpperCase();
                    Sleep.sleep();
                    break;

                case 9:
                    System.out.println("Gracias por utilizar nuestra aplicación");
                    Sleep.sleep();
                    break;

                case 0:
                    System.out.println("""
                            Un texto va acá........
                            """);
                    Sleep.sleep();
                    break;
            }

        }
    }
}
