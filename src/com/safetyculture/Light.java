package com.safetyculture;

/**
 * Created by skushekbayev on 10/05/2017.
 */
public enum Light {
    RED("Red"),
    GREEN("Green"),
    YELLOW("Yellow");

    private String light;

    Light(String light) {
        this.light = light;
    }
}
