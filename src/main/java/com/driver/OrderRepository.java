package com.driver;

import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {

   /* Map<String, Order> orderMap = new HashMap<>(); // orderId, Order

    //Each order is assigned to at most one delivery partner.
    // This means that either the order is assigned to exactly one delivery partner or it is left unassigned.
    // partnerId --> might be null
    Map<String, String> orderPartnerPair = new HashMap<>(); // oderId, partnerId

//    Map<String, List<Order>> partnerListOrderPair = new HashMap<>(); // partnerId, list of orders
    Map<String, List<String>> partnerListOrderPair = new HashMap<>(); // partnerId, list of orderId
    Map<String, DeliveryPartner> deliveryPartnerMap = new HashMap<>(); // partnerId, DeliveryPartner

    public void addOrder(Order order){
        orderMap.put(order.getId(), order);
//        orderPartnerPair.put(order.getId(), null); // not assigned any partner
    }
    public void addPartner(String partnerId){
        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerId);
        deliveryPartnerMap.put(partnerId, deliveryPartner);

//        partnerListOrderPair.put(partnerId, null);
    }
    public void addOrderPartnerPair(String orderId, String partnerId){

        if(orderMap.containsKey(orderId) && deliveryPartnerMap.containsKey(partnerId)){

            orderPartnerPair.put(orderId, partnerId);

            //List<Order> list = partnerListOrderPair.get(partnerId); Order order = orderMap.get(orderId); list.add(order);
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
//        List<String> list = new ArrayList<>();
//        for(String orderId : orderMap.keySet())
//            list.add(orderId);
//        //list.addAll(orderMap.keySet());
//
//        return list;
        return new ArrayList<>(orderMap.keySet());
    }
    public Integer getCountOfUnassignedOrders(){
        Integer count = 0;

        List<String> list = new ArrayList<>(orderMap.keySet());
        for(String orderId : list){
            if(!orderPartnerPair.containsKey(orderId))
                count++;
        }

//        for(String orderId : orderMap.keySet()){
//            if(!orderPartnerPair.containsKey(orderId))
//                count++;
//        }

//        for(String partnerId : orderPartnerPair.values()){
//            if(partnerId == null)
//                count++;
//        }
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
        if(deliveryPartnerMap.containsKey(partnerId))
            deliveryPartnerMap.remove(partnerId);

        if(partnerListOrderPair.containsKey(partnerId)){
            List<String> list = partnerListOrderPair.get(partnerId);
            for(String orderId : list){
                if(orderPartnerPair.containsKey(orderId))
                    orderPartnerPair.remove(orderId);
            }
            partnerListOrderPair.remove(partnerId);

//            for(String orderId : orderPartnerPair.keySet()){
//                if(orderPartnerPair.get(orderId).equals(partnerId))
//                    orderPartnerPair.put(orderId, null);
                //orderPartnerPair.remove(orderId);
//            }
        }
    }
    public void deleteOrderById(String orderId){
        if(orderMap.containsKey(orderId))
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

    }*/



    private HashMap<String, Order> orderHashMap;
    private HashMap<String, DeliveryPartner> deliveryPartnerHashMap;
    private HashMap<String, String> orderPartnerHashMap;
    private HashMap<String, HashSet<String>> partnerOrderHashMap;

    public OrderRepository() {
        this.orderHashMap = new HashMap<>();
        this.deliveryPartnerHashMap = new HashMap<>();
        this.orderPartnerHashMap = new HashMap<>();
        this.partnerOrderHashMap = new HashMap<>();
    }

    public void addOrder(Order order) {
        orderHashMap.put(order.getId(), order);
    }

    public void addPartner(String partnerId) {
        deliveryPartnerHashMap.put(partnerId, new DeliveryPartner(partnerId));
    }


    public void addOrderPartnerPair(String orderId, String partnerId) {

        if(orderHashMap.containsKey(orderId) && deliveryPartnerHashMap.containsKey(partnerId)){

            HashSet<String> currentOrders = new HashSet<String>();
            if(partnerOrderHashMap.containsKey(partnerId))
                currentOrders = partnerOrderHashMap.get(partnerId);
            currentOrders.add(orderId);
            partnerOrderHashMap.put(partnerId, currentOrders);

            DeliveryPartner partner = deliveryPartnerHashMap.get(partnerId);
            partner.setNumberOfOrders(currentOrders.size());
            orderPartnerHashMap.put(orderId, partnerId);
        }
    }

    public Order getOrderById(String orderId) {
        return orderHashMap.get(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        return deliveryPartnerHashMap.get(partnerId);
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        Integer orderCount = 0;
        if(deliveryPartnerHashMap.containsKey(partnerId)){
            orderCount = deliveryPartnerHashMap.get(partnerId).getNumberOfOrders();
        }
        return orderCount;
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        HashSet<String> orderList = new HashSet<>();
        if(partnerOrderHashMap.containsKey(partnerId)) orderList = partnerOrderHashMap.get(partnerId);
        return new ArrayList<>(orderList);
    }

    public List<String> getAllOrders() {
        return new ArrayList<>(orderHashMap.keySet());
    }

    public Integer getCountOfUnassignedOrders() {
        Integer countOfOrders = 0;
        List<String> orders =  new ArrayList<>(orderHashMap.keySet());
        for(String orderId: orders){
            if(!orderPartnerHashMap.containsKey(orderId)){
                countOfOrders += 1;
            }
        }
        return countOfOrders;
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String timeS, String partnerId) {
        Integer hour = Integer.valueOf(timeS.substring(0, 2));
        Integer minutes = Integer.valueOf(timeS.substring(3));
        Integer time = hour*60 + minutes;

        Integer countOfOrders = 0;
        if(partnerOrderHashMap.containsKey(partnerId)){
            HashSet<String> orders = partnerOrderHashMap.get(partnerId);
            for(String order: orders){
                if(orderHashMap.containsKey(order)){
                    Order currOrder = orderHashMap.get(order);
                    if(time < currOrder.getDeliveryTime()){
                        countOfOrders += 1;
                    }
                }
            }
        }
        return countOfOrders;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        Integer time = 0;

        if(partnerOrderHashMap.containsKey(partnerId)){
            HashSet<String> orders = partnerOrderHashMap.get(partnerId);
            for(String order: orders){
                if(orderHashMap.containsKey(order)){
                    Order currOrder = orderHashMap.get(order);
                    time = Math.max(time, currOrder.getDeliveryTime());
                }
            }
        }

        Integer hour = time/60;
        Integer minutes = time%60;

        String hourInString = String.valueOf(hour);
        String minInString = String.valueOf(minutes);
        if(hourInString.length() == 1){
            hourInString = "0" + hourInString;
        }
        if(minInString.length() == 1){
            minInString = "0" + minInString;
        }

        return  hourInString + ":" + minInString;
    }

    public void deletePartnerById(String partnerId) {
        HashSet<String> orders = new HashSet<>();
        if(partnerOrderHashMap.containsKey(partnerId)){
            orders = partnerOrderHashMap.get(partnerId);
            for(String order: orders){
                if(orderPartnerHashMap.containsKey(order)){

                    orderPartnerHashMap.remove(order);
                }
            }
            partnerOrderHashMap.remove(partnerId);
        }

        if(deliveryPartnerHashMap.containsKey(partnerId)){
            deliveryPartnerHashMap.remove(partnerId);
        }
    }

    public void deleteOrderById(String orderId) {
        if(orderPartnerHashMap.containsKey(orderId)){
            String partnerId = orderPartnerHashMap.get(orderId);
            HashSet<String> orders = partnerOrderHashMap.get(partnerId);
            orders.remove(orderId);
            partnerOrderHashMap.put(partnerId, orders);

            //change order count of partner
            DeliveryPartner partner = deliveryPartnerHashMap.get(partnerId);
            partner.setNumberOfOrders(orders.size());
        }

        if(orderHashMap.containsKey(orderId)){
            orderHashMap.remove(orderId);
        }

    }
}
