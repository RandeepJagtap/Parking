package org.sample;

/**
 * Created by Randeep on 8/11/2015.
 */
public class Lot {

    private boolean isEmpty;
    private Integer lotId;
    private Integer carId;

    public Lot(Integer lotId){
        isEmpty=true;
        this.lotId=lotId;
        this.carId=-1;
    }

    public void freeLot() {
        isEmpty=true;
        this.carId=-1;
    }

    public void fillLot(int carId) {
        isEmpty=false;
        this.carId=carId;
    }

    public boolean checkIsEmpty() {
      return isEmpty;
    }

}
