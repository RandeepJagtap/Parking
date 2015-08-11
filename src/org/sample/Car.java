package org.sample;

/**
 * Created by Randeep on 8/11/2015.
 */
public class Car {
    private static int carCount=0;
    private int carId;

    public Car() {
        this.carId = carCount++;
    }

    public int getCarId() {
        return carId;
    }

}
