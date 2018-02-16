package controller;

import model.SensorInterface;
import model.ActuatorInterface;

public class CarImplementation implements CarInterface {
    private CarPosition car;
    private QueryResult result;
    private SensorInterface sensor1;
    private SensorInterface sensor2;
    private SensorInterface sensor3;
    private SensorInterface sensor4;
    private ActuatorInterface actuator;
    public CarImplementation(SensorInterface sensor1,SensorInterface sensor2,SensorInterface sensor3,SensorInterface sensor4 , ActuatorInterface actuator) {
        this.sensor1 = sensor1;
        this.sensor2 = sensor2;
        this.sensor3 = sensor3;
        this.sensor4 = sensor4;
        this.actuator = actuator;
        car = new CarPosition();
    }

    @Override
    public int moveForward() {
        if(this.car.getPosition() >=96 || this.car.getPosition() < 0){  // TC1  checks if the car is out of range
            return this.car.getPosition();
        } else {
            //car.move();         // TC0      moves the car when its within range
            actuator.moveForward();
            return this.car.getPosition();
        }
    }

    @Override
    public boolean leftLaneDetect() throws DetectException{
       QueryResult q1 = checkQuery();
       QueryResult q2 = checkQuery();
       int res1 = q1.getWorkingCounter();
       int res2 = q2.getWorkingCounter();
       boolean bool1 = q1.getQueryOutput();
       boolean bool2 = q2.getQueryOutput();


       if(bool1==bool2 && bool1){               //TC4 , TC5 and TC7
           return true;         // if both queries have at least 1 sensor input in range(0-5)
       } else if(bool1 == bool2 && !bool1 && res1 >= 2 && res2 >=2) {    // TC6 and TC8
           return false;        //if both queries have at least 2 sensors input nothing in range(0-5)
       } else if (res1 == 0 && res2 == 0) {     // TC2
           throw new DetectException("No sensor working");  // Sensors working counter is 0 ie. no sensor is working
       } else if(res1 < 2 || res2 < 2){         // TC3
           throw new DetectException("Only one of the sensors is working"); // Sensor working is less than 2 ie. only one of the sensors are working
       } else {                                 // TC9
           throw new DetectException("Queries didn't match");
       }
    }

    @Override
    public int changeLane() {
    	boolean detect;
		try {
			detect = leftLaneDetect();
	    	if(whereIs()[0] != 3 && !detect && whereIs()[1] < 96 && whereIs()[1] > 0){         //TC11
	    		moveForward();              // we check if the sensors doesn't detect anything and the car is not on the end of the street and the car is not in the leftmost lane
	    		car.increaseLane();         // move forward and change the lane.
	    		return 1;
	    	} else if(whereIs()[0] !=3 && detect){          //TC10
	    		moveForward();              // we check if the car is not in the leftmost lane but it detects something , we just move forward and dont change the lane.
	    		return 0;
	    	} else if (whereIs()[0] == 3 && whereIs()[1] < 96 && whereIs()[1] > 0){     //TC13
	    		moveForward();              // we check if the car is in the leftmost lane and not on the end of the street , just move forward.
	    		return 2;
	    	}
	    	return 3;           //TC12 and TC14
	    
		} catch (DetectException e) {
			e.printStackTrace();
		}
		return 3;
    }

    @Override
    public int[] whereIs() {            //TC15 and TC16
    	int street = car.getPosition();
    	int lane =car.getLane();
    	int[] position = {lane,street};     // initialize the position of the car , contains where the car is on the street and the lane.
		return position;
    }

    public CarPosition getCar() {
        return this.car;
    }

    public int getCarPosition() {
        return this.car.streetPosition;
    }
    /*
        This method checks the query and returns the result contains information about the no. of sensor working and the output of those sensors.
     */
    public QueryResult checkQuery(){
        int [] sensorData = new int[4];
        sensorData[0] = sensor1.getSensorData();
        sensorData[1] =sensor2.getSensorData();
        sensorData[2] =sensor3.getSensorData();
        sensorData[3] =sensor4.getSensorData();

        result = new QueryResult();
        boolean check = false;
        int counter = 0;

        for(int i = 0; i<sensorData.length;i++){
            if(sensorData[i] >= 0 && sensorData[i]<=50){
                counter++;                              // checks if the sensor is working within range 0-50
                if(sensorData[i]<=5){
                    check=true;                         // checks if the sensor detects something within the range of 0-5
                }
            }
        }
        result.setQueryOutput(check);
        result.setWorkingCounter(counter);
        return result;

    }


}
