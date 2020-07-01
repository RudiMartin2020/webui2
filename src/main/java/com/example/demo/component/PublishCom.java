package com.example.demo.component;

import com.example.demo.domain.Todo;
import com.example.demo.util.JAXBUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import com.example.demo.domain.RequestMsg;


import javax.jms.*;
import java.util.UUID;


@Component
@Slf4j
public class PublishCom {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private JAXBUtil jaxbUtil;

    @Value("${destination.topic.server}")
    private String queue;

    public Todo sendWait(Todo todo) throws JMSException {
        //String message = jaxbUtil.getXmlMessage(todo);
        String message = "<service id=\"RMS\" _pid=\"\" user=\"SA\" target=\"TCDYN01\" source=\"PeakPerformance\" tid=\"\"><method id=\"QUERYRECIPE\"><recipe eqpid=\"TCDYN01\" moduleid=\"SK/siltron/WF7/Cleaning:TCDYN01\" modulename=\"TCDYN01\" moduletype=\"\" /></method></service>";
        log.info("send message: {}", message);
        jmsTemplate.setReceiveTimeout(20000);
        TextMessage receive = (TextMessage) jmsTemplate.sendAndReceive(queue, s -> s.createTextMessage(message));
        assert receive != null;
        log.info("receive message: {}", receive.getText());
        return jaxbUtil.getObject(receive.getText(), Todo.class);
       }

       /**
    public String sendWait2(Todo todo) throws JMSException {
        //String message = jaxbUtil.getXmlMessage(todo);
        String message = "<service id=\"RMS\" _pid=\"\" user=\"SA\" target=\"TCDYN01\" source=\"PeakPerformance\" tid=\"\"><method id=\"QUERYRECIPE\"><recipe eqpid=\"TCDYN01\" moduleid=\"SK/siltron/WF7/Cleaning:TCDYN01\" modulename=\"TCDYN01\" moduletype=\"\" /></method></service>";
        log.info("send message: {}", message);
        TextMessage receive = (TextMessage) jmsTemplate.sendAndReceive(queue, s -> s.createTextMessage(message));
        //jmsTemplate.convertAndSend(queue,message);
        assert receive != null;
        log.info("test receive message: {}", receive.getText());
        return receive.getText();
    } */

       /**
    public void  sendWait3() throws JMSException {
        //String message = jaxbUtil.getXmlMessage(todo);
        String sendmsg = "<service id=\"RMS\" _pid=\"\" user=\"SA\" target=\"TCDYN01\" source=\"PeakPerformance\" tid=\"\"><method id=\"QUERYRECIPE\"><recipe eqpid=\"TCDYN01\" moduleid=\"SK/siltron/WF7/Cleaning:TCDYN01\" modulename=\"TCDYN01\" moduletype=\"\" /></method></service>";
        log.info("send message: {}", sendmsg);
        TextMessage receive = (TextMessage) jmsTemplate.sendAndReceive(queue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                String msgId = UUID.randomUUID().toString();
                jmsTemplate.setReceiveTimeout(20000);

                BytesMessage message = session.createBytesMessage();

                message.writeBytes(sendmsg.getBytes());
                // message.setStringProperty(String s , "tcp://10.150.2.164:61616");
                //message.setStringProperty(ISapProducer.ORIGIN_FILE_NAME, name);
                //message.setStringProperty(ISapProducer.MESSAGE_ID, msgId);
                //message.setStringProperty(ISapProducer.ARCHIVE_PATH, archivePath);

                message.setJMSReplyTo(session.createTemporaryQueue());
                message.setJMSCorrelationID(msgId);

                return message;
            }
        });
        //assert receive != null;
        log.info("receive message: {}", receive.getText());
        //return jaxbUtil.getObject(receive.getText(), Todo.class);
     //return "TEST";
    } */
}
