package com.conversor.modelos;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AnalizadorJson {
    public static double extraerTasa(String json) {
        JsonObject objeto = JsonParser.parseString(json).getAsJsonObject();

        if (objeto.has("result") && objeto.get("result").getAsString().equals("error")) {
            System.out.println("Error desde la API: " + objeto.get("error-type").getAsString());
            return 0.0;
        }

        return objeto.get("conversion_rate").getAsDouble();
    }
}
