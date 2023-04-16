package ru.job4j.question;

import java.util.HashMap;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;

        HashMap<Integer, String> mapPrevious = new HashMap<>();
        for (var user : previous) {
            mapPrevious.put(user.getId(), user.getName());
        }

        for (var user : current) {
            if (!mapPrevious.containsValue(user.getName())
                    && mapPrevious.containsKey(user.getId())) {
                changed++;
            }
            if (mapPrevious.size() > current.size()) {
                deleted++;
                break;
            }
            if (mapPrevious.size() < current.size()) {
                added++;
                break;
            }
            if (!mapPrevious.containsKey(user.getId())
                    && mapPrevious.size() == current.size()) {
                added++;
                deleted++;
            }
        }
        return new Info(added, changed, deleted);
    }
}