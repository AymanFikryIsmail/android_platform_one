package com.iti.mad.firstlab.list_view;

import java.io.Serializable;

/**
 * Created by ayman on 2019-01-30.
 */

public class Cake implements Serializable {
    private int  imageCake;
    private String cakeName;
    private  String cakeDesc;

    private  String image;

    public Cake(String image, String cakeName, String cakeDesc) {
        this.image = image;
        this.cakeName = cakeName;
        this.cakeDesc = cakeDesc;
    }
    public Cake(int imageCake, String cakeName, String cakeDesc) {
        this.imageCake = imageCake;
        this.cakeName = cakeName;
        this.cakeDesc = cakeDesc;
    }

    public String getImage() {
        return image;
    }

    public int getImageCake() {
        return imageCake;
    }

    public String getCakeName() {
        return cakeName;
    }

    public String getCakeDesc() {
        return cakeDesc;
    }
}
