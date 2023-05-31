package com.lakshmi.orderManagementApplication.orderAcknowledgement.service;

import com.lakshmi.orderManagementApplication.orderAcknowledgement.model.OrderDetail;
import jakarta.jms.*;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.InitialContext;
import javax.naming.NamingException;

@Service
@Transactional
public class OrderAcknowledgmentService {
    public String createOrder(OrderDetail order) throws NamingException,JMSException {

        InitialContext context = new InitialContext();
        Queue queue = (Queue) context.lookup("queue/MyQueue");
        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
             JMSContext jmsContext = cf.createContext()) {
            JMSProducer producer = jmsContext.createProducer();
            ObjectMessage objectMessage = jmsContext.createObjectMessage();
            objectMessage.setObject(order);
            producer.send(queue,objectMessage);
            System.out.println("Message sent successfully");
        }
        return "Order Placed Successfully";
    }
}
