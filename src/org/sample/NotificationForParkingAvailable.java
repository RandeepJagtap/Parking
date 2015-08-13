package org.sample;

/**
 * Created by Randeep on 8/12/2015.
 */
public interface NotificationForParkingAvailable {
    final NotificationType type = NotificationType.PARKINGAVAILABLE;
    public void registerObserverForEmpty(Observer observer);
    public void removeObserverForEmpty(Observer observer);
    public void notifyObserversForEmpty();


}
