package com.driver;

import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {

    Map<String, Order> orderMap = new HashMap<>(); // orderId, Order

    //Each order is assigned to at most one delivery partner.
    // This means that either the order is assigned to exactly one delivery partner or it is left unassigned.
    // partnerId --> might be null
    Map<String, String> orderPartnerPair = new HashMap<>(); // oderId, partnerId

//    Map<String, List<Order>> partnerListOrderPair = new HashMap<>(); // partnerId, list of orders
    Map<String, List<String>> partnerListOrderPair = new HashMap<>(); // partnerId, list of orderId
    Map<String, DeliveryPartner> deliveryPartnerMap = new HashMap<>(); // partnerId, DeliveryPartner

    public void addOrder(Order order){
        orderMap.put(order.getId(), order);
        orderPartnerPair.put(order.getId(), null); // not assigned any partner
    }
    public void addPartner(String partnerId){
        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerId);
        deliveryPartnerMap.put(partnerId, deliveryPartner);

        partnerListOrderPair.put(partnerId, null);
    }
    public void addOrderPartnerPair(String orderId, String partnerId){

        if(orderMap.containsKey(orderId) && deliveryPartnerMap.containsKey(partnerId)){

            orderPartnerPair.put(orderId, partnerId);

            //List<Order> list = partnerListOrderPair.get(partnerId);
            //Order order = orderMap.get(orderId);
            //list.add(order);
            List<String> list = new ArrayList<>();
            if(partnerListOrderPair.containsKey(partnerId))
               list = partnerListOrderPair.get(partnerId);
            list.add(orderId);
            partnerListOrderPair.put(partnerId, list);

//        DeliveryPartner deliveryPartner = deliveryPartnerMap.get(partnerId);
//        deliveryPartner.setNumberOfOrders(list.size());
        }

    }
    public Order getOrderById(String orderId){
        return orderMap.get(orderId);
    }
    public DeliveryPartner getPartnerById(String partnerId){
        return deliveryPartnerMap.get(partnerId);
    }
    public Integer getOrderCountByPartnerId(String partnerId){
        //List<Order> list = partnerListOrderPair.get(partnerId);
        List<String> list = new ArrayList<>();
        if(partnerListOrderPair.containsKey(partnerId))
            list = partnerListOrderPair.get(partnerId);
        return list.size();
    }
    public List<String> getOrdersByPartnerId(String partnerId){
        //List<Order> list = partnerListOrderPair.get(partnerId);
        List<String> list = new ArrayList<>();
        if(partnerListOrderPair.containsKey(partnerId))
            list = partnerListOrderPair.get(partnerId);
        return list;
    }
    public List<String> getAllOrders(){
        List<String> list = null;
        for(String orderId : orderMap.keySet())
            list.add(orderId);

        //list.addAll(orderMap.keySet());
        return list;
    }
    public Integer getCountOfUnassignedOrders(){
        Integer count = 0;
        for(String partnerId : orderPartnerPair.values()){
            if(partnerId == null)
                count++;
        }
        return count;
    }
    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId){
        Integer count = 0;

        int hour = Integer.parseInt(time.substring(0,2));
        int min = Integer.parseInt(time.substring(3));
        int timeInt = hour*60 + min;

        if(partnerListOrderPair.containsKey(partnerId)){
            List<String> list = partnerListOrderPair.get(partnerId);
            for(String orderId : list){
                if(orderMap.containsKey(orderId)){
                    Order order = orderMap.get(orderId);
                    int deliverTime = order.getDeliveryTime();
                    if(deliverTime > timeInt)
                        count++;
                }
            }
        }

        return count;
    }
    public String getLastDeliveryTimeByPartnerId(String partnerId){
        int lastTime = 0;
        if(partnerListOrderPair.containsKey(partnerId)){
            List<String> list = partnerListOrderPair.get(partnerId);
            for(String orderId : list){
                if(orderMap.containsKey(orderId)){
                    Order order = orderMap.get(orderId);
                    int deliverTime = order.getDeliveryTime();
                    if(deliverTime > lastTime)
                        lastTime = deliverTime;
                }
            }
        }

        //covert int to string
        int hour = lastTime/60;
        int min = lastTime%60;

        String hourS = Integer.toString(hour);
        String minS = Integer.toString((min));
        if(hourS.length() == 1) hourS = "0" + hourS;
        if(minS.length() == 1) minS = "0" + minS;
        return hourS + ":" + minS;
//        String ans = hour+":"+min;
//        return ans;
    }
    public void deletePartnerById(String partnerId){
//        if(deliveryPartnerMap.containsKey(partnerId))
        deliveryPartnerMap.remove(partnerId);
//        if(partnerListOrderPair.containsKey(partnerId))
        partnerListOrderPair.remove(partnerId);

        for(String orderId : orderPartnerPair.keySet()){
            if(orderPartnerPair.get(orderId).equals(partnerId))
                orderPartnerPair.put(orderId, null);
            //orderPartnerPair.remove(orderId);
        }
    }
    public void deleteOrderById(String orderId){
//        if(orderMap.containsKey(orderId))
            orderMap.remove(orderId);

        if(orderPartnerPair.containsKey(orderId)){
            String partnerId = orderPartnerPair.get(orderId);
            orderPartnerPair.remove(orderId);

            List<String> list = partnerListOrderPair.get(partnerId);
            list.remove(orderId);
            partnerListOrderPair.put(partnerId, list); // update again

//        DeliveryPartner deliveryPartner = deliveryPartnerMap.get(partnerId);
//        deliveryPartner.setNumberOfOrders(list.size());
        }

    }
}
