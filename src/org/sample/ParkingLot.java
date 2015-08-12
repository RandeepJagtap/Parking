package org.sample;

import java.util.ArrayList;

/**
 * Created by Randeep on 8/11/2015.
 */
public class ParkingLot {

    private static Integer lotCount=0;
    private Integer capacity;
    private Lot[] lots;
    private ArrayList<Ticket> tickets=new ArrayList<>();

    public ParkingLot(Integer capacity) {
        this.capacity=capacity;
        lots =new Lot[capacity];
        for(int i=0;i<capacity;i++){
            lots[i]=new Lot(lotCount++);
        }
    }

    public Ticket park(Car car) throws ParkingFullException {
        for(Ticket tempTicket:tickets)
        {
            if(tempTicket.getCarId().equals(car.getCarId())){
                return tempTicket;
            }
        }
        for(Lot tempLot: lots){
            if(tempLot.checkIsEmpty()){
                tempLot.fillLot(car);
                Ticket ticket = new Ticket(tempLot.getLotId(),car.getCarId());
                tickets.add(ticket);
                return ticket;
            }
        }
        throw new ParkingFullException("Parking Full");
    }

    public Car unPark(Ticket ticket) throws NoSuchCarParkedException {
        for(Lot tempLot : lots) {
            if(tempLot.getLotId().equals(ticket.getLotId())) {
                tickets.remove(ticket);
                return tempLot.freeLot(ticket);
            }
        }
       throw new NoSuchCarParkedException("No Car Found");
    }

}
