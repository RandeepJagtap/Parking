package org.sample;

import java.util.ArrayList;

/**
 * Created by Randeep on 8/11/2015.
 */
public class ParkingLot {
    private Boolean fullSign;
    private Integer lotCount=0;
    private Integer capacity;
    private Lot[] lots;
    private ArrayList<Ticket> tickets=new ArrayList<>();
    private Owner owner;

    public ParkingLot(Integer capacity) {
        fullSign=false;
        owner = new Owner("Randeep");
        this.capacity=capacity;
        lots =new Lot[capacity];
        for(int i=0;i<capacity;i++){
            lots[i]=new Lot(lotCount++);
        }
        owner.createParkingLot(this);
    }

    public Ticket park(Car car) throws ParkingFullException {
        for(Ticket tempTicket:tickets)
        {
            if(tempTicket.getCarId().equals(car.getCarId())){
                return tempTicket;
            }
        }
        if (isFull())
            throw new ParkingFullException("Parking Full");
        for(Lot tempLot: lots){
            if(tempLot.checkIsEmpty()){
                tempLot.fillLot(car);
                Ticket ticket = new Ticket(tempLot.getLotId(),car.getCarId());
                tickets.add(ticket);
                if (tickets.size()==capacity)
                    fullSign=owner.notifyParkingIsFull(this);
                return ticket;
            }
        }
        throw new ParkingFullException("Parking Full");
    }

    public Car unPark(Ticket ticket) throws NoSuchCarParkedException {
        for(Lot tempLot : lots) {
            if(tempLot.getLotId().equals(ticket.getLotId())) {
                tickets.remove(ticket);
                if (fullSign)
                    fullSign=owner.notifyParkingAvailable(this);
                return tempLot.freeLot(ticket);
            }
        }
       throw new NoSuchCarParkedException("No Car Found");
    }
    public Boolean isFull(){

        return fullSign;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
}
