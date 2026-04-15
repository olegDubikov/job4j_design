package ru.job4j.algo.sliding.window;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static int[] findMaxOverlapInterval(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty()) {
            return new int[]{-1, -1};
        }

        List<Event> events = new ArrayList<>();
        for (Interval interval : intervals) {
            events.add(new Event(interval.start, 1, interval));
            events.add(new Event(interval.end, -1, interval));
        }

        events.sort((a, b) -> {
            if (a.time != b.time) {
                return Integer.compare(a.time, b.time);
            }
            return Integer.compare(a.type, b.type);
        });

        PriorityQueue<Interval> activeEnds = new PriorityQueue<>(Comparator.comparingInt(i -> i.end));
        int maxOverlap = 0;
        int maxStart = -1;
        int maxEnd = -1;

        for (Event event : events) {
            if (event.type == 1) {
                activeEnds.offer(event.interval);
                int currentCount = activeEnds.size();
                if (currentCount > maxOverlap) {
                    maxOverlap = currentCount;
                    maxStart = event.time;
                    maxEnd = activeEnds.peek().end;
                }
            } else {
                activeEnds.remove(event.interval);
            }
        }
        return new int[]{maxStart, maxEnd};
    }

    private record Event(int time, int type, Interval interval) {
    }

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(7, 8));
        int[] result = findMaxOverlapInterval(intervals);
        System.out.println("Interval that overlaps the maximum number of intervals: [" + result[0] + ", " + result[1] + "]");
    }
}