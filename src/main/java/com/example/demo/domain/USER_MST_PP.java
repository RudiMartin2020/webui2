package com.example.demo.domain;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.Date;

@Data
@XmlRootElement(name = "user_mst_pp")
@XmlAccessorType(XmlAccessType.NONE)
public class USER_MST_PP {
    @XmlAttribute
    private String user_id;
    @XmlElement
    private String user_name;
    private String password;
    private String description;
    private String create_by;
    private Date create_dtts;
    private String last_update_by;
    private Date last_update_dtts;
    private String emailaddress;
    private Date password_ch_dtts;
    private Long password_fail_count;
    private String lock_yn;
    private Long location_rawid;
    private Long area_rawid;
    private String pager_no;
    private String phone_no;
    private String department;
    private String title;
    private String responsibilites;
    private String search_config_xml;
    private Long area_node_rawid;
    private String area_node_rawids;
    private String department_cd;
}