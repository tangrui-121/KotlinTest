package com.person.kotlintest.rxandroid;

import java.util.LinkedList;
import java.util.List;

/**
 * @anthor tr
 * @date 2021/5/12
 * @desc
 */
public class StreamTimer implements Runnable {

    private List<Task> tasks = new LinkedList<>();

    public StreamTimer() {

    }

    public StreamTimer next(Runnable task) {
        return next(task, 0);
    }

    public StreamTimer next(Runnable task, long delay) {
        tasks.add(new Task(task, delay));
        return this;
    }

    public void start() {
        startNextTimer();
    }

    private void startNextTimer() {
        if (tasks.size() == 0) return;
        Task task = tasks.get(0);
        Timer.get().postDelayed(this, task.delay);
    }

    @Override
    public void run() {
        Task task = tasks.remove(0);
        task.runnable.run();
        startNextTimer();
    }

    private class Task {
        Runnable runnable;
        long delay;

        Task(Runnable runnable, long delay) {
            this.runnable = runnable;
            this.delay = delay;
        }
    }
}