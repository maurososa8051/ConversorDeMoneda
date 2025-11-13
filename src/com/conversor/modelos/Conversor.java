package com.conversor.modelos;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Conversor {

    public static void eleccionUserMenu() throws IOException, InterruptedException {
        Scanner entrada = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== CONVERSOR DE MONEDAS ===");
            System.out.println("1) USD → ARS");
            System.out.println("2) ARS → USD");
            System.out.println("3) USD → BRL");
            System.out.println("4) BRL → USD");
            System.out.println("5) USD → COP");
            System.out.println("6) USD → CLP");
            System.out.println("7) Ver historial ");
            System.out.println("8) Salir ");
            System.out.print("Seleccione una opción: ");

            int opcion = entrada.nextInt();

            if (opcion == 8) {
                System.out.println("Gracias por usar el conversor. ¡Hasta pronto!");
                continuar = false;
                break;
            }

            if (opcion == 7) {
                Historial.mostrarHistorial();
                continue;
            }

            System.out.print("Ingrese el monto a convertir: ");
            double monto = entrada.nextDouble();

            String base = "USD", destino = "ARS";

            switch (opcion) {
                case 1 -> { base = "USD"; destino = "ARS"; }
                case 2 -> { base = "ARS"; destino = "USD"; }
                case 3 -> { base = "USD"; destino = "BRL"; }
                case 4 -> { base = "BRL"; destino = "USD"; }
                case 5 -> { base = "USD"; destino = "COP"; }
                case 6 -> { base = "USD"; destino = "CLP"; }
                default -> {
                    System.out.println("Opción inválida.");
                    continue;
                }
            }

            String url = "https://v6.exchangerate-api.com/v6/05da19c8336cb9766c375007/pair/" + base + "/" + destino;
            String json = Respuesta.obtenerRespuesta(url);
            double tasa = AnalizadorJson.extraerTasa(json);
            double resultado = Calculadora.convertir(monto, tasa);

            System.out.printf(" %.2f %s equivale a %.2f %s%n", monto, base, resultado, destino);

            String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            String registro = String.format("[%s] %.2f %s → %.2f %s", fechaHora, monto, base, resultado, destino);
            Historial.agregarRegistro(registro);
        }
    }
}
