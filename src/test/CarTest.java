package test;

import main.CarImplementation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class CarTest {
	private CarImplementation car;

    @Before
    public void setUp() {
        car = new CarImplementation();
    }

	@Test
	//TC0
    public void testMoveForwardInRange() {
        int newPosition = car.moveForward(0);
        Assert.assertEquals(5, newPosition);
    }
    @Test
    //TC1
    public void testMoveForwardOutsideRange(){
        int newPosition = car.moveForward(96);
        Assert.assertEquals(96, newPosition);
    }
    
    @Test
    public void testNotMostLeft(){
    	//int newPosition = car.changeLane();
    	int newPosition= car.whereIs()[1];
    	Assert.assertEquals(1, newPosition);
    }
    
    @Test
    public void testMostLeft(){
    	int position= car.whereIs()[1];
    	Assert.assertEquals(3, position);
    }
    
    @Test
    public void testOnStreet(){
    	int positionCarforw = car.whereIs()[0]; 
    	int positionCarlan = car.whereIs()[1];
  //  	Assert.assertEquals(50,positionCarforw);
    //	Assert.assertEquals(2,positionCarlan);
    	Assert.assertTrue(positionCarforw > 0 && positionCarforw <101);
    	Assert.assertTrue(positionCarlan > 0 && positionCarlan < 4);
    }
    
    @Test
    public void testNotStreet(){
    	int positionCarforw = car.whereIs()[0]; 
    	int positionCarlan = car.whereIs()[1];
  //  	Assert.assertEquals(50,positionCarforw);
    //	Assert.assertEquals(2,positionCarlan);
    	Assert.assertTrue(positionCarforw > 100);
    	Assert.assertTrue(positionCarlan > 3);
    }

}
