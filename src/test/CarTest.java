package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.Car;


public class CarTest {
	private Car forward;

    @Before
    public void setUp() {
        forward = new Car();
    }

	@Test
	public void testForward() {
//        account = new AccountImpl("testId", "testOwner");
        int expectedSpeed = 5;
        int actualSpeed = forward.getSpeed();
        Assert.assertEquals(expectedSpeed, actualSpeed);
    }
}
