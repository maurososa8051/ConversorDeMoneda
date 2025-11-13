package com.conversor.modelos;
import java.util.ArrayList;
import java.util.List;

public class Historial {
    private static final List<String> registros = new ArrayList<>();

    public static void agregarRegistro(String registro) {
        registros.add(registro);
    }

    public static void mostrarHistorial() {
        if (registros.isEmpty()) {
            System.out.println("No hay conversiones registradas aún.");
        } else {
            System.out.println("\n=== HISTORIAL DE CONVERSIONES ===");
            for (String r : registros) {
                System.out.println(r);
            }
        }
    }
}
