package com.conversor.modelos;

import java.net.URI;
import java.net.http.HttpRequest;

public class Solicitud {
    public static HttpRequest crearSolicitud(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
    }
}
