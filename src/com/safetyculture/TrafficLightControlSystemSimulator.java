package com.safetyculture;

import com.sun.tools.javac.tree.JCTree;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
     * - parse a config.properties file to get:
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

    private boolean readConfig() { return false; }

    /**
     * Check:
     *     - junctions is not empty
     *     - config was loaded
     *
     * Start simulation.
     *
     */
    public void executeSimulation() {
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



}
