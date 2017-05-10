package com.safetyculture;

/**
 * Created by skushekbayev on 10/05/2017.
 */
public class Main {

    public static void main(String[] args) {
        TrafficLightControlSystemSimulator simulator = new TrafficLightControlSystemSimulator();

        TrafficLight north = new TrafficLight("North Light", true);
        TrafficLight south = new TrafficLight("South Light", true);
        TrafficLight east = new TrafficLight("East Light", false);
        TrafficLight west = new TrafficLight("West Light", false);

        Junction junction = new Junction("Shibuya Crossing", north, south, east, west);
        simulator.attachJunction(junction);

        simulator.executeSimulation();
    }
}
