package com.driver;

public class Order {

    private String id;
    private int deliveryTime;//in minutes

    public Order() {
    }

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id = id;

        int hour = Integer.parseInt(deliveryTime.substring(0,2));
        int min = Integer.parseInt(deliveryTime.substring(3));

        this.deliveryTime = hour*60 + min;
//        if(hour <=12) this.deliveryTime = hour*60 + min;
//        else this.deliveryTime = (hour-12)*60 + min;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}

    public void setId(String id) {
        this.id = id;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
