package com.conversor.modelos;

import java.net.http.HttpClient;

public class Cliente {
    public static HttpClient crearCliente() {
        return HttpClient.newHttpClient();
    }
}
