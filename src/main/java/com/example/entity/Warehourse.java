package com.example.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * Company: www.leadmap.net
 * Description:
 *
 * @author: 49022
 * @Date: 2019/1/14 16:33
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "warehourse")
public class Warehourse {
    ////@Id 标注用于声明一个实体类的属性映射为数据库的主键列
    @Id
    //@GeneratedValue 用于标注主键的生成策略，通过strategy 属性指定
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //@Column用来标识实体类中属性与数据表中字段的对应关系
    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "unitprice")
    private Float unitprice;

    @Column(name = "totalprice")
    private Float totalprice;

    @Column(name = "vender")
    private String vender;

    @Temporal(TemporalType.TIMESTAMP)
    private Date producedata;

    @Column(name = "buyperson")
    private String buyperson;

    @Column(name = "color")
    private String color;

    @Column(name = "number")
    private String number;

    @Temporal(TemporalType.TIMESTAMP)
    private Date enterhoursetime;

    @Column(name = "enterhourseperson")
    private String enterhourseperson;

    @Column(name = "hoursenumber")
    private Integer hoursenumber;

    //必须要有构造函数
    public Warehourse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Float unitprice) {
        this.unitprice = unitprice;
    }

    public Float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Float totalprice) {
        this.totalprice = totalprice;
    }

    public String getVender() {
        return vender;
    }

    public void setVender(String vender) {
        this.vender = vender;
    }

    public Date getProducedata() {
        return producedata;
    }

    public void setProducedata(Date producedata) {
        this.producedata = producedata;
    }

    public String getBuyperson() {
        return buyperson;
    }

    public void setBuyperson(String buyperson) {
        this.buyperson = buyperson;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getEnterhoursetime() {
        return enterhoursetime;
    }

    public void setEnterhoursetime(Date enterhoursetime) {
        this.enterhoursetime = enterhoursetime;
    }

    public String getEnterhourseperson() {
        return enterhourseperson;
    }

    public void setEnterhourseperson(String enterhourseperson) {
        this.enterhourseperson = enterhourseperson;
    }

    public Integer getHoursenumber() {
        return hoursenumber;
    }

    public void setHoursenumber(Integer hoursenumber) {
        this.hoursenumber = hoursenumber;
    }
}
