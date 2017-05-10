# traffic-light-simulator

This is my attempt at the traffic light simulation. I have used a "what works first approach", trying to think ahead and see how this can be extended.
Introducing a Junction class, provides extensibility for the future, in order to include different types of junctions. Some might only have 2 or 3 Lights.
The Junction holds a queue of lights that need to be notified to change to Yellow 30 seconds before the full cycle completes.
The Traffic Light is responsible for changing it's own state, once it is notified by the Junction that it is time to change.
I believe this can be extracted out to a TrafficLightFLow class which will consist of different "steps". That way a Traffic Light could also implement logic for a right/left turn.

You can run the output by checking out this repo and running "java -jar traffic-light-simulator.jar" command from the out/artifacts/traffic_light_simulator_jar directory.
