package main;

public class CarImplementation implements CarInterface{
  //  private int position;
    private CarPosition car;
    private QueryResult result;
    public CarImplementation() {
        car = new CarPosition();
    }
    @Override
    public int moveForward() {
        if(this.car.getPosition() >=96 || this.car.getPosition() < 0){
            return this.car.getPosition();
        } else {
            car.move();
            return this.car.getPosition();
        }
    }

    @Override
    public boolean leftLaneDetect(){
       QueryResult q1 = checkQuery();
       QueryResult q2 = checkQuery();
       int res1 = q1.getWorkingCounter();
       int res2 = q2.getWorkingCounter();
       boolean bool1 = q1.getQueryOutput();
       boolean bool2 = q2.getQueryOutput();
        // if both queries have atleast 1 sensor input in range(0-5)
       if(bool1==bool2 && bool1){
           return true;
       } else if(bool1 == bool2 && !bool1 && res1 >= 2 && res2 >=2) {    // if both queries have atleast 2 sensors input nothing in range(0-5)
           return false;
       } else {
           System.out.println("ERROR MESSAGE");
       }
       return false;
    }

    @Override
    public int changeLane() {
    	
        return 1;
    }

    @Override
    public int[] whereIs() {
    	int road = (int) Math.floor(Math.random()* 101);
    	int lane = (int) Math.floor(Math.random()* 3)+1;
    	int[] random = new int[2];
    	random[0] = road;
    	random[1] = lane;
		return random;
    }


    public CarPosition getPosition() {
        return this.car;
    }

    public int getCar() {
        return this.car.streetPosition;
    }

    public QueryResult getResult() {
        return this.result;
    }

    public QueryResult checkQuery(){
        result = new QueryResult();
        boolean check = false;
        int counter = 0;
        // checks the sensors
        for(int i = 0 ; i < this.car.sensorData.length ; i++){
            if(this.car.getSensorData()[i] >=0 && this.car.getSensorData()[i] <=50){
                counter++;
                if(this.car.getSensorData()[i]<=5){
                    check = true;
                }
            }
        }

        result.setQueryOutput(check);
        result.setWorkingCounter(counter);
        return result;
//        if(counter >=2 && !check){
//            return check;
//        } else if (counter >=1 && check){
//            return check;
//        }
//        return check;
    }


}
