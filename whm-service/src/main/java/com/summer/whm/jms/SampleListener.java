package com.summer.whm.jms;
//package com.suning.sample.jms;
//
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.MessageListener;
//import javax.jms.TextMessage;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class SampleListener implements MessageListener {
//	Logger log = LoggerFactory.getLogger(this.getClass());
//    public void onMessage(Message message) {
//        if (message instanceof TextMessage) {
//            try {
//                log.info(((TextMessage) message).getText());
//            }
//            catch (JMSException ex) {
//                throw new RuntimeException(ex);
//            }
//        }
//        else {
//            throw new IllegalArgumentException("Message must be of type TextMessage");
//        }
//    }
//}