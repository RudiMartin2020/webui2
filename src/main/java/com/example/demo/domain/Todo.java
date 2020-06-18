package com.example.demo.domain;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.Date;

@Data
@XmlRootElement(name = "todo")
@XmlAccessorType(XmlAccessType.NONE)
public class Todo {
    @XmlAttribute
    private Long id;
    @XmlElement
    private String text;
    private Date createAt;
    private Date updateAt;
}
