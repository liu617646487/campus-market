package com.ljc.market.entity.common;

import com.ljc.market.annotation.ValidateEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


/**
 * 求购物品实体类
 */
@Entity
@Table(name = "home_wanted_goods")
@EntityListeners(AuditingEntityListener.class)
public class WantedGoods extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;//发布人

    @ValidateEntity(required = true, requiredLength = true, minLength = 1, maxLength = 32, errorRequiredMsg = "物品名称不能为空！", errorMinLengthMsg = "物品名称长度必须大于1！", errorMaxLengthMsg = "物品名称长度必须小于18！")
    @Column(name = "name", nullable = false, length = 18)
    private String name;//物品名称

    @ValidateEntity(required = true, errorRequiredMsg = "物品期望价格不能为空！",requiredMinValue = true,errorMinValueMsg = "物品期望价格必须大于0")
    @Column(name = "sell_price", nullable = false, length = 8)
    private Float sellPrice;//物品期望价格

    @ValidateEntity(required = true,requiredLength = true, minLength = 1, maxLength = 100, errorRequiredMsg = "物品交易地点不能为空！", errorMinLengthMsg = "物品交易地点长度必须大于1！", errorMaxLengthMsg = "物品交易地点长度必须小于128！")
    @Column(name = "trade_place",nullable = false, length = 128)
    private String tradePlace;//物品交易地点

    @ValidateEntity(required = true,requiredLength = true, minLength = 1, maxLength = 1000, errorRequiredMsg = "物品描述不能为空！", errorMinLengthMsg = "物品名称长度必须大于1！", errorMaxLengthMsg = "物品名称长度必须小于1024！")
    @Column(name = "content",nullable = false, length = 1024)
    private String content;//物品详情描述

    @Column(name = "view_number",nullable = false,length = 8)
    private Integer viewNumber = 0;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getTradePlace() {
        return tradePlace;
    }

    public void setTradePlace(String tradePlace) {
        this.tradePlace = tradePlace;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(Integer viewNumber) {
        this.viewNumber = viewNumber;
    }

    @Override
    public String toString() {
        return "WantedGoods{" +
                "student=" + student +
                ", name='" + name + '\'' +
                ", sellPrice=" + sellPrice +
                ", tradePlace='" + tradePlace + '\'' +
                ", content='" + content + '\'' +
                ", viewNumber=" + viewNumber +
                '}';
    }
}

