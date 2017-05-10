package com.safetyculture;

import java.util.*;

/**
 * Created by skushekbayev on 10/05/2017.
 */
public class Junction {

    /**
     * A junction must have 4 registered traffic lights at the moment.
     * The idea a is to have multiple constructors, that can take in different amounts of traffic lights.
     * That way we could also have a subtype of a junction ie crossroad, T-junction and so on.
     * It will pass on the signal to change state
     *
     */

    private List<TrafficLight> trafficLights;
    private String name;
    private Queue<TrafficLight> yellowQueue;

    public Junction(String name, TrafficLight northLight, TrafficLight southLight, TrafficLight eastLight, TrafficLight westLight) {
        this.name = name;
        this.trafficLights = new ArrayList<>();
        this.trafficLights.add(northLight);
        this.trafficLights.add(southLight);
        this.trafficLights.add(eastLight);
        this.trafficLights.add(westLight);
        this.yellowQueue = new LinkedList<>();
        enqueueTrafficLights();
    }

    private void enqueueTrafficLights() {
        this.trafficLights.stream().forEach(trafficLight -> {
            if (trafficLight.getCurrentState() == Light.GREEN) {
                this.yellowQueue.add(trafficLight);
            }
        });
    }

    public String displayJunction() {
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        builder.append("\n");
        for(TrafficLight trafficLight : trafficLights) {
            builder.append("Registered Traffic Light: ");
            builder.append(trafficLight.getName());
            builder.append(" starting it's sequence at ");
            builder.append(trafficLight.getCurrentState());
            builder.append("\n");
        }

        return builder.toString();
    }

    public void changeLights(Calendar cal) {
        // get seconds from config file
        cal.add(Calendar.SECOND, -30);
        while(!yellowQueue.isEmpty()) {
            yellowQueue.remove().changeLight(cal.getTime());
        }
        cal.add(Calendar.SECOND, 30);
        trafficLights.stream().forEach(trafficLight -> trafficLight.changeLight(cal.getTime()));
        enqueueTrafficLights();
    }
}
