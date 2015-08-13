package org.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Randeep on 8/11/2015.
 */
public class ParkingLot implements NotificationForFull,NotificationForParkingAvailable,NotificationForEightyPercent {
    private boolean fullSign;
    private boolean eightyPercentlFlag;
    private Integer lotCount = 0;
    private Integer capacity;
    private Lot[] lots;
    private List<Ticket> tickets = new ArrayList<>();
    private Owner owner;
    private List<Observer> fullObservers = new ArrayList<Observer>();
    private List<Observer> availabelObservers = new ArrayList<Observer>();
    private List<Observer> eightyPercentlObservers = new ArrayList<Observer>();
    private Attendant attendant;

    public ParkingLot(Integer capacity, Owner owner,Attendant attendant) {
        this.attendant=attendant;
        fullSign = false;
        this.owner = owner;
        this.capacity = capacity;
        attendant.assignParkingLot(this);
        owner.createParkingLot(this);
        lots = new Lot[capacity];
        for (int i = 0; i < capacity; i++) {
            lots[i] = new Lot(lotCount++);
        }
        if (capacity==0)
        {
            notifyObserversForFull();
            this.fullSign = true;

        }



    }

    public Ticket park(Car car) throws ParkingFullException {
        for (Ticket tempTicket : tickets) {
            if (tempTicket.getCarId().equals(car.getCarId())) {
                return tempTicket;
            }
        }
        if (isFull())
            throw new ParkingFullException("Parking Full");
        for (Lot tempLot : lots) {
            if (tempLot.checkIsEmpty()) {
                tempLot.fillLot(car);
                Ticket ticket = new Ticket(tempLot.getLotId(), car.getCarId());
                tickets.add(ticket);
                if (tickets.size() == capacity) {
                    notifyObserversForFull();
                    this.fullSign = true;
                }
                if (tickets.size() >= .8*capacity && !eightyPercentlFlag) {
                    notifyObserversForEightyPercent();
                    this.eightyPercentlFlag = true;
                }

                return ticket;
            }
        }
        throw new ParkingFullException("Parking Full");
    }

    public Car unPark(Ticket ticket) throws NoSuchCarParkedException {
        for (Lot tempLot : lots) {
            if (tempLot.getLotId().equals(ticket.getLotId())) {
                tickets.remove(ticket);
                if (fullSign) {
                    notifyObserversForEmpty();
                    fullSign = false;
                }
                if(tickets.size() < .8*capacity)
                    this.eightyPercentlFlag = false;
                if (tickets.size() <= .8*capacity && eightyPercentlFlag) {
                    notifyObserversForEightyPercent();

                }

                return tempLot.freeLot(ticket);
            }
        }
        throw new NoSuchCarParkedException("No Car Found");
    }

    public Boolean isFull() {

        return fullSign;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public Owner getOwner() {
        return owner;
    }


    @Override
    public void registerObserverForFull(Observer observer) {
        fullObservers.add(observer);
    }

    @Override
    public void removeObserverForFull(Observer observer) {
        fullObservers.remove(observer);
    }

    @Override
    public void notifyObserversForFull( ) {
        for (Observer ob : fullObservers) {
            ob.notification(this,NotificationType.PARKINGFULL);
        }
    }

    @Override
    public void registerObserverForEmpty(Observer observer) {
        availabelObservers.add(observer);
    }

    @Override
    public void removeObserverForEmpty(Observer observer) {
        availabelObservers.remove(observer);
    }

    @Override
    public void notifyObserversForEmpty() {
        for (Observer ob : availabelObservers) {
            ob.notification(this,NotificationType.PARKINGAVAILABLE);
        }
    }

    @Override
    public void registerObserverForEightyPercent(Observer observer) {
        eightyPercentlObservers.add(observer);
    }

    @Override
    public void removeObserverForEightyPercent(Observer observer) {
        eightyPercentlObservers.add(observer);
    }

    @Override
    public void notifyObserversForEightyPercent() {
        for (Observer ob : eightyPercentlObservers) {
            ob.notification(this,NotificationType.PARKINGEIGHTYPERCENTFULL);
        }
    }

    public Integer freeSpaces(){


        return capacity-tickets.size();
    }
}