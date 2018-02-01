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
    public void testMoveForwardInRange() {
        car.getPosition().setPosition(0);
        car.moveForward();
        Assert.assertEquals(5,car.getCarPosition());
    }
    @Test
    public void testMoveForwardOutsideRange(){
        car.getPosition().setPosition(96);
        car.moveForward();
        Assert.assertEquals(96,car.getCarPosition());
    }

    @Test
    public void testNoSensorWorking(){

    }

}
