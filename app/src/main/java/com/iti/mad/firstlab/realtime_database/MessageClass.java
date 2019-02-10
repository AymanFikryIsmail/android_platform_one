package com.iti.mad.firstlab.realtime_database;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by ayman on 2019-02-06.
 */

@IgnoreExtraProperties
public class MessageClass {

    private String name;
    private String text;

    /**
     * Empty constructor needed for Firebase object deserialization.
     */
    public MessageClass() {
    }

    public MessageClass(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

//    @Override
//    public String toString() {
//        return "(" + name + ":" + text + ")";
//    }

}