package com.example.amal.esa.network;

import java.io.IOException;

public class NoConnectivityException extends IOException {
 
    @Override
    public String getMessage() {
        return "Network Connection exception";
    }
 
}