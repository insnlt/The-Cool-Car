package test;

import main.CarImplementation;
import main.CarInterface;
import main.DetectException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;


public class CarTest {
	private CarImplementation car;

    @Before
    public void setUp() {
        car = new CarImplementation();
    }

	@Test
	//TC0
    public void testMoveForwardInRange() {
        car.getPosition().setPosition(0);
        car.moveForward();
        Assert.assertEquals(5,car.getCar());
    }
    //TC1
    @Test
    public void testMoveForwardOutsideRange(){
        car.getPosition().setPosition(96);
        car.moveForward();
        Assert.assertEquals(96,car.getCar());
    }
    //TC2
    @Test(expected = DetectException.class)
    public void testNoSensorWorking() throws DetectException{
        int [] arr = {-1,-1,-1,-1};
        car.leftLaneDetect(arr,arr);
        Assert.fail("No sensor working");
    }
    //TC3
    @Test(expected = DetectException.class)
    public void testSensorWorkingOutsideRange() throws DetectException{
        //more than 5 meter range
        int [] arr = {9,-1,-1,-1};
        car.leftLaneDetect(arr,arr);
        Assert.fail("Only one of the sensors is working");
    }
    //TC4
    @Test
    public void testSensorWorkingInsideRange1() throws DetectException{
        int [] arr = {4,-1,-1,-1};
        Assert.assertEquals(true, car.leftLaneDetect(arr,arr));
    }
    //TC5
    @Test
    public void testSensorWorkingInsideRange2() throws DetectException{
        int [] arr = {4,3,2,-1};
        Assert.assertEquals(true, car.leftLaneDetect(arr,arr));
    }
    //TC6
    @Test
    public void testTwoOrMoreSensorWorkingOutsideRange() throws DetectException{
        int [] arr = {14,11,-1,-1};
        Assert.assertEquals(false,car.leftLaneDetect(arr,arr));
    }

    //TC7
    @Test
    public void testQueriesMatchAsTrue() throws DetectException{
        int [] arr1 = {5,14,-1,-1}; //for throwing exception
        int [] arr2 = {1,3,9,-1};
        Assert.assertEquals(true,car.leftLaneDetect(arr1, arr2));
    }

    //TC8
    @Test
    public void testQueriesMatchAsFalse() throws DetectException{
        int [] arr1 = {14,14,-1,-1}; //for throwing exception
        int [] arr2 = {12,13,19,-1};
        Assert.assertEquals(false,car.leftLaneDetect(arr1, arr2));
    }

    //TC9
    @Test(expected = DetectException.class)
    public void testQueriesMatchAsException() throws DetectException {
        int [] arr1 = {14,14,-1,-1}; //for throwing exception
        int [] arr2 = {1,3,9,-1};
        car.leftLaneDetect(arr1,arr2);
        Assert.fail("Queries didn't match");
    }

    @Test
    public void testNotMostLeft(){
    	//int newPosition = car.changeLane();
    	//int newPosition= car.whereIs()[1];
    //	Assert.assertEquals(1, newPosition);
    }

    @Test
    public void testMostLeft(){
    	Assert.assertEquals(3, car.whereIs()[1]);
    }
    
    @Test
    //TC13
    public void testOnStreet(){
    	int positionCarforw = car.whereIs()[0]; 
    	int positionCarlan = car.whereIs()[1];
  //  	Assert.assertEquals(50,positionCarforw);
    //	Assert.assertEquals(2,positionCarlan);
    	Assert.assertTrue(positionCarforw > 0 && positionCarforw <101);
    	Assert.assertTrue(positionCarlan > 0 && positionCarlan < 4);
    }
    
    @Test
    //TC14
    public void testNotStreet(){
    	int positionCarforw = car.whereIs()[0];
    	int positionCarlan = car.whereIs()[1];
  //  	Assert.assertEquals(50,positionCarforw);
    //	Assert.assertEquals(2,positionCarlan);
    	Assert.assertTrue(positionCarforw > 100);
    	Assert.assertTrue(positionCarlan > 3);
    }

}
