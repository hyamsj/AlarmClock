package gui;

import java.util.Timer;
import java.util.TimerTask;

public class MainController {

    private MainModel model;

    Timer timer;
    TimerTask task;

    int secondsPassed = 0;
    int max = 5;

    public MainController(MainModel m) {
        model = m;
        createTimer();
        startTimer();
    }

    public void increment() {
        model.inc();
    }

    public void reset() {
        model.reset();

    }

    public void createTimer(){
        this.timer = new Timer();
        this.task = new TimerTask() {
            @Override
            public void run() {
                secondsPassed++;
                System.out.println("Seconds passed " + secondsPassed);
                if (secondsPassed == max){
                    timer.cancel();
                    int i = Integer.MAX_VALUE;
                    System.out.println(i);
                }
            }
        };
    }



    public void startTimer(){
        timer.scheduleAtFixedRate(task, 1000, 1000);

    }
}