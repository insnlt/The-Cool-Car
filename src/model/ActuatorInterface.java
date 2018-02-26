package model;

import controller.CarInterface;

public interface ActuatorInterface {
    /**
     Description: This class is used to move the car forward.
     Pre-condition: None.
     Post-condition: None.
     Test-cases: None.
     */
    public CarInterface.CarPosition moveForward();
}
