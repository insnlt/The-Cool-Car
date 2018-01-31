package main;

public interface CarInterface {
    /**
     Description: This method moves the car by 5 metres for each call until the end of the street is reached.
     Pre-condition: Car is on the street and range is between 0-95 metres.
     Post-condition: The car has moved 5 metres ahead.
     Test-cases: NONE :P
     */
    int moveForward(int position);
    /**
     Description: This method detects an empty left lane using sensor data.
     Pre-condition: At least 2 out of 4 sensors should input an integer value from the range between 0-50 metres.
     Post-condition: The method indicates whether the lane is empty or not. In other cases an error message is returned.
     Test-cases: AGAIN NONE :P
     */
    boolean leftLaneDetect();
    /**
     Description: This method moves the car to the left lane and 5 metres forward if the method leftLaneDetect indicates that the lane is empty,
     otherwise the car moves 5 metres forward.
     Pre-condition: The car should not be in the left most lane.
     Post-condition: If the left lane is empty, the car switches the lane and returns a success code otherwise an error code is returned.
     Test-cases: shpx u!
     */
    int changeLane();
    /**
     Description: The method returns the latitudinal and longitudinal position of the car.
     Pre-condition: The car is on the street.
     Post-condition: Returns current position of the car.
     Test-cases:
     */
    void whereIs(); // change the return type
}
