package com.example.demo.domain;


import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.Date;

@Data
public class RequestMsg {
    private String R_URL;
    private String R_TYPE;
    private String R_SUBJECT;
    private String R_MSG;
    private String R_TIMEOUT;
}
