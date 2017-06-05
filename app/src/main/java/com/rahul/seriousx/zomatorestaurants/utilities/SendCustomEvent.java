package com.rahul.seriousx.zomatorestaurants.utilities;

/**
 * Created by root on 6/4/17.
 */

public class SendCustomEvent {
    public static final int EVENT_DB_CHANGE = 1;

    private int typeOfEvent;
    private Object data;

    public SendCustomEvent(int typeOfEvent, Object data) {
        this.typeOfEvent = typeOfEvent;
        this.data = data;
    }

    public int getTypeOfEvent() {
        return typeOfEvent;
    }

    public Object getData() {
        return data;
    }
}
