package org.sample;

/**
 * Created by Randeep on 8/12/2015.
 */
public interface NotificationForEmpty {
    public void registerObserverForEmpty(Observer observer);
    public void removeObserverForEmpty(Observer observer);
    public void notifyObserversForEmpty();


}
