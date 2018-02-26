package test;

import controller.CarImplementation;
import model.SensorInterface;
import model.ActuatorInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

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

    private CarImplementation randomCar1;
    private CarImplementation randomCar2;
    private CarImplementation randomCar3;

    private CarImplementation randomCar4;
    private CarImplementation randomCar5;
    private CarImplementation randomCar6;

    @Before
    public void setUp(){
        sensor1 = Mockito.mock(SensorInterface.class);
        sensor2 = Mockito.mock(SensorInterface.class);
        sensor3 = Mockito.mock(SensorInterface.class);
        sensor4 = Mockito.mock(SensorInterface.class);
        actuator = Mockito.mock(ActuatorInterface.class);
        car = new CarImplementation(sensor1,sensor2,sensor3,sensor4,actuator);
        randomCar1 = new CarImplementation();
        randomCar2 = new CarImplementation();
        randomCar3 = new CarImplementation();
        randomCar4 = new CarImplementation();
        randomCar5 = new CarImplementation();
        randomCar6 = new CarImplementation();
        car.getCar().setLane(1);
        MockitoAnnotations.initMocks(this);
    }

    private void moveCars(int steps) {
        for(int i = 0 ; i < steps ; i++){
            randomCar1.moveForwardRandomCar();
            randomCar2.moveForwardRandomCar();
            randomCar3.moveForwardRandomCar();
            randomCar4.moveForwardRandomCar();
            randomCar5.moveForwardRandomCar();
            randomCar6.moveForwardRandomCar();

            Mockito.when(actuator.moveForward()).thenReturn(car.getCar());
            actuator.moveForward().move();
        }
    }

    // There are 3 random cars in the middle lane and our car is on the rightmost lane. We move our car a few times and issue ChangeLane,
    // it checks the sensor data and if the lane is empty, it changes lane and moves to the end of the street.
    @Test
    public void IntegrationTestScenario1(){

        randomCar1.getRandomCar().setPosition(20);
        randomCar2.getRandomCar().setPosition(40);
        randomCar3.getRandomCar().setPosition(60);

        moveCars(10);

        Assert.assertEquals(50, car.getCarPosition());

        int diffBtwCars = randomCar1.getRandomCarPosition() - car.getCarPosition();
        for(int i = 0 ; i < 2 ; i++){
            Mockito.when(sensor1.setSensorData(i)).thenReturn(diffBtwCars);
            Mockito.when(sensor2.setSensorData(i)).thenReturn(diffBtwCars + 2);
            Mockito.when(sensor3.setSensorData(i)).thenReturn(diffBtwCars + 2);
            Mockito.when(sensor4.setSensorData(i)).thenReturn(diffBtwCars + 4);

        }
        car.changeLane();
        Assert.assertEquals(2,car.getCar().getLane());

        for(int i=0; i<9 ; i++){
            Mockito.when(actuator.moveForward()).thenReturn(car.getCar());
            actuator.moveForward().move();
//            System.out.println(car.getCarPosition());
        }
        Assert.assertEquals(100, car.getCarPosition());
    }
    @Test
    public void IntegrationTestScenario2(){
        randomCar1.getRandomCar().setPosition(5);
        randomCar2.getRandomCar().setPosition(10);
        randomCar3.getRandomCar().setPosition(20);

        moveCars(10);

        Assert.assertEquals(50, car.getCarPosition());
        int diffBtwCars = randomCar1.getRandomCarPosition() - car.getCarPosition();
        for(int i = 0 ; i < 2 ; i++) {
            Mockito.when(sensor1.setSensorData(i)).thenReturn(diffBtwCars + i);
            Mockito.when(sensor2.setSensorData(i)).thenReturn(diffBtwCars + 2 + i);
            Mockito.when(sensor3.setSensorData(i)).thenReturn(diffBtwCars + 2 + i);
            Mockito.when(sensor4.setSensorData(i)).thenReturn(diffBtwCars + 4 + i);
        }
        car.changeLane();
        Assert.assertEquals(1,car.getCar().getLane());

        for(int i=0; i < 10 ; i++){
            Mockito.when(actuator.moveForward()).thenReturn(car.getCar());
            actuator.moveForward().move();
        }
        Assert.assertEquals(100, car.getCarPosition());

    }

    @Test
    public void IntegrationTestScenario3(){
        randomCar1.getRandomCar().setPosition(5);
        randomCar2.getRandomCar().setPosition(10);
        randomCar3.getRandomCar().setPosition(30);

        moveCars(10);

        Assert.assertEquals(50, car.getCarPosition());
        int diffBtwCars = randomCar1.getRandomCarPosition() - car.getCarPosition();
        for(int i = 0 ; i < 2 ; i++) {
            Mockito.when(sensor1.setSensorData(i)).thenReturn(diffBtwCars);
            Mockito.when(sensor2.setSensorData(i)).thenReturn(diffBtwCars + 2);
            Mockito.when(sensor3.setSensorData(i)).thenReturn(diffBtwCars + 2);
            Mockito.when(sensor4.setSensorData(i)).thenReturn(diffBtwCars + 4);
        }
       // System.out.println(car.getCarPosition());
        car.changeLane();
       // System.out.println(car.getCarPosition());
        Assert.assertEquals(1,car.getCar().getLane());

        for(int i=0; i < 9 ; i++){
            Mockito.when(actuator.moveForward()).thenReturn(car.getCar());
            actuator.moveForward().move();
          //  System.out.println(car.getCarPosition());
        }
        Assert.assertEquals(100, car.getCarPosition());

    }

    @Test
    public void IntegrationTestScenario4(){
        randomCar1.getRandomCar().setPosition(10);
        randomCar2.getRandomCar().setPosition(15);
        randomCar3.getRandomCar().setPosition(25);

        moveCars(10);

        Assert.assertEquals(50, car.getCarPosition());
        int diffBtwCars = randomCar1.getRandomCarPosition() - car.getCarPosition();
        for(int i = 0 ; i < 2 ; i++) {
            if(i == 0) {
                Mockito.when(sensor1.setSensorData(i)).thenReturn(diffBtwCars);
                Mockito.when(sensor2.setSensorData(i)).thenReturn(diffBtwCars + 2);
                Mockito.when(sensor3.setSensorData(i)).thenReturn(diffBtwCars + 2);
                Mockito.when(sensor4.setSensorData(i)).thenReturn(diffBtwCars + 4);
                System.out.println(sensor1.setSensorData(i));
                System.out.println(sensor2.setSensorData(i));
                System.out.println(sensor3.setSensorData(i));
                System.out.println(sensor4.setSensorData(i));
            } else {
                Mockito.when(sensor1.setSensorData(i)).thenReturn(diffBtwCars * -1);
                Mockito.when(sensor2.setSensorData(i)).thenReturn(diffBtwCars * -15);
                Mockito.when(sensor3.setSensorData(i)).thenReturn(diffBtwCars + 2);
                Mockito.when(sensor4.setSensorData(i)).thenReturn(diffBtwCars + 4);
                System.out.println(sensor1.setSensorData(i));
                System.out.println(sensor2.setSensorData(i));
                System.out.println(sensor3.setSensorData(i));
                System.out.println(sensor4.setSensorData(i));
            }
        }
        car.changeLane();
        Assert.assertEquals(2,car.getCar().getLane());
        for(int i=0; i < 9 ; i++){
            Mockito.when(actuator.moveForward()).thenReturn(car.getCar());
            actuator.moveForward().move();
            //  System.out.println(car.getCarPosition());
        }
        Assert.assertEquals(100, car.getCarPosition());
    }

    @Test
    public void IntegrationTestScenario5(){
        randomCar1.getRandomCar().setPosition(5);
        randomCar2.getRandomCar().setPosition(15);
        randomCar3.getRandomCar().setPosition(30);

        moveCars(10);

        Assert.assertEquals(50, car.getCarPosition());
        int diffBtwCars = randomCar1.getRandomCarPosition() - car.getCarPosition();
        for(int i = 0 ; i < 2 ; i++) {
            if(i == 0) {
                Mockito.when(sensor1.setSensorData(i)).thenReturn(diffBtwCars);
                Mockito.when(sensor2.setSensorData(i)).thenReturn(diffBtwCars + 2);
                Mockito.when(sensor3.setSensorData(i)).thenReturn(diffBtwCars + 2);
                Mockito.when(sensor4.setSensorData(i)).thenReturn(diffBtwCars + 4);
                System.out.println(sensor1.setSensorData(i));
                System.out.println(sensor2.setSensorData(i));
                System.out.println(sensor3.setSensorData(i));
                System.out.println(sensor4.setSensorData(i));
            } else {
                Mockito.when(sensor1.setSensorData(i)).thenReturn(diffBtwCars);
                Mockito.when(sensor2.setSensorData(i)).thenReturn(diffBtwCars * -15);
                Mockito.when(sensor3.setSensorData(i)).thenReturn(diffBtwCars * -2);
                Mockito.when(sensor4.setSensorData(i)).thenReturn(diffBtwCars * -4);
                System.out.println(sensor1.setSensorData(i));
                System.out.println(sensor2.setSensorData(i));
                System.out.println(sensor3.setSensorData(i));
                System.out.println(sensor4.setSensorData(i));
            }
        }
        car.changeLane();
        Assert.assertEquals(1,car.getCar().getLane());
        for(int i=0; i < 9 ; i++){
            Mockito.when(actuator.moveForward()).thenReturn(car.getCar());
            actuator.moveForward().move();
            //  System.out.println(car.getCarPosition());
        }
        Assert.assertEquals(100, car.getCarPosition());
    }

    @Test
    public void IntegrationScenario6(){
        randomCar1.getRandomCar().setPosition(5);
        randomCar2.getRandomCar().setPosition(10);
        randomCar3.getRandomCar().setPosition(30);

        moveCars(10);

        Assert.assertEquals(50, car.getCarPosition());
        int diffBtwCars = randomCar1.getRandomCarPosition() - car.getCarPosition();
        for(int i = 0 ; i < 2 ; i++) {
            Mockito.when(sensor1.setSensorData(i)).thenReturn(diffBtwCars * -1);
            Mockito.when(sensor2.setSensorData(i)).thenReturn(diffBtwCars * - 7);
            Mockito.when(sensor3.setSensorData(i)).thenReturn(diffBtwCars * 200);
            Mockito.when(sensor4.setSensorData(i)).thenReturn(diffBtwCars * -17);
        }
        car.changeLane();
        Assert.assertEquals(1,car.getCar().getLane());
        for(int i=0; i < 10 ; i++){
            Mockito.when(actuator.moveForward()).thenReturn(car.getCar());
            actuator.moveForward().move();
            //  System.out.println(car.getCarPosition());
        }
        Assert.assertEquals(100, car.getCarPosition());

    }

    @Test
    public void IntegrationTestScenario7(){
        randomCar1.getRandomCar().setPosition(10);
        randomCar2.getRandomCar().setPosition(15);
        randomCar3.getRandomCar().setPosition(20);
        randomCar4.getRandomCar().setPosition(20);
        randomCar5.getRandomCar().setPosition(45);
        randomCar6.getRandomCar().setPosition(70);

        moveCars(10);
        Assert.assertEquals(50, car.getCarPosition());

        int diffBtwCars = randomCar1.getRandomCarPosition() - car.getCarPosition();

        for(int i = 0 ; i < 2 ; i++) {
            Mockito.when(sensor1.setSensorData(i)).thenReturn(diffBtwCars);
            Mockito.when(sensor2.setSensorData(i)).thenReturn(diffBtwCars + 2);
            Mockito.when(sensor3.setSensorData(i)).thenReturn(diffBtwCars + 2);
            Mockito.when(sensor4.setSensorData(i)).thenReturn(diffBtwCars + 4);
        }
        car.changeLane();
        Assert.assertEquals(2,car.getCar().getLane());

        moveCars(5);
        diffBtwCars = randomCar4.getRandomCarPosition() - car.getCarPosition();

        for(int i = 0 ; i < 2 ; i++) {
            Mockito.when(sensor1.setSensorData(i)).thenReturn(diffBtwCars);
            Mockito.when(sensor2.setSensorData(i)).thenReturn(diffBtwCars + 2);
            Mockito.when(sensor3.setSensorData(i)).thenReturn(diffBtwCars + 2);
            Mockito.when(sensor4.setSensorData(i)).thenReturn(diffBtwCars + 4);
        }
        car.changeLane();
        Assert.assertEquals(3,car.getCar().getLane());

        moveCars(3);
        Assert.assertEquals(100,car.getCarPosition());


    }
}