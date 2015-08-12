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
}
