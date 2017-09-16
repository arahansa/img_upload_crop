package com.arahansa.cropimageupload.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SampleDomain {

    @Id
    @GeneratedValue
    Long id;

    String msg;


}
