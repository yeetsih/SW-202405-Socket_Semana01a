package com.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final int PORT = 13;

    public Server() {
          Socket clientSocket = null;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("1 > Server started on port " + PORT);
            while (true) {
                System.out.println("2 > A la espera de un cliente");
                clientSocket = serverSocket.accept();
                System.out.println("3 > New client connected: " + clientSocket.getInetAddress().getHostAddress());

                //flujos de comunicación
                BufferedReader entrada = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter salida = new PrintWriter(clientSocket.getOutputStream(), true);

                //recibe el mensaje del cliente
                String mensaje = entrada.readLine();
                System.out.println("Cliente: " + mensaje);

                //generar precio del boleto
                String precio = "";
                switch (mensaje) {
                    case "PLATEA": precio = "S/. 50.00";      break;
                    case "VIP":   precio = "S/. 150.00";      break;   
                    case "PLATINIUM":  precio = "S/. 250.00";  break;
                }
                //enviar precio del boleto al cliente
                salida.println(precio);
               
                System.out.println("4 > Finaliza atencion de cliente");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}