package ru.job4j.gc.leak;

import java.util.Random;
import java.util.Scanner;

public class Menu {
    public final int addPost = 1;
    public final int addManyPost = 2;
    public final int showAllPosts = 3;
    public final int deletePost = 4;

    public final String select = "Выберите меню";
    public final String count = "Выберите количество создаваемых постов";
    public final String textOfPost = "Введите текст";
    public final String exit = "Конец работы";
    private final String idForDelete = "Все посты удалены";

    public final String menu = """
                Введите 1 для создание поста.
                Введите 2, чтобы создать определенное количество постов.
                Введите 3, чтобы показать все посты.
                Введите 4, чтобы удалить все посты.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) {
        Menu menu = new Menu();
        Random random = new Random();
        UserGenerator userGenerator = new UserGenerator(random);
        CommentGenerator commentGenerator = new CommentGenerator(random, userGenerator);
        Scanner scanner = new Scanner(System.in);
        PostStore postStore = new PostStore();
        menu.start(commentGenerator, scanner, userGenerator, postStore);
    }

    private void start(CommentGenerator commentGenerator, Scanner scanner, UserGenerator userGenerator, PostStore postStore) {
        boolean run = true;
        while (run) {
            System.out.println(menu);
            System.out.println(select);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (addPost == userChoice) {
                System.out.println(textOfPost);
                String text = scanner.nextLine();
                userGenerator.generate();
                commentGenerator.generate();
                postStore.add(new Post(text, commentGenerator.getComments()));
            } else if (addManyPost == userChoice) {
                System.out.println(textOfPost);
                String text = scanner.nextLine();
                System.out.println(count);
                String count = scanner.nextLine();
                int countInt = Integer.parseInt(count);
                for (int i = 0; i < countInt; i++) {
                    createPost(commentGenerator, userGenerator, postStore, text);
                }
            } else if (showAllPosts == userChoice) {
                System.out.println(postStore.getPosts());
            } else if (deletePost == userChoice) {
                System.out.println(idForDelete);
                postStore.removeAll();
            } else {
                run = false;
                System.out.println(exit);
            }
        }
        scanner.close();
    }

    private void createPost(CommentGenerator commentGenerator,
                                   UserGenerator userGenerator,
                                   PostStore postStore, String text) {
        userGenerator.generate();
        commentGenerator.generate();
        postStore.add(new Post(text, commentGenerator.getComments()));
    }
}