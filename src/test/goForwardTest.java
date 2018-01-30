package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.goForward;


public class goForwardTest {
	private goForward forward;

    @Before
    public void setUp() {
        forward = new goForward();
    }

	@Test
	public void testForward() {
//        account = new AccountImpl("testId", "testOwner");
        int expectedSpeed = 5;
        int actualSpeed = forward.getSpeed();
        Assert.assertEquals(expectedSpeed, actualSpeed);
    }
}
