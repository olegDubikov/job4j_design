package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> phrases = readPhrases();
        String question;
        String answer;
        List<String> log = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean run = true;
        boolean talk = true;
        System.out.println("<Chat>");
        while (run) {
            question = scanner.nextLine();
            log.add(question);
            switch (question.toLowerCase()) {
                case OUT -> {
                    run = false;
                }
                case STOP -> {
                    talk = false;
                }
                case CONTINUE -> {
                    talk = true;
                }
                default -> {
                    if (talk) {
                        answer = phrases.get(random.nextInt(phrases.size()));
                        System.out.println(answer);
                        log.add(answer);
                    }
                }
            }

        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/chat/talk.txt", "./data/chat/answers.txt");
        cc.run();
    }
}