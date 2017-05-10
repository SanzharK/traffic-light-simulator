package com.safetyculture;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by skushekbayev on 10/05/2017.
 */
public class TrafficLightControlSystemSimulator {

    /**
     * The TrafficLightControlSystemSimulator? class will be responsible for:
     * - maintaining count of all junctions that are controlled from it
     * - a junction will be able to have at least 2 registered traffic lights
     * - providing a method to attach/detach a junction to the system
     * - sending the main signal to change state at all junctions
     * - parse a configuration.properties file to get:
     *                                       - duration of simulation
     *                                       - length of cycle
     *                                       - length of yellow state duration
     */

    private List<Junction> junctions;
    private int simulationDuration;
    private int cycleDuration;
    private int yellowStateDuration;

    public TrafficLightControlSystemSimulator() {
        this.junctions = new ArrayList<>();
        loadProperties();
    }

    public TrafficLightControlSystemSimulator(int duration, int cycleDuration, int yellowStateDuration) {
        this.junctions = new ArrayList<>();
        this.simulationDuration = duration;
        this.cycleDuration = cycleDuration;
        this.yellowStateDuration = yellowStateDuration;
    }

    public void attachJunction(Junction junction) {
        this.junctions.add(junction);
    }

    public void dettachJunction(Junction junction) {
        this.junctions.remove(junction);
    }

    private void changeState(Calendar cal) {
        this.junctions.stream().forEach(junction -> junction.changeLights(cal));
    }

    /**
     * Check:
     *     - junctions is not empty
     *     - config was loaded
     *
     * Start simulation.
     *
     */
    public void executeSimulation() {
        if(this.junctions.isEmpty()) {
           System.out.println("Please add at least one junction");
            return;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        // add display format
        Date date = cal.getTime();
        System.out.println("Starting Traffic Light Control Simulation at : " + date);
        System.out.println("This simulation will show light state changes during a " + this.simulationDuration + " minute period.");
        System.out.println("Every cycle will change after : " + this.cycleDuration + " minutes.\n" +
                "The Yellow Light will be displayed for " + this.yellowStateDuration+ " seconds.\n");
        this.junctions.stream().forEach(junction -> System.out.println(junction.displayJunction()));
        while(this.simulationDuration > 0) {
            cal.add(Calendar.MINUTE, this.cycleDuration);
            this.simulationDuration -= this.cycleDuration;
            changeState(cal);
        }
    }


    private void loadProperties() {
        Properties properties = new Properties();
        InputStream inputStream = null;
        String propertiesFile = "config.properties";

        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFile);
            if(inputStream != null) {
                properties.load(inputStream);
                //being optimistic here and not checking the input of the config file.
                this.simulationDuration = Integer.parseInt(properties.getProperty("simulationDuration"));
                this.cycleDuration = Integer.parseInt(properties.getProperty("cycleDuration"));
                this.yellowStateDuration = Integer.parseInt(properties.getProperty("yellowStateDuration"));
            } else
                System.out.println(propertiesFile + " has not been found");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
