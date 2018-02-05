package main;

public interface CarInterface {

    class CarPosition{
        public int streetPosition;
        public int [] sensorData = new int[4];
        public int lane;

        public int getPosition(){
            return this.streetPosition;
        }
        public void setPosition(int streetPosition){
            this.streetPosition = streetPosition;
        }
        public void move(){
            this.streetPosition = this.streetPosition + 5 ;
        }
        public void setSensorData(int data[]){
            for(int i = 0 ; i < sensorData.length ; i++){
                sensorData[i] = data[i];
            }
        }
        public int[] getSensorData(){
            return this.sensorData;
        }
        public void setLane(int lane){
        	this.lane = lane;;
        }
        public int getLane() {
        	return this.lane;
        }
        public void increaseLane(){
        	this.lane++;
        }
    }

    class QueryResult{
        public int workingCounter;
        public boolean queryOutput;

        public int getWorkingCounter(){
            return this.workingCounter;
        }

        public boolean getQueryOutput(){
            return this.queryOutput;
        }

        public void setQueryOutput(boolean queryOutput) {
            this.queryOutput = queryOutput;
        }

        public void setWorkingCounter(int workingCounter) {
            this.workingCounter = workingCounter;
        }
    }

    /**
     Description: This method moves the car by 5 metres for each call until the end of the street is reached.
     Pre-condition: Car is on the street and range is between 0-95 metres.
     Post-condition: The car has moved 5 metres ahead.
     Test-cases: TC0,TC1
     */
    int moveForward();
    /**
     Description: This method detects an empty left lane using sensor data.
     Pre-condition: At least 2 out of 4 sensors should input an integer value from the range between 0-50 metres.
     Post-condition: The method indicates whether the lane is empty or not. In other cases an error message is returned.
     Test-cases: TC2,TC3,TC4,TC5,TC6,TC7,TC8,TC9
     */
    boolean leftLaneDetect(int[] arr1, int[] arr2);
    /**
     Description: This method moves the car to the left lane and 5 metres forward if the method leftLaneDetect indicates that the lane is empty,
     otherwise the car moves 5 metres forward.
     Pre-condition: The car should not be in the left most lane.
     Post-condition: If the left lane is empty, the car switches the lane and returns a success code otherwise an error code is returned.
     Test-cases: TC10,TC11,TC12,TC13
     */
    int changeLane(int[] arr1, int[] arr2);
    /**
     Description: The method returns the latitudinal and longitudinal position of the car.
     Pre-condition: The car is on the street.
     Post-condition: Returns current position of the car.
     Test-cases: TC14,TC15
     */
    int[] whereIs(); // change the return type
}
