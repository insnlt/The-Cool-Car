package model;

import controller.CarInterface;

public interface ActuatorInterface {
    /**
     Description: This class holds the results of the queries.
     Pre-condition: Car is on the street and not in the leftmost lane.
     Post-condition: None.
     Test-cases: None.
     */
    public CarInterface.CarPosition moveForward();
}
