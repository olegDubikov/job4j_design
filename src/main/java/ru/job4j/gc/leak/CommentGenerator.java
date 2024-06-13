package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommentGenerator implements Generate {
    public final String pathPhrases = "src/main/java/ru/job4j/gc/leak/files/phrases.txt";

    public final String separator = System.lineSeparator();
    private final List<Comment> comments = new ArrayList<>();
    public final int count = 50;
    private List<String> phrases;
    private final UserGenerator userGenerator;
    private final Random random;

    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.userGenerator = userGenerator;
        this.random = random;
        read();
    }

    private void read() {
        try {
            phrases = read(pathPhrases);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void generate() {
        comments.clear();
        try {
            for (int i = 0; i < count; i++) {
                StringBuilder comment = new StringBuilder();
                comment.append(phrases.get(random.nextInt(phrases.size())));
                comment.append(separator);
                comment.append(phrases.get(random.nextInt(phrases.size())));
                comment.append(separator);
                comment.append(phrases.get(random.nextInt(phrases.size())));
                comments.add(new Comment(comment.toString(),
                        userGenerator.randomUser()));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}