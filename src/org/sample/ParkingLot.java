package org.sample;

/**
 * Created by Randeep on 8/11/2015.
 */
public class ParkingLot {

    private static Integer lotCount=0;
    private Integer capacity;
    private Lot[] lot;

    public ParkingLot(Integer capacity) {
        this.capacity=capacity;
        lot=new Lot[capacity];
        for(int i=0;i<capacity;i++){
            lot[i]=new Lot(lotCount++);
        }
    }

    public Lot park(Car car){
        for(Lot tempLot:lot){
            if(tempLot.checkIsEmpty()){
                tempLot.fillLot(car);
                return tempLot;
            }
        }
        return null;
    }
    public Car unPark(Lot lot){
       return lot.freeLot();
    }

}
