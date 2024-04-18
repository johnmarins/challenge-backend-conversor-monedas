package com.jems.challenge.calculos;

import static com.jems.challenge.utilidades.FormatearMoneda.formatearMoneda;

public class ConversorMonedas {

    public String getValorCambio(Double tasaCambio, Double valorACambiar) {
        return formatearMoneda(tasaCambio * valorACambiar);
    }
}
