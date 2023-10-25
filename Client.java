package gameNet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "26.48.28.14"; // Endereço do servidor
        int serverPort = 12345; // Porta do servidor

        try (Socket socket = new Socket(serverAddress, serverPort);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado ao servidor. Por favor, insira seu nome:");
            String playerName = userInput.readLine();
            out.println(playerName);

            String response;
            while ((response = in.readLine()) != null) {
                System.out.println(response);
                if (response.contains("Fim de jogo")) {
                    break;
                }
                String answer = userInput.readLine();
                out.println(answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
