package org.sample;

/**
 * Created by Randeep on 8/13/2015.
 */
public class NoSpaceAvailable extends RuntimeException {
    public NoSpaceAvailable(String message) {
        super(message);
    }
}
