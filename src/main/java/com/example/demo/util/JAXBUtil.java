package com.example.demo.util;

import com.example.demo.domain.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

@Component
@Slf4j
public class JAXBUtil {
    public String getXmlMessage(Todo todo) {
        StringWriter sw = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Todo.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(todo, sw);
        } catch (JAXBException e) {
            log.error(e.getMessage());
        }
        return sw.toString();
    }

    public Todo getObject(String strMsg, Class<Todo> todoClass) {
        Todo todo = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(todoClass);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(strMsg);
            todo = (Todo) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            log.error(e.getMessage());
        }
        return todo;
    }
}
