package ru.job4j.question;

import java.util.*;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;

        HashMap<Integer, String> mapPrevious = new HashMap<>();
        for (var user : previous) {
            mapPrevious.put(user.getId(), user.getName());
        }
        ArrayList<User> arrCurrent = new ArrayList<>(current);
        for (var user : arrCurrent) {
            if (mapPrevious.containsKey(user.getId())
                    && !mapPrevious.containsValue(user.getName())) {
                changed++;
            }
            if (mapPrevious.size() > arrCurrent.size()
                    && deleted < 1) {
                deleted++;
            }
            if (mapPrevious.size() < arrCurrent.size()
                    && added < 1) {
                added++;
            }
            if (mapPrevious.size() == arrCurrent.size()
                    && !mapPrevious.containsKey(user.getId())) {
                added++;
                deleted++;
            }
        }
        return new Info(added, changed, deleted);
    }
}