package test;

import main.CarImplementation;
import main.CarInterface;
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
    @Ignore
    @Test
    public void testNoSensorWorking(){
        int [] arr = {-1,-1,-1,-1};
        car.getPosition().setSensorData(arr);
        //Assert.assertEquals(false,car.leftLaneDetect());
    }
    //TC3
    @Ignore
    @Test
    public void testSensorWorkingOutsideRange(){
        //more than 5 meter range
        int [] arr = {9,-1,-1,-1};
        car.getPosition().setSensorData(arr);
        //Assert.assertEquals(false,car.leftLaneDetect());
    }
    //TC4
    @Ignore
    @Test
    public void testSensorWorkingInsideRange1(){
        int [] arr = {4,-1,-1,-1};
        car.getPosition().setSensorData(arr);
        //Assert.assertEquals(true,car.leftLaneDetect());
    }
    //TC5
    @Ignore
    @Test
    public void testSensorWorkingInsideRange2(){
        int [] arr = {4,3,2,-1};
        car.getPosition().setSensorData(arr);
        //Assert.assertEquals(true, car.leftLaneDetect());
    }
    //TC6
    @Ignore
    @Test
    public void testTwoOrMoreSensorWorkingOutsideRange(){
        int [] arr = {14,11,-1,-1};
        car.getPosition().setSensorData(arr);
        //Assert.assertEquals(false,car.leftLaneDetect());
    }

    //TC7
    @Test
    public void testQueriesMatchAsTrue(){
        int [] arr1 = {5,14,-1,-1}; //for throwing exception
        int [] arr2 = {1,3,9,-1};
        //car.getPosition().setSensorData(arr1);
        //car.getPosition().setSensorData(arr2);
        //car.getResult().

        Assert.assertEquals(true,car.leftLaneDetect(arr1, arr2));
    }

    //TC8
    @Test
    public void testQueriesMatchAsFalse(){
        int [] arr1 = {14,14,-1,-1}; //for throwing exception
        int [] arr2 = {12,13,19,-1};
        //car.getPosition().setSensorData(arr1);
        //car.getPosition().setSensorData(arr2);
        //car.getResult().

        Assert.assertEquals(false,car.leftLaneDetect(arr1, arr2));
    }

    //TC9
    @Test
    public void testQueriesMatchAsException(){
        int [] arr1 = {14,14,-1,-1}; //for throwing exception
        int [] arr2 = {1,3,9,-1};
        //car.getPosition().setSensorData(arr1);
        //car.getPosition().setSensorData(arr2);
        //car.getResult().
        try{
            car.leftLaneDetect(arr1, arr2);
            Assert.fail("IllegalStateException expected");
        }
        catch (java.lang.IllegalStateException e){
            //expected
        }
        //Assert.assertEquals(true,car.leftLaneDetect(arr1, arr2));
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
