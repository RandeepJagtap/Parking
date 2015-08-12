package org.sample;

/**
 * Created by Randeep on 8/12/2015.
 */
public class Ticket {
    private Integer lotId;
    private Integer carId;

    public Ticket(Integer lotId,Integer carId) {
        this.lotId = lotId;
        this.carId = carId;
    }

    public Integer getLotId() {
        return lotId;
    }

    public Integer getCarId() {
        return carId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;

        Ticket ticket = (Ticket) o;

        if (!getLotId().equals(ticket.getLotId())) return false;
        return getCarId().equals(ticket.getCarId());

    }

    @Override
    public int hashCode() {
        int result = getLotId().hashCode();
        result = 31 * result + getCarId().hashCode();
        return result;
    }
}
