package test;

import main.CarImplementation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.Car;


public class CarTest {
	private CarImplementation car;

    @Before
    public void setUp() {
        car = new CarImplementation();
    }

	@Test
    public void testMoveForwardInRange() {
        int newPosition = car.moveForward(0);
        Assert.assertEquals(5, newPosition);
    }
    @Test
    public void testMoveForwardOutsideRange(){
        int newPosition = car.moveForward(96);
        Assert.assertEquals(96, newPosition);
    }

}
