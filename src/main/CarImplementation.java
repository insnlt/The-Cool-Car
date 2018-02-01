package main;

public class CarImplementation implements CarInterface{
  //  private int position;
    private CarPosition carPosition;
    public CarImplementation() {
        carPosition = new CarPosition();
    }
    @Override
    public int moveForward() {
        if(this.carPosition.getPosition() >=96 || this.carPosition.getPosition() < 0){
            return this.carPosition.getPosition();
        } else {
            carPosition.move();
            return this.carPosition.getPosition();
        }
    }

    @Override
    public boolean leftLaneDetect(int sensorData){
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
<<<<<<< HEAD

    public CarPosition getPosition() {
        return this.carPosition;
    }

    public int getCarPosition(){
        return this.carPosition.streetPosition;
    }
=======
    
>>>>>>> 1bf64549cf879a9fa37320428f7a2b7d3b669daf
}
