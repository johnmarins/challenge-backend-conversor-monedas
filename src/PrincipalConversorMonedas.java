import com.jems.challenge.api.APIExchange;
import com.jems.challenge.api.TasaDeCambio;
import com.jems.challenge.calculos.ConversorMonedas;
import com.jems.challenge.calculos.Divisas;
import com.jems.challenge.utilidades.Sleep;

import java.util.Map;
import java.util.Scanner;

import static com.jems.challenge.utilidades.ConsultaMonedas.consultaMonedas;

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
            Divisas divisaBase = (Divisas) consultaMonedas().get(monedaBase);
            Divisas divisaCambio = (Divisas) consultaMonedas().get(monedaCambio);
            System.out.println("***************************************");
            System.out.format("""
                    Cambiar de %s - %s
                    a %s - %s""",
                    monedaBase, divisaBase.divisa(), monedaCambio, divisaCambio.divisa());

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
                            Puede ingresar los códigos de divisa que correspondan a cada país:
                            
                            "COP","AED","AFN","ALL","AMD","ANG","AOA","ARS","AUD","AWG","AZN","BAM","BBD","BDT",
                            "BGN","BHD","BIF","BMD","BND","BOB","BRL","BSD","BTN","BWP","BYN","BZD","CAD","CDF",
                            "CHF","CLP","CNY","CRC","CUP","CVE","CZK","DJF","DKK","DOP","DZD","EGP","ERN","ETB",
                            "EUR","FJD","FKP","FOK","GBP","GEL","GGP","GHS","GIP","GMD","GNF","GTQ","GYD","HKD",
                            "HNL","HRK","HTG","HUF","IDR","ILS","IMP","INR","IQD","IRR","ISK","JEP","JMD","JOD",
                            "JPY","KES","KGS","KHR","KID","KMF","KRW","KWD","KYD","KZT","LAK","LBP","LKR","LRD",
                            "LSL","LYD","MAD","MDL","MGA","MKD","MMK","MNT","MOP","MRU","MUR","MVR","MWK","MXN",
                            "MYR","MZN","NAD","NGN","NIO","NOK","NPR","NZD","OMR","PAB","PEN","PGK","PHP","PKR",
                            "PLN","PYG","QAR","RON","RSD","RUB","RWF","SAR","SBD","SCR","SDG","SEK","SGD","SHP",
                            "SLE","SLL","SOS","SRD","SSP","STN","SYP","SZL","THB","TJS","TMT","TND","TOP","TRY",
                            "TTD","TVD","TWD","TZS","UAH","UGX","USD","UYU","UZS","VES","VND","VUV","WST","XAF",
                            "XCD","XDR","XOF","XPF","YER","ZAR","ZMW","ZWL"
                            """);
                    Sleep.sleep();
                    break;
            }

        }
    }
}
