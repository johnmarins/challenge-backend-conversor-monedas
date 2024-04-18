package com.jems.challenge.utilidades;

import java.text.NumberFormat;

public interface FormatearMoneda {

    static String formatearMoneda(double valor) {
        NumberFormat formato = NumberFormat.getCurrencyInstance();
        String valorFormateado = formato.format(valor);
        return valorFormateado;
    }
}
