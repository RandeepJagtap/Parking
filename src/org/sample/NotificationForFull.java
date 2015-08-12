package org.sample;

import java.util.ArrayList;

/**
 * Created by Randeep on 8/12/2015.
 */
public interface NotificationForFull
{
    public void registerObserverForFull(Observer observer);
    public void removeObserverForFull(Observer observer);
    public void notifyObserversForFull();

}
