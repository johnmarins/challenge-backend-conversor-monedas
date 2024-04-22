package com.jems.challenge.utilidades;

import com.google.gson.Gson;
import com.jems.challenge.calculos.Divisas;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public interface ConsultaMonedas {

    static HashMap consultaMonedas() {
        Map<String, Divisas> divisasMap = null;

        try {
            FileReader fileReader = new FileReader("divisas.json");
            Gson gson = new Gson();
            Divisas[] divisas = gson.fromJson(fileReader, Divisas[].class);

            divisasMap = new HashMap<>();
            for (Divisas objeto : divisas) {
                divisasMap.put(objeto.codigo(), objeto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (HashMap) divisasMap;
    }
}