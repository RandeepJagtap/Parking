package org.sample;


/**
 * Created by Randeep on 8/12/2015.
 */
public class ParkingFullException extends RuntimeException {
    public ParkingFullException(String message) {
        super(message);
    }
}
