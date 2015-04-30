package com.sinosoft.gypsymoth.pojo;
// default package

import java.math.BigDecimal;


/**
 * AbstractExample entity provides the base persistence definition of the Example entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractExample  implements java.io.Serializable {


    // Fields    

     private String id;
     private String name;
     private Integer age;
     private String password;


    // Constructors

    /** default constructor */
    public AbstractExample() {
    }

    
    /** full constructor */
    public AbstractExample(String name, Integer age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
   








}