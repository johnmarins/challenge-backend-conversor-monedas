import com.jems.challenge.api.APIExchange;
import com.jems.challenge.api.TasaDeCambio;
import com.jems.challenge.calculos.ConversorMonedas;
import com.jems.challenge.utilidades.Sleep;

import java.util.Map;
import java.util.Scanner;

public class PrincipalConversorMonedas {
    public static void main(String[] args) {

        int opcion = 0;

        String opciones = """
                ***************************************
                Seleccione una opción:
                [1] Calcular otro valor
                [2] Seleccionar otras monedas
                [9] Salir
                [0] Ayuda
                ***************************************
                """;

        ConversorMonedas cm = new ConversorMonedas();
        APIExchange consulta = new APIExchange();

        System.out.println("***************************************");
        System.out.println("Bienvenid@ al Conversor de Monedas");
        System.out.println("***************************************");

        String[] monedas = cm.setUp();
        String monedaBase = monedas[0];
        String monedaCambio = monedas[1];

        TasaDeCambio tc = consulta.buscaTasaCambio(monedaBase);
        Map<String, Double> mapTasaDeCambio = (Map<String, Double>) tc.conversion_rates();
        Double tasaCambio = mapTasaDeCambio.get(monedaCambio);

        cm.cambiarMoneda(monedaBase, monedaCambio, tasaCambio);


        while (opcion != 9){
            System.out.println("***************************************");
            System.out.format("Cambiar de %s a %s", monedaBase, monedaCambio);
            System.out.println("\n" + opciones);

            Scanner opt = new Scanner(System.in);
            opcion = opt.nextInt();

            if (opcion > 2 && opcion < 9) {
                System.out.println("Esta opción no se encuentra disponible");
                Sleep.sleep();
            }

            switch (opcion){
                case 1:
                    cm.cambiarMoneda(monedaBase, monedaCambio, tasaCambio);
                    Sleep.sleep();
                    break;

                case 2:
                    monedas = cm.setUp();
                    monedaBase = monedas[0];
                    monedaCambio = monedas[1];
                    tc = consulta.buscaTasaCambio(monedaBase);
                    mapTasaDeCambio = (Map<String, Double>) tc.conversion_rates();
                    tasaCambio = mapTasaDeCambio.get(monedaCambio);
                    cm.cambiarMoneda(monedaBase, monedaCambio, tasaCambio);
                    Sleep.sleep();
                    break;

                case 9:
                    System.out.println("Gracias por utilizar nuestra aplicación");
                    Sleep.sleep();
                    break;

                case 0:
                    System.out.println("""
                            Moneda base: Es la moneda que desea cambiar a otra.
                            Moneda cambio: Es la moneda a la que desea convertir el valor.
                            Puede ingresar en la aplicación los códigos de divisa que correspondan a cada país.
                            """);
                    Sleep.sleep();
                    break;
            }

        }
    }
}
