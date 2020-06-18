package com.example.demo.component;

import com.example.demo.domain.Todo;
import com.example.demo.util.JAXBUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
@Slf4j
public class PublishCom {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private JAXBUtil jaxbUtil;

    @Value("${destination.topic.server}")
    private String topic;

    public Todo sendWait(Todo todo) throws JMSException {
        String message = jaxbUtil.getXmlMessage(todo);
        log.info("send message: {}", message);
        TextMessage receive = (TextMessage) jmsTemplate.sendAndReceive(topic, s -> s.createTextMessage(message));
        assert receive != null;
        log.info("receive message: {}", receive.getText());
        return jaxbUtil.getObject(receive.getText(), Todo.class);
    }
}
