package gui;

import java.util.Timer;
import java.util.TimerTask;

public class MainController {

    private MainModel model;

    private Timer timer;
    private TimerTask task;

    private int secondsPassed = 0;
    private int max = 3;

    public MainController(MainModel m) {
        model = m;
        createTimer();
        startTimer();
    }

    public void increment() {
        model.inc();
    }


    public void createTimer() {
        this.timer = new Timer();
        this.task = new TimerTask() {
            @Override
            public void run() {
                secondsPassed++;
                System.out.println("Seconds passed " + secondsPassed);
                if (secondsPassed == max) {
                    timer.cancel();
                    System.out.println("done");
                }
            }
        };
    }

    public void startTimer() {
        timer.scheduleAtFixedRate(task, 1000, 1000);

    }


}