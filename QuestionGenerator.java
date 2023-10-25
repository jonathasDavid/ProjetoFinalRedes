package gameNet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionGenerator {
    private List<String> questions;
    private List<String> answers;
    private List<Integer> usedIndexes;
    private Random random;

    public QuestionGenerator() {
        questions = new ArrayList<>();
        answers = new ArrayList<>();
        usedIndexes = new ArrayList<>();
        random = new Random();
        // Adicione suas perguntas e respostas ao jogo aqui
        questions.add("Em que filme, um jovem se apaixona por uma mulher mais velha que é sua vizinha e inicia um caso proibido?\n");
        answers.add("O Graduado");
        questions.add("Qual é o filme que explora a história de um romance entre um músico e uma imigrante que compartilham uma paixão pela música?\n");
        answers.add("Once - Apenas uma Vez");
        questions.add("Em que filme, um escritor contrata uma prostituta para acompanhá-lo em eventos sociais e acaba se apaixonando por ela?\n");
        answers.add("Uma Linda Mulher");
        questions.add("Qual é o filme que narra a história de um jovem casal que se apaixona em meio à guerra e à ocupação nazista?\n");
        answers.add("Casablanca");
        questions.add("Em que filme, um músico de jazz e uma atriz se apaixonam em Los Angeles, enquanto perseguem seus sonhos na cidade?\n");
        answers.add("La La Land");
        questions.add("Qual filme explora um romance que se desenvolve ao longo de várias décadas e épocas diferentes, destacando a imortalidade do amor?\n");
        answers.add("Te Amarei para Sempre");
        questions.add("Em que filme, um casal se apaixona durante um cruzeiro, mas o romance enfrenta desafios quando eles retornam à vida real?\n");
        answers.add("Titanic");
        questions.add("Qual é o filme que apresenta uma história de amor entre um inventor excêntrico e uma artista, com a Torre Eiffel como pano de fundo?\n");
        answers.add("Hugo");
        questions.add("Em que filme, um jovem casal enfrenta um dilema quando o rapaz é diagnosticado com câncer terminal, desafiando seu amor?\n");
        answers.add("Um Amor para Recordar");
        questions.add("Qual filme segue a jornada de um músico famoso que se apaixona por uma mulher que não pode ouvir sua música devido à surdez?\n");
        answers.add("Amor Sublime Amor");

    }
    public String getCorrectAnswer() {
        if (usedIndexes.isEmpty() || usedIndexes.size() > answers.size()) {
            return "N/A"; // Alguma condição de erro, pois não deveríamos ter mais respostas do que perguntas.
        }
        int currentIndex = usedIndexes.get(usedIndexes.size() - 1);
        return answers.get(currentIndex);
    }


    public String getQuestion() {
        if (usedIndexes.size() == questions.size()) {
            return null; // Todas as perguntas foram usadas
        }

        int randomIndex;
        do {
            randomIndex = random.nextInt(questions.size());
        } while (usedIndexes.contains(randomIndex));

        usedIndexes.add(randomIndex);
        return questions.get(randomIndex);
    }

    public boolean checkAnswer(String answer) {
        if (usedIndexes.isEmpty() || usedIndexes.size() > answers.size()) {
            return false; // Alguma condição de erro, pois não deveríamos ter mais respostas do que perguntas.
        }
        int currentIndex = usedIndexes.get(usedIndexes.size() - 1);
        return answers.get(currentIndex).equalsIgnoreCase(answer.trim());
    }
}


