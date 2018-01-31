package main;

public class Car {

    public int moveForward(int position) {
        if(position >= 96){
            return position;
        } else {
            return position + 5;
        }
    }
}
