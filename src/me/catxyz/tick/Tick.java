package me.catxyz.tick;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Tick {

    public static void main(String[] args) throws AWTException {
        Scanner input = new Scanner(System.in);
        say("Specify how long I should keep clicking in seconds.");

        final int time = input.nextInt();

        Robot robot = new Robot();
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            int timePassed = 0;
            final int endTime = time * 1000;

            @Override
            public void run() {
                timePassed += 1000;

                if (timePassed >= endTime) {
                    say("Finished after " + time + " seconds.");
                    System.exit(0);
                }
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.delay(20);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            }
        };
        timer.scheduleAtFixedRate(task, 0L, 1000L);
    }

    public static void say(String message) {
        System.out.println(message);
    }

}
