package com.lakshmi.orderManagementApplication.backendSystems.shippingService;

import com.lakshmi.orderManagementApplication.orderAcknowledgement.model.OrderDetail;
import jakarta.jms.*;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ShippingService {

    public static void main(String[] args) throws NamingException {

        InitialContext initialContext = new InitialContext();
        Queue requestQueue = (Queue) initialContext.lookup("queue/MyQueue");
        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
             JMSContext jmsContext = cf.createContext()) {
            JMSConsumer consumer = jmsContext.createConsumer(requestQueue);
            Message message = consumer.receive();
            OrderDetail order = message.getBody(OrderDetail.class);
            System.out.println("Order Received Successfully with details -> Order id: " + order.getOrderId() + " Customer Name: " + order.getCustomerName());
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

}
