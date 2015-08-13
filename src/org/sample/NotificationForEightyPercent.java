package org.sample;

/**
 * Created by Randeep on 8/12/2015.
 */
public interface NotificationForEightyPercent {
    final NotificationType type = NotificationType.PARKINGEIGHTYPERCENTFULL;
    public void registerObserverForEightyPercent(Observer observer);
    public void removeObserverForEightyPercent(Observer observer);
    public void notifyObserversForEightyPercent();
}
