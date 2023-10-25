package gameNet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private QuestionGenerator questionGenerator;
    private int correctAnswersCount;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        this.questionGenerator = new QuestionGenerator();
        this.correctAnswersCount = 0;
    }

    @Override
    public void run() {
        try (
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            String playerName = in.readLine();
            System.out.println("Novo jogador: " + playerName);

            String question;
            int questionCount = 0;
            int maxQuestions = 10; // Número máximo de perguntas
            while ((question = questionGenerator.getQuestion()) != null && questionCount < maxQuestions) {
                out.println(question);
                String answer = in.readLine();
                boolean isCorrect = questionGenerator.checkAnswer(answer);
                if (isCorrect) {
                    out.println("Correto! Próxima pergunta.");
                    correctAnswersCount++;
                } else {
                    String correctAnswer = questionGenerator.getCorrectAnswer(); // Obter a resposta correta
                    out.println("Errado! A resposta correta é: " + correctAnswer);
                    out.println("Tente novamente.");
                }
                questionCount++;
            }

            out.println("Fim de jogo. Você acertou " + correctAnswersCount + " perguntas.");
            System.out.println("Jogador desconectado: " + playerName);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
