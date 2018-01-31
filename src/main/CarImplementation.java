package main;

public class CarImplementation implements CarInterface{

    @Override
    public int moveForward(int position) {
        if(position >= 96){
            return position;
        } else {
            return position + 5;
        }
    }

    @Override
    public boolean leftLaneDetect() {
        return false;
    }

    @Override
    public int changeLane() {
        return 0;
    }

    @Override
    public void whereIs() {

    }
}
