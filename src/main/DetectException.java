package main;

/**
 *  This class handles exceptions and prints out the related message.
 */
public class DetectException extends Exception {
    public DetectException(String message){
        super(message);
    }
}
