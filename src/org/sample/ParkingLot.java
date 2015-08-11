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

    public boolean canPark(Car car){
        for(Lot tempLot:lot){
            if(tempLot.checkIsEmpty()){
                tempLot.fillLot(car.getCarId());
                return true;
            }
        }
        return false;
    }

}
