package com.safetyculture.test;

import com.safetyculture.TrafficLightControlSystemSimulator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by skushekbayev on 10/05/2017.
 */
public class TrafficLightControlSystemSimulatorTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    TrafficLightControlSystemSimulator simulator;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        simulator = new TrafficLightControlSystemSimulator();
    }

    @After
    public void shutdown() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void testSimulationWithoutJunctionWontRun() {
        simulator.executeSimulation();
        Assert.assertEquals("Please add at least one junction\n", outContent.toString());
    }
}
