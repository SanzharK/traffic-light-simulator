package com.safetyculture;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by skushekbayev on 10/05/2017.
 */
public class TrafficLight {

    /**
     * The Traffic Light will have an internal Flow. The Flow can later be represented by an Object and passed into the traffic light.
     * It would be useful to introduce different flows ie. right/left turn traffic lights.
     */

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String GREEN = ANSI_GREEN + "GREEN" + ANSI_RESET;
    public static final String RED = ANSI_RED + "RED" + ANSI_RESET;
    public static final String YELLOW = ANSI_YELLOW + "YELLOW" + ANSI_RESET;

    private String name;
    private Light currentState;

    public TrafficLight(String name, boolean isRed) {
        if(isRed) {
            this.currentState = Light.RED;
        } else
            this.currentState = Light.GREEN;

        this.name = name;
    }

    public void changeLight(Date time) {
        if (this.currentState == Light.GREEN) {
            //type out names in actual colour?
            System.out.println(time + " - " + this.name + " " + GREEN + "=========>" +  YELLOW);
            this.currentState = Light.YELLOW;
        } else if(this.currentState == Light.RED) {
            System.out.println(time + " - " + this.name + " " + RED + "=========>" +  GREEN);
            this.currentState = Light.GREEN;
        } else if(this.currentState == Light.YELLOW) {
            System.out.println(time + " - " + this.name + " " + YELLOW + "=========>" +  RED);
            this.currentState = Light.RED;
        }
    }

    public String getName() {
        return name;
    }

    public Light getCurrentState() {
        return currentState;
    }
}
