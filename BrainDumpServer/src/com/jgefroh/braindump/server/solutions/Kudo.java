package com.jgefroh.braindump.server.solutions;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.jgefroh.braindump.server.security.User;


@Entity
public class Kudo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private User giver;
}
