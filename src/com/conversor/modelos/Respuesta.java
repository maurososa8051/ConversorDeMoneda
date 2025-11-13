package com.conversor.modelos;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Respuesta {
    public static String obtenerRespuesta(String url) throws IOException, InterruptedException {
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        return respuesta.body();
    }
}
