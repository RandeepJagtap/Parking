package org.sample;

/**
 * Created by Randeep on 8/12/2015.
 */
public class NoSuchCarParkedException extends RuntimeException {
    public NoSuchCarParkedException(String message) {
        super(message);
    }
}
