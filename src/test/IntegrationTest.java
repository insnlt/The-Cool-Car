package test;

import controller.CarImplementation;
import model.SensorInterface;
import model.ActuatorInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.io.*;

public class IntegrationTest{
    @Mock
    private ActuatorInterface actuator;
    @Mock
    private SensorInterface sensor1;
    @Mock
    private SensorInterface sensor2;
    @Mock
    private SensorInterface sensor3;
    @Mock
    private SensorInterface sensor4;

    @InjectMocks
    private CarImplementation car;

    private int startOfRoad = 0;
    private int endOfRoad = 100;

    @Before
    public void setUp(){
        sensor1 = Mockito.mock(SensorInterface.class);
        sensor2 = Mockito.mock(SensorInterface.class);
        sensor3 = Mockito.mock(SensorInterface.class);
        sensor4 = Mockito.mock(SensorInterface.class);
        actuator = Mockito.mock(ActuatorInterface.class);
        car = new CarImplementation(sensor1,sensor2,sensor3,sensor4,actuator);
        car.
    }

}