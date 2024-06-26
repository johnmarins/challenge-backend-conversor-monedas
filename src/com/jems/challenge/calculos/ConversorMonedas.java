package com.jems.challenge.calculos;

import java.util.HashMap;
import java.util.Scanner;

import static com.jems.challenge.utilidades.ConsultaMonedas.consultaMonedas;
import static com.jems.challenge.utilidades.FormatearMoneda.formatearMoneda;

public class ConversorMonedas {


    public String[] setUp() {

        String ayuda = """
                El conversor soporta cualquier moneda del mundo.
                Algunos ejemplos:
                
                Código : USD  País : USA          Divisa : DOLAR ESTADOUNIDENSE
                Código : GBP  País : REINO UNIDO  Divisa : LIBRA ESTERLINA
                Código : COP  País : COLOMBIA     Divisa : PESO COLOMBIANO
                Código : ARS  País : ARGENTINA    Divisa : PESO ARGENTINO
                Código : BRL  País : BRASIL       Divisa : REAL BRASILEÑO
                """;

        System.out.println("\n>>> Ingrese los códigos de las monedas que desea cambiar.");
        System.out.println(ayuda);
        Scanner sc = new Scanner(System.in);

        System.out.println("--> Primero ingrese la moneda de la que desea cambiar: ");

        String monedaBase;
        HashMap consultaDivisas = consultaMonedas();

        while (true) {
            monedaBase = sc.nextLine().toUpperCase();
            if (monedaBase.matches("[a-zA-Z]{3}") && consultaDivisas.containsKey(monedaBase)) {
                break;
            } else if (!monedaBase.matches("[a-zA-Z]{3}")) {
                System.out.print("El código de moneda debe tener 3 letras, intente nuevamente: ");
            } else {
                System.out.println("No es una moneda disponible para consultar, intente nuevamente: ");
            }
        }

        System.out.println("--> Ahora ingrese la segunda moneda (a la que desea cambiar): ");
        String monedaCambio;

        while (true) {
            monedaCambio = sc.nextLine().toUpperCase();
            if (monedaCambio.matches("[a-zA-Z]{3}") && consultaDivisas.containsKey(monedaCambio)) {
                break;
            } else if (!monedaCambio.matches("[a-zA-Z]{3}")) {
                System.out.print("El código de moneda debe tener 3 letras, intente nuevamente: ");
            } else {
                System.out.println("No es una moneda disponible para consultar, intente nuevamente: ");
            }
        }

        return new String[]{monedaBase, monedaCambio};
    }

    public String getValorCambio(Double tasaCambio, Double valorACambiar) {
        return formatearMoneda(tasaCambio * valorACambiar);
    }

    public void cambiarMoneda(String monedaBase, String monedaCambio, Double tasaCambio) {

        Scanner scValorACambiar = new Scanner(System.in);
        System.out.format("¿Cuál es el valor en %s que desea cambiar a %s?\n", monedaBase, monedaCambio);

        while (!scValorACambiar.hasNextDouble()) {
            System.out.println("El valor debe ser número. Intente nuevamente: ");
            scValorACambiar.next();
        }

        Double valorACambiar = scValorACambiar.nextDouble();

        String valor = getValorCambio(tasaCambio, valorACambiar);
        String fValorACambiar = formatearMoneda(valorACambiar);
        System.out.println("-------------------------------------------------");
        System.out.format("%s %s es igual a %s %s \n", fValorACambiar, monedaBase, valor, monedaCambio);
        System.out.format("El valor de cambio de $ 1 %s es %s %s\n", monedaBase, formatearMoneda(tasaCambio), monedaCambio);
        System.out.println("-------------------------------------------------");
    }
}
