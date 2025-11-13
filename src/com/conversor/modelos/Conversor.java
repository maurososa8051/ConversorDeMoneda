package com.conversor.modelos;

import java.io.IOException;
import java.util.Scanner;

public class Conversor {

    public static void eleccionUserMenu() throws IOException, InterruptedException {
        Scanner entrada = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = entrada.nextInt();

            if (opcion == 6) {
                System.out.println("Gracias por usar el conversor. ¡Hasta pronto!");
                break;
            }

            if (opcion < 1 || opcion > 6) {
                System.out.println("Opción inválida. Intente nuevamente.");
                continue;
            }

            realizarConversion(opcion, entrada);

            System.out.print("\n¿Desea realizar otra conversión? (s/n): ");
            String continuar = entrada.next().toLowerCase();
            if (!continuar.equals("s")) {
                System.out.println("Gracias por usar el conversor. ¡Hasta pronto!");
                break;
            }

        } while (true);

        entrada.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=== com.conversor.modelos.Conversor de Moneda ===");
        System.out.println("1) USD → ARS");
        System.out.println("2) ARS → USD");
        System.out.println("3) USD → BRL");
        System.out.println("4) BRL → USD");
        System.out.println("5) USD → COP");
        System.out.println("6) Salir");
        System.out.print("Elija una opción: ");
    }

    private static void realizarConversion(int opcion, Scanner entrada) throws IOException, InterruptedException {
        System.out.print("Ingrese el monto: ");
        double monto = entrada.nextDouble();

        String base = "", destino = "";

        switch (opcion) {
            case 1 -> { base = "USD"; destino = "ARS"; }
            case 2 -> { base = "ARS"; destino = "USD"; }
            case 3 -> { base = "USD"; destino = "BRL"; }
            case 4 -> { base = "BRL"; destino = "USD"; }
            case 5 -> { base = "USD"; destino = "COP"; }
        }

        String url = "https://v6.exchangerate-api.com/v6/05da19c8336cb9766c375007/pair/" + base + "/" + destino;

        String json = Respuesta.obtenerRespuesta(url);
        double tasa = AnalizadorJson.extraerTasa(json);

        if (tasa == 0.0) {
            System.out.println("No se pudo obtener la tasa de cambio.");
            return;
        }

        double resultado = Calculadora.convertir(monto, tasa);
        System.out.printf("%.2f %s equivale a %.2f %s%n", monto, base, resultado, destino);
    }
}
