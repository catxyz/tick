package me.cat.tick;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Tick {

    public static void main(String[] args) throws AWTException {
        Scanner input = new Scanner(System.in);
        out("specify the number of seconds to keep clicking ->");

        int time;
        try {
            time = input.nextInt();
        } catch (InputMismatchException ex) {
            System.err.println("'time' must be of 'int'");
            return;
        }

        new Timer().scheduleAtFixedRate(new TimerTask() {
            final Robot robot = new Robot();
            int timePassed = 0;
            final int endTime = time * 1000;

            @Override
            public void run() {
                timePassed += 1000;
                out("'timePassed' = %,d", timePassed);

                if (timePassed >= endTime) {
                    out("finished after %,d seconds!", time);
                    System.exit(0);
                }
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.delay(20);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            }
        }, 0L, 1000L);
    }

    public static void out(String message, Object... params) {
        System.out.printf(message + '\n', params);
    }
}
