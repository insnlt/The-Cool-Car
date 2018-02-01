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
        return 0;
    }

    @Override
    public void whereIs() {

    }

    public CarPosition getPosition() {
        return this.carPosition;
    }

    public int getCarPosition(){
        return this.carPosition.streetPosition;
    }
}
