package org.sample;

/**
 * Created by Randeep on 8/11/2015.
 */
public class Lot {

    private boolean isEmpty;
    private Integer lotId;
    private Car car;

    public Lot(Integer lotId){
        isEmpty=true;
        this.lotId=lotId;
        this.car=null;
    }

    public Car freeLot() {
        isEmpty=true;
        Car car=this.car;
        this.car=null;
        return car;
    }

    public void fillLot(Car car) {
        isEmpty=false;
       this.car=car;
    }

    public boolean checkIsEmpty() {
      return isEmpty;
    }

}
