package com.example.demo.component;

import com.example.demo.domain.Todo;
import com.example.demo.util.JAXBUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.Date;

@Component
@Slf4j
public class SubscribeCom {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private JAXBUtil jaxbUtil;

    @JmsListener(destination = "${destination.topic.client}", containerFactory = "topicConnectionFactory")
    public void readMessage(String message) {
        log.info("receive message: {}", message);
    }

    @JmsListener(destination = "${destination.topic.server}")
    public void server(TextMessage message) throws JMSException {
        String strMsg = message.getText();

        log.info("receive message: {}", strMsg);

        Todo todo = jaxbUtil.getObject(strMsg, Todo.class);
        todo.setText(todo.getText() + " 잘 받았다 돌려줌.");
        String sendMsg = jaxbUtil.getXmlMessage(todo);

        log.info("sendmessage: {}", sendMsg);

        jmsTemplate.send(message.getJMSReplyTo(), s -> s.createTextMessage(sendMsg));
    }
}
