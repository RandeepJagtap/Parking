package org.sample;

import java.util.ArrayList;
import java.util.Arrays;

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
    public ParkingLot(Integer capacity,Owner owner) {
        fullSign=false;
        this.owner=owner;
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

    public Owner getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingLot)) return false;

        ParkingLot that = (ParkingLot) o;

        if (fullSign != null ? !fullSign.equals(that.fullSign) : that.fullSign != null) return false;
        if (lotCount != null ? !lotCount.equals(that.lotCount) : that.lotCount != null) return false;
        if (getCapacity() != null ? !getCapacity().equals(that.getCapacity()) : that.getCapacity() != null)
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(lots, that.lots)) return false;
        if (getTickets() != null ? !getTickets().equals(that.getTickets()) : that.getTickets() != null) return false;
        return !(getOwner() != null ? !getOwner().equals(that.getOwner()) : that.getOwner() != null);

    }

    @Override
    public int hashCode() {
        int result = fullSign != null ? fullSign.hashCode() : 0;
        result = 31 * result + (lotCount != null ? lotCount.hashCode() : 0);
        result = 31 * result + (getCapacity() != null ? getCapacity().hashCode() : 0);
        result = 31 * result + (lots != null ? Arrays.hashCode(lots) : 0);
        result = 31 * result + (getTickets() != null ? getTickets().hashCode() : 0);
        result = 31 * result + (getOwner() != null ? getOwner().hashCode() : 0);
        return result;
    }
}
