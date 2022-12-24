package com.driver;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Repository
public class OrderRepository {

    private HashMap<String, Order> orderMapping;
    private HashMap<String, DeliveryPartner> delPartnerMapping;
    private HashMap<String, List<String>> delOrderMapping;
    private HashMap<String,Integer> orderDelMapping;

    public OrderRepository() {
        this.orderMapping = new HashMap<>();
        this.delPartnerMapping = new HashMap<>();
        this.delOrderMapping = new HashMap<>();
        this.orderDelMapping=new HashMap<>();
    }

    public void saveOrders(Order order) {
        orderMapping.put(order.getId(), order);
        orderDelMapping.put(order.getId(),order.getDeliveryTime());
    }
      public DeliveryPartner deliveryPartner;
    public void savedel(String deliveryPartnerId) {

        delPartnerMapping.put(deliveryPartnerId, deliveryPartner);
    }

    public void pairOrder(String order, String deliveryPartner) {
        if (orderMapping.containsKey(order) && delOrderMapping.containsKey(deliveryPartner)) {
            orderMapping.put(order, orderMapping.get(order));
            delPartnerMapping.put(deliveryPartner, delPartnerMapping.get(deliveryPartner));
            List<String> pair = new ArrayList<>();
            if (delOrderMapping.containsKey(deliveryPartner))
                pair = delOrderMapping.get(deliveryPartner);
            pair.add(order);
            delOrderMapping.put(deliveryPartner,pair);
        }
    }
    public Order findOrder(String order){
        return orderMapping.get(order);
    }
    public DeliveryPartner findDelPartner(String deliveryPartner){
        return delPartnerMapping.get(deliveryPartner);
    }
    public int findOrderCountName(String deliveryPartner) {
        List<String> orderName = new ArrayList<>();
        if (delOrderMapping.containsKey(deliveryPartner))
            orderName = delOrderMapping.get(deliveryPartner);
        return orderName.size();
    }
    public List<String> findOrderName(String deliveryPartner){
        List<String> orderNames=new ArrayList<>();
        if(delOrderMapping.containsKey(deliveryPartner))
            orderNames=delOrderMapping.get(deliveryPartner);
        return orderNames;
    }
    public List<String> findAllOrders(){
        List<String> allOrders=new ArrayList<>(orderMapping.keySet());
        return allOrders;
    }
    public int findAllUnOrder(){
        int count = 0;
        HashSet<String> hs = new HashSet<>();
        for(String key : delOrderMapping.keySet())
        {
            List<String> order_id = delOrderMapping.get(key);
            for(String s : order_id)
            {
                hs.add(s);
            }
        }

        for(String str : orderMapping.keySet())
        {
            if(hs.contains(str))
            {
                count++;
            }
        }
        return count;
    }
     public int getOrdersLeftAfterGivenTimeByPartnerId(String time,String partnerId){
        int count=0;
         int a = Integer.valueOf(time.substring(0,3));
         int b = Integer.valueOf((time.substring(4,time.length())));
         int t = (a*60) + b;
         List<String> list = delOrderMapping.get(partnerId);
         for(String str : list)
         {
             if(t< orderDelMapping.get(str))
             {
                 count++;
             }
         }
         return count;
     }
     public String getLastDeliveryTimeByPartner(String partnerId){
         String time = null;
         int max = 0;
         List<String> list = delOrderMapping.get(partnerId);
         for(String str : list)
         {
             if(max < orderDelMapping.get(str))
             {
                 max = orderDelMapping.get(str);
             }
         }
         int hour = max/60;
         int m = max%60;
         time = Integer.toString(hour) + Integer.toString(m);
         return time;
     }

    public void deletePartner(String deliveryPartner){
        List<String> delOrderNames=new ArrayList<>();
        if(delOrderMapping.containsKey(deliveryPartner)){

            delOrderMapping.remove(deliveryPartner);
        }
        if(delPartnerMapping.containsKey(deliveryPartner)){
            delPartnerMapping.remove(deliveryPartner);
        }
    }
    public void deleteAllData(String order) {
        for (String k : delOrderMapping.keySet()) {
            List<String> delOrder = delOrderMapping.get(k);
            for (String i :delOrder){
                if(i.equals(order)){
                    delOrder.remove(order);
                    delOrderMapping.remove(k);
                    delOrderMapping.put(k,delOrder);
                }
            }
        }
        orderMapping.remove(order);
    }
}


