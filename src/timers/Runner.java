package timers;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Joni on 23.02.2017.
 */
public class Runner {

    int secondsPassed = 0;
    int max = 5;
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            secondsPassed++;
            System.out.println("Seconds passed " + secondsPassed);
            //timer.cancel();
            if (secondsPassed == max){
                timer.cancel();
            }
        }
    };

    public void start(){
        timer.scheduleAtFixedRate(task, 1000, 1000);

    }

    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.start();

    }
}
