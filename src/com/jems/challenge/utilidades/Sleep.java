package com.jems.challenge.utilidades;

public interface Sleep {
    public static void sleep() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
