package org.sample;

/**
 * Created by Randeep on 8/12/2015.
 */
public interface NotificationForEightyPercent {
    public void registerObserverForEightyPercent(Observer observer);
    public void removeObserverForEightyPercent(Observer observer);
    public void notifyObserversForEightyPercent();
}
