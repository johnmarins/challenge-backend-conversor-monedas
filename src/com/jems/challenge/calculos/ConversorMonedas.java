package com.jems.challenge.calculos;

import java.util.Scanner;

import static com.jems.challenge.utilidades.FormatearMoneda.formatearMoneda;

public class ConversorMonedas {

    public String getValorCambio(Double tasaCambio, Double valorACambiar) {
        return formatearMoneda(tasaCambio * valorACambiar);
    }

    public void cambiarMoneda(String monedaBase, String monedaCambio, Double tasaCambio){
        System.out.format("¿Cuál es el valor en %s que desea cambiar a %s?\n", monedaBase, monedaCambio);
        Scanner scValorACambiar = new Scanner(System.in);
        Double valorACambiar = scValorACambiar.nextDouble();
        String valor = getValorCambio(tasaCambio, valorACambiar);
        String fValorACambiar = formatearMoneda(valorACambiar);
        System.out.println("-------------------------------------------------");
        System.out.format("%s %s es igual a %s %s \n", fValorACambiar, monedaBase, valor, monedaCambio);
        System.out.format("El valor de cambio de $ 1 %s es %s %s\n", monedaBase, formatearMoneda(tasaCambio), monedaCambio);
        System.out.println("-------------------------------------------------");
    }
}
