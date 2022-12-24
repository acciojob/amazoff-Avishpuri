package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order){
        orderRepository.saveOrders(order);
    }
    public void addPartner(String deliveryPartnerId){
        orderRepository.savedel(deliveryPartnerId);
    }
    public void addOrderPartnerPair(String order,String deliveryPartner){
        orderRepository.pairOrder(order,deliveryPartner);
    }
    public Order getOrderById(String order){
        return orderRepository.findOrder(order);
    }
    public DeliveryPartner getPartnerById(String deliveryPartner){
        return orderRepository.findDelPartner(deliveryPartner);
    }
    public int getOrderCountByPartnerId(String deliveryPartner){
        return orderRepository.findOrderCountName(deliveryPartner);
    }
    public List<String> getOrderByPartnerId(String deliveryPartner){
        return orderRepository.findOrderName(deliveryPartner);
    }
    public List<String> getAllOrders(){
        return orderRepository.findAllOrders();
    }
    public int getCountOfUnassignedOrders(){return orderRepository.findAllUnOrder();}

    public int getOrdersLeftAfterGivenTimeByPartnerId(String time,String partnerId){
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);
    }

    public String getLastDeliveryTimeByPartnerID(String partnerId){
       return orderRepository.getLastDeliveryTimeByPartner(partnerId);
    }
    public void deletePartnerById(String deliveryPartner){
        orderRepository.deletePartner(deliveryPartner);
    }
    public void deleteOrderById(String order){orderRepository.deleteAllData(order);
    }


}
