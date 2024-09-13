package com.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class Client {

    private final int PORT = 13;
    private final String HOST = "localhost";

    public Client() {
        try {
            Socket socket = new Socket(HOST, PORT);

            //flujos de comunicación
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            //se genera un mensaje aleatorio
            String[] tipoCliente = { "PLATEA", "VIP", "PLATINIUM" };
            Random random = new Random();
            int index = random.nextInt(3);
            String mensaje = tipoCliente[index];
            System.out.println("Cliente: " + mensaje);

            //enviar mensaje al servidor
            salida.println(mensaje);

            //recibe el precio del boleto del servidor
            String precio = entrada.readLine();

            System.out.println("Precio del boleto: " + precio);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}
