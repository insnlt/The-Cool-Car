package test;

import main.CarImplementation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

<<<<<<< HEAD

=======
>>>>>>> 1bf64549cf879a9fa37320428f7a2b7d3b669daf

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
        Assert.assertEquals(5,car.getCarPosition());
    }
    @Test
    //TC1
    public void testMoveForwardOutsideRange(){
        car.getPosition().setPosition(96);
        car.moveForward();
        Assert.assertEquals(96,car.getCarPosition());
    }

    @Test
    public void testNoSensorWorking(){

    }
    
    @Test
    public void testNotMostLeft(){
    	//int newPosition = car.changeLane();
    	int newPosition= car.whereIs()[1];
    	Assert.assertEquals(1, newPosition);
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
