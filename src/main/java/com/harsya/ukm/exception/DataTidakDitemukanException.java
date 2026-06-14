package com.harsya.ukm.exception;

public class DataTidakDitemukanException extends Exception {
    public DataTidakDitemukanException(String message) {
        super(message);
    }

    public DataTidakDitemukanException(String type, Object key) {
        super(type + " dengan kunci '" + key + "' tidak ditemukan.");
    }
}
