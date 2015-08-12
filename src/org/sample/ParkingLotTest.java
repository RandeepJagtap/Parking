package org.sample;

import junit.framework.Assert;
import org.junit.Test;

import org.mockito.Mockito;

import static org.mockito.Mockito.*;


/**
 * Created by Randeep on 8/11/2015.
 */
public class ParkingLotTest {


    @Test
    public  void shouldTestCarParking() {

        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot(10,new Owner());
        Assert.assertTrue("Can Park", parkingLot.park(car) instanceof Ticket);

    }
    @Test(expected = ParkingFullException.class)
         public  void shouldTestCarParkingFail(){

        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot(0,new Owner());

        Assert.assertFalse("CanNot Park", parkingLot.park(car) instanceof Ticket);

    }
    @Test
    public  void shouldTestDuplicateCarParking(){

        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot(5,new Owner());
        Ticket ticket=parkingLot.park(car);
        Assert.assertEquals(ticket, parkingLot.park(car));

    }
    @Test
    public  void shouldTestDuplicateCarParkingFail(){

        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot(5,new Owner());
        Ticket ticket=parkingLot.park(car);
        Ticket ticket1=new Ticket(12,12);
        Assert.assertNotSame(ticket1, parkingLot.park(car));

    }
    @Test
    public  void shouldTestCarUnParking(){

        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot(10,new Owner());

        Assert.assertTrue("Can UnPark", parkingLot.unPark(parkingLot.park(car)).equals(car));

    }
    @Test(expected =NoSuchCarParkedException.class)
    public  void shouldTestCarUnParkingFail(){

        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot(0,new Owner());

        Assert.assertFalse("Cannot UnPark", parkingLot.unPark(new Ticket(200, 300)).equals(car));

    }

    @Test
    public void shouldTestParkingFull(){
        ParkingLot parkingLot = new ParkingLot(2,new Owner());
        Car car = new Car();
        parkingLot.park(car);
        parkingLot.park(new Car());
        Assert.assertTrue(parkingLot.isFull());
    }

    @Test
    public void shouldTestParkingNotFull(){
        ParkingLot parkingLot = new ParkingLot(1,new Owner());
        Car car = new Car();
        parkingLot.unPark(parkingLot.park(car));

        Assert.assertFalse(parkingLot.isFull());
    }
    @Test
    public void shouldTestOwnerNotificationOfParkingFull(){
        Owner owner = Mockito.mock(Owner.class);
        FbiAgent fbiAgent=Mockito.mock(FbiAgent.class);
        ParkingLot parkingLot = new ParkingLot(1,owner);
        parkingLot.registerObserverForFull(owner);
        parkingLot.registerObserverForFull(fbiAgent);
        Car car = new Car();
        parkingLot.park(car);


        Mockito.verify(owner,times(1)).notification(parkingLot);
        Mockito.verify(fbiAgent,times(1)).notification(parkingLot);

    }
    @Test
    public void shouldTestOwnerNotificationOfParkingFullFail(){
        Owner owner = Mockito.mock(Owner.class);
        FbiAgent fbiAgent=Mockito.mock(FbiAgent.class);
        ParkingLot parkingLot = new ParkingLot(2,owner);
        parkingLot.registerObserverForFull(owner);
        parkingLot.registerObserverForFull(fbiAgent);
        Car car = new Car();
        parkingLot.park(car);

        Mockito.verify(owner,never()).notification(parkingLot);
        Mockito.verify(fbiAgent,never()).notification(parkingLot);

    }
    @Test
    public  void shouldTestOwnerNotificationOfParkingFullOnlyOnce(){

        Owner owner = Mockito.mock(Owner.class);
        FbiAgent fbiAgent=Mockito.mock(FbiAgent.class);
        ParkingLot parkingLot = new ParkingLot(1,owner);
        parkingLot.registerObserverForFull(owner);
        parkingLot.registerObserverForFull(fbiAgent);
        Car car = new Car();
        Car car1=new Car();
        parkingLot.park(car);

       try{
           parkingLot.park(car1);
       }catch(ParkingFullException e){
           Mockito.verify(owner,times(1)).notification(parkingLot);
           Mockito.verify(fbiAgent,times(1)).notification(parkingLot);
       }



    }

    @Test
    public void shouldTestOwnerNotificationOfParkingAvailable(){
        Owner owner = Mockito.mock(Owner.class);
        ParkingLot parkingLot = new ParkingLot(1,owner);
        parkingLot.registerObserverForEmpty(owner);

        Car car = new Car();
        Ticket ticket=parkingLot.park(car);


        Mockito.verify(owner,never()).notification(parkingLot);

       parkingLot.unPark(ticket);
        Mockito.verify(owner,times(1)).notification(parkingLot);



    }
    @Test
    public void shouldTestOwnerNotificationOfParkingAvailableFail(){
        Owner owner = Mockito.mock(Owner.class);
        ParkingLot parkingLot = new ParkingLot(1,owner);
        parkingLot.registerObserverForEmpty(owner);

        Car car = new Car();


        parkingLot.unPark(parkingLot.park(car));


        Mockito.verify(owner,times(1)).notification(parkingLot);

    }
    @Test
    public  void shouldTestOwnerNotificationOfParkingAvailableOnlyOnce(){

        Owner owner = Mockito.mock(Owner.class);
        ParkingLot parkingLot = new ParkingLot(2,owner);
        parkingLot.registerObserverForEmpty(owner);

        Car car = new Car();
        Car car1=new Car();
        Ticket ticket=parkingLot.park(car);
        Ticket ticket1=parkingLot.park(car1);

        parkingLot.unPark(ticket1);
        parkingLot.unPark(ticket);

        Mockito.verify(owner,times(1)).notification(parkingLot);

    }

    @Test
    public void shouldTestOwnerNotificationOfParkingForEightyPercent(){

        Owner owner = Mockito.mock(Owner.class);
        FbiAgent fbiAgent= Mockito.mock(FbiAgent.class);
        ParkingLot parkingLot = new ParkingLot(5,owner);
        parkingLot.registerObserverForEmpty(owner);
        parkingLot.registerObserverForEightyPercent(fbiAgent);

       for(int i=0;i<4;i++){
           parkingLot.park(new Car());
       }

         Mockito.verify(fbiAgent,times(1)).notification(parkingLot);



    }
    @Test
    public void shouldTestOwnerNotificationOfParkingEightyPercent(){
        Owner owner = Mockito.mock(Owner.class);
        FbiAgent fbiAgent= Mockito.mock(FbiAgent.class);
        ParkingLot parkingLot = new ParkingLot(5,owner);
        parkingLot.registerObserverForEmpty(owner);
        parkingLot.registerObserverForEightyPercent(fbiAgent);

        for(int i=0;i<4;i++){
            parkingLot.park(new Car());
        }
        parkingLot.unPark(parkingLot.park(new Car()));

        Mockito.verify(fbiAgent,times(2)).notification(parkingLot);


    }
    @Test
    public  void shouldTestFbiAgentNotificationOfParkingAvailableAtEightyPercent(){

        Owner owner = Mockito.mock(Owner.class);
        FbiAgent fbiAgent= Mockito.mock(FbiAgent.class);
        ParkingLot parkingLot = new ParkingLot(9,owner);
        parkingLot.registerObserverForEmpty(owner);
        parkingLot.registerObserverForEightyPercent(fbiAgent);

        for(int i=0;i<7;i++){
            parkingLot.park(new Car());
        }
        Ticket ticket=parkingLot.park(new Car());
        parkingLot.unPark(ticket);
        parkingLot.unPark(parkingLot.park(new Car()));

        Mockito.verify(fbiAgent,times(2)).notification(parkingLot);


    }

}