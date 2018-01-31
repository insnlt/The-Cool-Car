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
    
}
