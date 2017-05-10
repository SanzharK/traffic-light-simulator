package com.safetyculture;

/**
 * Created by skushekbayev on 10/05/2017.
 */
public class TrafficLightControlSystemSimulatorTest {
    public static void main(String [] args) {
        TrafficLight northLight = new TrafficLight("North Light", true);
        TrafficLight southLight = new TrafficLight("South Light", true);
        TrafficLight eastLight = new TrafficLight("East Light", false);
        TrafficLight westLight = new TrafficLight("West Light", false);

        Junction junction = new Junction("Boulevard Ave & Main Road crossroad", northLight, southLight, eastLight, westLight);

        TrafficLightControlSystemSimulator simulator = new TrafficLightControlSystemSimulator(10, 5, 30);
        simulator.attachJunction(junction);
        simulator.executeSimulation();

    }
}
