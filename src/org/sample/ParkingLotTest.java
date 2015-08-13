package org.sample;

import junit.framework.Assert;
import org.junit.Test;

import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


/**
 * Created by Randeep on 8/11/2015.
 */
public class ParkingLotTest {


    @Test
    public  void shouldTestCarParking() {

        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot(10,new Owner(),new StandardAttendant());
        Assert.assertTrue("Can Park", parkingLot.park(car) instanceof Ticket);

    }
    @Test(expected = ParkingFullException.class)
         public  void shouldTestCarParkingFail(){

        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot(0,new Owner(),new StandardAttendant());

        Assert.assertFalse("CanNot Park", parkingLot.park(car) instanceof Ticket);

    }
    @Test
    public  void shouldTestDuplicateCarParking(){

        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot(5,new Owner(),new StandardAttendant());
        Ticket ticket=parkingLot.park(car);
        Assert.assertEquals(ticket, parkingLot.park(car));

    }
    @Test
    public  void shouldTestDuplicateCarParkingFail(){

        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot(5,new Owner(),new StandardAttendant());
        Ticket ticket=parkingLot.park(car);
        Ticket ticket1=new Ticket(12,12);
        Assert.assertNotSame(ticket1, parkingLot.park(car));

    }
    @Test
    public  void shouldTestCarUnParking(){

        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot(10,new Owner(),new StandardAttendant());

        Assert.assertTrue("Can UnPark", parkingLot.unPark(parkingLot.park(car)).equals(car));

    }
    @Test(expected =NoSuchCarParkedException.class)
    public  void shouldTestCarUnParkingFail(){

        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot(0,new Owner(),new StandardAttendant());

        Assert.assertFalse("Cannot UnPark", parkingLot.unPark(new Ticket(200, 300)).equals(car));

    }

    @Test
    public void shouldTestParkingFull(){
        ParkingLot parkingLot = new ParkingLot(2,new Owner(),new StandardAttendant());
        Car car = new Car();
        parkingLot.park(car);
        parkingLot.park(new Car());
        Assert.assertTrue(parkingLot.isFull());
    }

    @Test
    public void shouldTestParkingNotFull(){
        ParkingLot parkingLot = new ParkingLot(1,new Owner(),new StandardAttendant());
        Car car = new Car();
        parkingLot.unPark(parkingLot.park(car));

        Assert.assertFalse(parkingLot.isFull());
    }
    @Test
    public void shouldTestOwnerNotificationOfParkingFull(){
        Owner owner = Mockito.mock(Owner.class);
        FbiAgent fbiAgent=Mockito.mock(FbiAgent.class);
        ParkingLot parkingLot = new ParkingLot(1,owner,new StandardAttendant());
        parkingLot.registerObserverForFull(owner);
        parkingLot.registerObserverForFull(fbiAgent);
        Car car = new Car();
        parkingLot.park(car);


        Mockito.verify(owner,times(1)).notification(parkingLot, NotificationType.PARKINGFULL);
        Mockito.verify(fbiAgent,times(1)).notification(parkingLot, NotificationType.PARKINGFULL);

    }
    @Test
    public void shouldTestOwnerNotificationOfParkingFullFail(){
        Owner owner = Mockito.mock(Owner.class);
        FbiAgent fbiAgent=Mockito.mock(FbiAgent.class);
        ParkingLot parkingLot = new ParkingLot(2,owner,new StandardAttendant());
        parkingLot.registerObserverForFull(owner);
        parkingLot.registerObserverForFull(fbiAgent);
        Car car = new Car();
        parkingLot.park(car);

        Mockito.verify(owner,never()).notification(parkingLot,NotificationType.PARKINGFULL);
        Mockito.verify(fbiAgent,never()).notification(parkingLot,NotificationType.PARKINGFULL);

    }
    @Test
    public  void shouldTestOwnerNotificationOfParkingFullOnlyOnce(){

        Owner owner = Mockito.mock(Owner.class);
        FbiAgent fbiAgent=Mockito.mock(FbiAgent.class);
        ParkingLot parkingLot = new ParkingLot(1,owner,new StandardAttendant());
        parkingLot.registerObserverForFull(owner);
        parkingLot.registerObserverForFull(fbiAgent);
        Car car = new Car();
        Car car1=new Car();
        parkingLot.park(car);

       try{
           parkingLot.park(car1);
       }catch(ParkingFullException e){
           Mockito.verify(owner,times(1)).notification(parkingLot,NotificationType.PARKINGFULL);
           Mockito.verify(fbiAgent,times(1)).notification(parkingLot,NotificationType.PARKINGFULL);
       }



    }

    @Test
    public void shouldTestOwnerNotificationOfParkingAvailable(){
        Owner owner = Mockito.mock(Owner.class);
        ParkingLot parkingLot = new ParkingLot(1,owner,new StandardAttendant());
        parkingLot.registerObserverForEmpty(owner);

        Car car = new Car();
        Ticket ticket=parkingLot.park(car);


        Mockito.verify(owner,never()).notification(parkingLot,NotificationType.PARKINGAVAILABLE);

       parkingLot.unPark(ticket);
        Mockito.verify(owner,times(1)).notification(parkingLot, NotificationType.PARKINGAVAILABLE);



    }
    @Test
    public void shouldTestOwnerNotificationOfParkingAvailableFail(){
        Owner owner = Mockito.mock(Owner.class);
        ParkingLot parkingLot = new ParkingLot(1,owner,new StandardAttendant());
        parkingLot.registerObserverForEmpty(owner);

        Car car = new Car();


        parkingLot.unPark(parkingLot.park(car));


        Mockito.verify(owner,times(1)).notification(parkingLot,NotificationType.PARKINGAVAILABLE);

    }
    @Test
    public  void shouldTestOwnerNotificationOfParkingAvailableOnlyOnce(){

        Owner owner = Mockito.mock(Owner.class);
        ParkingLot parkingLot = new ParkingLot(2,owner,new StandardAttendant());
        parkingLot.registerObserverForEmpty(owner);

        Car car = new Car();
        Car car1=new Car();
        Ticket ticket=parkingLot.park(car);
        Ticket ticket1=parkingLot.park(car1);

        parkingLot.unPark(ticket1);
        parkingLot.unPark(ticket);

        Mockito.verify(owner,times(1)).notification(parkingLot,NotificationType.PARKINGAVAILABLE);

    }

    @Test
    public void shouldTestOwnerNotificationOfParkingForEightyPercent(){

        Owner owner = Mockito.mock(Owner.class);
        FbiAgent fbiAgent= Mockito.mock(FbiAgent.class);
        ParkingLot parkingLot = new ParkingLot(5,owner,new StandardAttendant());
        parkingLot.registerObserverForEmpty(owner);
        parkingLot.registerObserverForEightyPercent(fbiAgent);

       for(int i=0;i<4;i++){
           parkingLot.park(new Car());
       }

         Mockito.verify(fbiAgent,times(1)).notification(parkingLot,NotificationType.PARKINGEIGHTYPERCENTFULL);



    }
    @Test
    public void shouldTestOwnerNotificationOfParkingEightyPercent(){
        Owner owner = Mockito.mock(Owner.class);
        FbiAgent fbiAgent= Mockito.mock(FbiAgent.class);
        ParkingLot parkingLot = new ParkingLot(5,owner,new StandardAttendant());
        parkingLot.registerObserverForEmpty(owner);
        parkingLot.registerObserverForEightyPercent(fbiAgent);

        for(int i=0;i<4;i++){
            parkingLot.park(new Car());
        }
        parkingLot.unPark(parkingLot.park(new Car()));

        Mockito.verify(fbiAgent,times(2)).notification(parkingLot,NotificationType.PARKINGEIGHTYPERCENTFULL);


    }
    @Test
    public  void shouldTestFbiAgentNotificationOfParkingAvailableAtEightyPercent(){

        Owner owner = Mockito.mock(Owner.class);
        FbiAgent fbiAgent= Mockito.mock(FbiAgent.class);
        ParkingLot parkingLot = new ParkingLot(9,owner,new StandardAttendant());
        parkingLot.registerObserverForEmpty(owner);
        parkingLot.registerObserverForEightyPercent(fbiAgent);

        for(int i=0;i<7;i++){
            parkingLot.park(new Car());
        }
        Ticket ticket=parkingLot.park(new Car());
        parkingLot.unPark(ticket);
        parkingLot.unPark(parkingLot.park(new Car()));

        Mockito.verify(fbiAgent,times(2)).notification(parkingLot,NotificationType.PARKINGEIGHTYPERCENTFULL);


    }
    @Test
    public void shouldTestUnSubscribeNotNotificationOfParkingFull(){
        Owner owner = Mockito.mock(Owner.class);
        FbiAgent fbiAgent=Mockito.mock(FbiAgent.class);
        ParkingLot parkingLot = new ParkingLot(1,owner,new StandardAttendant());
        parkingLot.registerObserverForFull(owner);
        parkingLot.registerObserverForFull(fbiAgent);
        parkingLot.removeObserverForFull(fbiAgent);
        Car car = new Car();
        parkingLot.park(car);


        Mockito.verify(owner,times(1)).notification(parkingLot, NotificationType.PARKINGFULL);
        Mockito.verify(fbiAgent,never()).notification(parkingLot,NotificationType.PARKINGFULL);

    }
    @Test
    public void shouldTestAttendantParking(){

        Attendant attendant=new StandardAttendant("Randeep");
        ParkingLot parkingLot = new ParkingLot(1,new Owner(),attendant);
        parkingLot.registerObserverForFull(attendant);
        parkingLot.registerObserverForEmpty(attendant);
        ParkingLot parkingLot1 = new ParkingLot(2,new Owner(),attendant);
        parkingLot1.registerObserverForFull(attendant);
        parkingLot1.registerObserverForEmpty(attendant);

        Car car = new Car();
        attendant.getFreeParkingLot().park(new Car());
        Ticket ticket = attendant.getFreeParkingLot().park(car);
        Assert.assertTrue(ticket instanceof Ticket);

    }
    @Test(expected = NoSpaceAvailable.class)
    public void shouldTestAttendantParkingFail(){

        Attendant attendant=new StandardAttendant("Randeep");
        ParkingLot parkingLot = new ParkingLot(1,new Owner(),attendant);
        parkingLot.registerObserverForFull(attendant);
        parkingLot.registerObserverForEmpty(attendant);
        ParkingLot parkingLot1 = new ParkingLot(1,new Owner(),attendant);
        parkingLot1.registerObserverForFull(attendant);
        parkingLot1.registerObserverForEmpty(attendant);

        Car car = new Car();
        attendant.getFreeParkingLot().park(new Car());
        attendant.getFreeParkingLot().park(new Car());
        Ticket ticket = attendant.getFreeParkingLot().park(car);

    }
    @Test
    public void shouldTestAttendantMaxParkingSpace(){

        Attendant attendant=new MostFreeAttendant("Randeep");
        Owner owner = new Owner();
        ParkingLot parkingLot = new ParkingLot(2,owner,attendant);
        parkingLot.registerObserverForFull(attendant);
        parkingLot.registerObserverForEmpty(attendant);
        ParkingLot parkingLot1 = new ParkingLot(3,owner,attendant);
        parkingLot1.registerObserverForFull(attendant);
        parkingLot1.registerObserverForEmpty(attendant);
        Car car = new Car();

        attendant.getFreeParkingLot().park(new Car());
        parkingLot1.park(new Car());

        Assert.assertEquals(parkingLot, attendant.getFreeParkingLot());

    }
    @Test(expected = NoSpaceAvailable.class)
    public void shouldTestAttendantMaxParkingSpaceFail(){

        Attendant attendant=new MostFreeAttendant("Randeep");
        ParkingLot parkingLot = new ParkingLot(1,new Owner(),attendant);
        parkingLot.registerObserverForFull(attendant);
        parkingLot.registerObserverForEmpty(attendant);
        ParkingLot parkingLot1 = new ParkingLot(1,new Owner(),attendant);
        parkingLot1.registerObserverForFull(attendant);
        parkingLot1.registerObserverForEmpty(attendant);
        Car car = new Car();

        attendant.getFreeParkingLot().park(new Car());
        attendant.getFreeParkingLot().park(new Car());

        attendant.getFreeParkingLot();

    }

}