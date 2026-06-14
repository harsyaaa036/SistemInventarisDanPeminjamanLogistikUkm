package com.harsya.ukm.exception;

public class StokTidakCukupException extends Exception {
    public StokTidakCukupException(String namaBarang, int stokTersedia, int diminta) {
        super("Stok '" + namaBarang + "' tidak mencukupi! Tersedia: " + stokTersedia + ", diminta: " + diminta);
    }
}
