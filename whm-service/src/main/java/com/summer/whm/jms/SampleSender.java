package com.summer.whm.jms;
//package com.suning.sample.jms;
//import javax.jms.ConnectionFactory;
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.Session;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.core.MessageCreator;
//
//public class SampleSender {
//
//    @Autowired JmsTemplate jmsTemplate;
//
//    public void setConnectionFactory(ConnectionFactory cf) {
//        this.jmsTemplate = new JmsTemplate(cf);
//    }
//
//
//    public void simpleSend(final String message) {
//        this.jmsTemplate.send(new MessageCreator() {
//            public Message createMessage(Session session) throws JMSException {
//              return session.createTextMessage(message);
//            }
//        });
//    }
//}