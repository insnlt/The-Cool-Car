package test;

import model.SensorInterface;
import model.ActuatorInterface;
import controller.CarImplementation;
import controller.DetectException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

public class CarTest {
    @Mock
    private SensorInterface sensor1;
    @Mock
    private SensorInterface sensor2;
    @Mock
    private SensorInterface sensor3;
    @Mock
    private SensorInterface sensor4;
    @Mock
    private ActuatorInterface actuator;

    @InjectMocks
    private CarImplementation car;

    @Before
    public void setUp() {
        sensor1 = Mockito.mock(SensorInterface.class);
        sensor2 = Mockito.mock(SensorInterface.class);
        sensor3 = Mockito.mock(SensorInterface.class);
        sensor4 = Mockito.mock(SensorInterface.class);
        actuator = Mockito.mock(ActuatorInterface.class);
        car = new CarImplementation(sensor1,sensor2,sensor3,sensor4,actuator);
        car.getCar().setLane(1);
        MockitoAnnotations.initMocks(this);

    }

    @Test
    //TC0
    public void testMoveForwardInRange() {
        car.getCar().setPosition(0);
        car.getCar().move();
      //  actuator.moveForward().move();
        Assert.assertEquals(5, car.getCarPosition());
    }

    //TC1
    @Test
    public void testMoveForwardOutsideRange() {
        car.getCar().setPosition(96);
        car.moveForward();
        Assert.assertEquals(96, car.getCarPosition());
    }

    //TC2
    @Test(expected = DetectException.class)
    public void testNoSensorWorking() throws DetectException {
      //  int[] arr = {-1, -1, -1, -1};
        for(int i = 0 ; i < 2 ; i++){
            Mockito.when(sensor1.setSensorData(i)).thenReturn(-1);
            Mockito.when(sensor2.setSensorData(i)).thenReturn(-1);
            Mockito.when(sensor3.setSensorData(i)).thenReturn(-1);
            Mockito.when(sensor4.setSensorData(i)).thenReturn(-1);
        }
        //   car.leftLaneDetect(arr, arr);
        car.leftLaneDetect();
        Assert.fail("No sensor working");
    }

    //TC3
    @Test(expected = DetectException.class)
    public void testSensorWorkingOutsideRange() throws DetectException {
        //more than 5 meter range
       // int[] arr = {9, -1, -1, -1};
        for(int i = 0 ; i < 2 ; i++){
            Mockito.when(sensor1.setSensorData(i)).thenReturn(9);
            Mockito.when(sensor2.setSensorData(i)).thenReturn(-1);
            Mockito.when(sensor3.setSensorData(i)).thenReturn(-1);
            Mockito.when(sensor4.setSensorData(i)).thenReturn(-1);
        }
        //  car.leftLaneDetect(arr, arr);
        car.leftLaneDetect();
        Assert.fail("Only one of the sensors is working");
    }

    //TC4
    @Test
    public void testSensorWorkingInsideRange1() throws DetectException {

        for(int i = 0 ; i < 2 ; i++){
            Mockito.when(sensor1.setSensorData(i)).thenReturn(4);
            Mockito.when(sensor2.setSensorData(i)).thenReturn(-1);
            Mockito.when(sensor3.setSensorData(i)).thenReturn(-1);
            Mockito.when(sensor4.setSensorData(i)).thenReturn(-1);
        }
        Assert.assertEquals(true, car.leftLaneDetect());
    }

    //TC5
    @Test
    public void testSensorWorkingInsideRange2() throws DetectException {
       // int[] arr = {4, 3, 2, -1};
        for(int i = 0 ; i < 2 ; i++){
            Mockito.when(sensor1.setSensorData(i)).thenReturn(4);
            Mockito.when(sensor2.setSensorData(i)).thenReturn(3);
            Mockito.when(sensor3.setSensorData(i)).thenReturn(2);
            Mockito.when(sensor4.setSensorData(i)).thenReturn(-1);
        }
        Assert.assertEquals(true, car.leftLaneDetect());
    }

    //TC6
    @Test
    public void testTwoOrMoreSensorWorkingOutsideRange() throws DetectException {
   //     int[] arr = {14, 11, -1, -1};
        for(int i = 0 ; i < 2 ; i++){
            Mockito.when(sensor1.setSensorData(i)).thenReturn(14);
            Mockito.when(sensor2.setSensorData(i)).thenReturn(11);
            Mockito.when(sensor3.setSensorData(i)).thenReturn(-1);
            Mockito.when(sensor4.setSensorData(i)).thenReturn(-1);
        }
        Assert.assertEquals(false, car.leftLaneDetect());
    }

    //TC7
    @Test
    public void testQueriesMatchAsTrue() throws DetectException {
 //       int[] arr1 = {5, 14, -1, -1}; //for throwing exception
 //       int[] arr2 = {1, 3, 9, -1};
            Mockito.when(sensor1.setSensorData(0)).thenReturn(5);
            Mockito.when(sensor2.setSensorData(0)).thenReturn(14);
            Mockito.when(sensor3.setSensorData(0)).thenReturn(-1);
            Mockito.when(sensor4.setSensorData(0)).thenReturn(-1);
            Mockito.when(sensor1.setSensorData(1)).thenReturn(1);
            Mockito.when(sensor2.setSensorData(1)).thenReturn(3);
            Mockito.when(sensor3.setSensorData(1)).thenReturn(9);
            Mockito.when(sensor4.setSensorData(1)).thenReturn(-1);

        Assert.assertEquals(true, car.leftLaneDetect());
    }

    //TC8
    @Test
    public void testQueriesMatchAsFalse() throws DetectException {
    //    int[] arr1 = {14, 14, -1, -1}; //for throwing exception
     //   int[] arr2 = {12, 13, 19, -1};
        Mockito.when(sensor1.setSensorData(0)).thenReturn(14);
        Mockito.when(sensor2.setSensorData(0)).thenReturn(14);
        Mockito.when(sensor3.setSensorData(0)).thenReturn(-1);
        Mockito.when(sensor4.setSensorData(0)).thenReturn(-1);
        Mockito.when(sensor1.setSensorData(1)).thenReturn(12);
        Mockito.when(sensor2.setSensorData(1)).thenReturn(13);
        Mockito.when(sensor3.setSensorData(1)).thenReturn(19);
        Mockito.when(sensor4.setSensorData(1)).thenReturn(-1);
        Assert.assertEquals(false, car.leftLaneDetect());
    }

    //TC9
    @Test(expected = DetectException.class)
    public void testQueriesMatchAsException() throws DetectException {
     //   int[] arr1 = {14, 14, -1, -1}; //for throwing exception
      //  int[] arr2 = {1, 3, 9, -1};
        Mockito.when(sensor1.setSensorData(0)).thenReturn(14);
        Mockito.when(sensor2.setSensorData(0)).thenReturn(14);
        Mockito.when(sensor3.setSensorData(0)).thenReturn(-1);
        Mockito.when(sensor4.setSensorData(0)).thenReturn(-1);
        Mockito.when(sensor1.setSensorData(1)).thenReturn(1);
        Mockito.when(sensor2.setSensorData(1)).thenReturn(3);
        Mockito.when(sensor3.setSensorData(1)).thenReturn(9);
        Mockito.when(sensor4.setSensorData(1)).thenReturn(-1);
        car.leftLaneDetect();
        Assert.fail("Queries didn't match");
    }

    //TC10
    @Test
    public void testNotMostLeftOccupied() throws DetectException {
    //    int[] arr1 = {14, 5, -1, -1}; //for throwing exception
    //    int[] arr2 = {1, 4, -1, -1};
        Mockito.when(sensor1.setSensorData(0)).thenReturn(14);
        Mockito.when(sensor2.setSensorData(0)).thenReturn(5);
        Mockito.when(sensor3.setSensorData(0)).thenReturn(-1);
        Mockito.when(sensor4.setSensorData(0)).thenReturn(-1);
        Mockito.when(sensor1.setSensorData(1)).thenReturn(1);
        Mockito.when(sensor2.setSensorData(1)).thenReturn(4);
        Mockito.when(sensor3.setSensorData(1)).thenReturn(-1);
        Mockito.when(sensor4.setSensorData(1)).thenReturn(-1);
        car.getCar().setLane(2);
        Assert.assertEquals(2, car.getCar().getLane());

        Assert.assertEquals(0, car.changeLane());
    }

    //TC11
    @Test
    public void testNotMostLeftNotOccupiedNotEndOfStreet() throws DetectException {
     //   int[] arr1 = {14, 20, -1, -1}; //for throwing exception
     //   int[] arr2 = {10, 15, -1, -1};
        Mockito.when(sensor1.setSensorData(0)).thenReturn(14);
        Mockito.when(sensor2.setSensorData(0)).thenReturn(20);
        Mockito.when(sensor3.setSensorData(0)).thenReturn(-1);
        Mockito.when(sensor4.setSensorData(0)).thenReturn(-1);
        Mockito.when(sensor1.setSensorData(1)).thenReturn(10);
        Mockito.when(sensor2.setSensorData(1)).thenReturn(15);
        Mockito.when(sensor3.setSensorData(1)).thenReturn(-1);
        Mockito.when(sensor4.setSensorData(1)).thenReturn(-1);
        car.getCar().setLane(2);
        car.getCar().setPosition(50);
        Assert.assertTrue(car.getCar().getPosition() > 0 && car.getCar().getPosition() < 96);
       Assert.assertEquals(1, car.changeLane());
    }

    //TC12
    @Test
    public void testNotMostLeftNotOccupiedEndOfStreet() throws DetectException {
  //      int[] arr1 = {14, 20, -1, -1};
   //     int[] arr2 = {10, 15, -1, -1};
        Mockito.when(sensor1.setSensorData(0)).thenReturn(14);
        Mockito.when(sensor2.setSensorData(0)).thenReturn(20);
        Mockito.when(sensor3.setSensorData(0)).thenReturn(-1);
        Mockito.when(sensor4.setSensorData(0)).thenReturn(-1);
        Mockito.when(sensor1.setSensorData(1)).thenReturn(10);
        Mockito.when(sensor2.setSensorData(1)).thenReturn(15);
        Mockito.when(sensor3.setSensorData(1)).thenReturn(-1);
        Mockito.when(sensor4.setSensorData(1)).thenReturn(-1);
        car.getCar().setLane(2);
        car.getCar().setPosition(98);
        Assert.assertTrue(!(car.getCar().getPosition() > 0 && car.getCar().getPosition() < 96));
        Assert.assertEquals(3, car.changeLane());
    }

    //TC13
    @Test
    public void testMostLeftNotEndOfStreet() throws DetectException {
      //  int[] arr1 = {14, 20, -1, -1};
    //    int[] arr2 = {10, 15, -1, -1};
        Mockito.when(sensor1.setSensorData(0)).thenReturn(14);
        Mockito.when(sensor2.setSensorData(0)).thenReturn(20);
        Mockito.when(sensor3.setSensorData(0)).thenReturn(-1);
        Mockito.when(sensor4.setSensorData(0)).thenReturn(-1);
        Mockito.when(sensor1.setSensorData(1)).thenReturn(10);
        Mockito.when(sensor2.setSensorData(1)).thenReturn(15);
        Mockito.when(sensor3.setSensorData(1)).thenReturn(-1);
        Mockito.when(sensor4.setSensorData(1)).thenReturn(-1);
        car.getCar().setLane(3);
        car.getCar().setPosition(50);
        Assert.assertTrue(car.getCar().getPosition() > 0 && car.getCar().getPosition() < 96);
       Assert.assertEquals(2, car.changeLane());
    }

    //TC14
    @Test
    public void testMostLeftEndOfStreet() throws DetectException {
    //    int[] arr1 = {14, 20, -1, -1};
   //     int[] arr2 = {10, 15, -1, -1};
        Mockito.when(sensor1.setSensorData(0)).thenReturn(14);
        Mockito.when(sensor2.setSensorData(0)).thenReturn(20);
        Mockito.when(sensor3.setSensorData(0)).thenReturn(-1);
        Mockito.when(sensor4.setSensorData(0)).thenReturn(-1);
        Mockito.when(sensor1.setSensorData(1)).thenReturn(10);
        Mockito.when(sensor2.setSensorData(1)).thenReturn(15);
        Mockito.when(sensor3.setSensorData(1)).thenReturn(-1);
        Mockito.when(sensor4.setSensorData(1)).thenReturn(-1);
        car.getCar().setLane(3);
        car.getCar().setPosition(98);
        Assert.assertTrue(!(car.getCar().getPosition() > 0 && car.getCar().getPosition() < 96));
        Assert.assertEquals(3, car.changeLane());
    }

    @Test
    //TC15
    public void testOnStreet() {
        car.getCar().setPosition(50);
        car.getCar().setLane(2);
        int positionCarforw = car.whereIs()[1];
        int positionCarlan = car.whereIs()[0];
        Assert.assertTrue(positionCarforw > 0 && positionCarforw < 101);
        Assert.assertTrue(positionCarlan > 0 && positionCarlan < 4);
    }

    @Test
    //TC16
    public void testNotStreet() {
        car.getCar().setPosition(150);
        car.getCar().setLane(4);
        int positionCarforw = car.whereIs()[1];
        int positionCarlan = car.whereIs()[0];
        Assert.assertTrue(positionCarforw > 100 || positionCarforw < 0);
        Assert.assertTrue(positionCarlan > 3 || positionCarlan < 0);
    }

}

