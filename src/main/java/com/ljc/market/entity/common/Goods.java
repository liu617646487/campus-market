package com.ljc.market.entity.common;

import com.ljc.market.annotation.ValidateEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


/**
 * 物品物品实体类
 */
@Entity
@Table(name = "home_goods")
@EntityListeners(AuditingEntityListener.class)
public class Goods extends BaseEntity {

    private final static Long serialVersionUID = 1L;

    public static final Integer GOODS_STATUS_UP = 1;//上架
    public static final Integer GOODS_STATUS_DOWN = 2;//下架
    public static final Integer GOODS_STATUS_SOLD = 3;//已售出

    public static final Integer GOODS_FLAG_ON = 1;//擦亮
    public static final Integer GOODS_FLAG_OFF = 0;//不擦亮

    public static final Integer GOODS_RECOMMEND_ON = 1;//推荐
    public static final Integer GOODS_RECOMMEND_OFF = 0;//不推荐

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;//发布人

    @ValidateEntity(required = true, requiredLength = true, minLength = 1, maxLength = 32, errorRequiredMsg = "物品名称不能为空！", errorMinLengthMsg = "物品名称长度必须大于1！", errorMaxLengthMsg = "物品名称长度必须小于18！")
    @Column(name = "name", nullable = false, length = 18)
    private String name;//物品名称

    @ValidateEntity(required = true, errorRequiredMsg = "物品分类不能为空！")
    @ManyToOne
    @JoinColumn(name="goods_category_id")
    private GoodsCategory goodsCategory;//物品分类

    @ValidateEntity(required = true, errorRequiredMsg = "物品购买价格不能为空！",requiredMinValue = true,errorMinValueMsg = "物品购买价格必须大于0")
    @Column(name = "buy_price", nullable = false, length = 8)
    private Float buyPrice;//物品购买价格

    @ValidateEntity(required = true, errorRequiredMsg = "物品出售价格不能为空！",requiredMinValue = true,errorMinValueMsg = "物品出售价格必须大于0")
    @Column(name = "sell_price", nullable = false, length = 8)
    private Float sellPrice;//物品出售价格

    @ValidateEntity(required = true, errorRequiredMsg = "物品图片不能为空！")
    @Column(name = "photo", nullable = false, length = 64)
    private String photo;//物品图片

    @Column(name = "status", nullable = false, length = 1)
    private Integer status = GOODS_STATUS_UP;//物品状态

    @Column(name = "flag", nullable = false, length = 1)
    private Integer flag = GOODS_FLAG_ON;//是否擦亮

    @Column(name = "recommend", nullable = false, length = 1)
    private Integer recommend = GOODS_RECOMMEND_OFF;//是否推荐

    @ValidateEntity(required = true,requiredLength = true, minLength = 1, maxLength = 1000, errorRequiredMsg = "物品描述不能为空！", errorMinLengthMsg = "物品名称长度必须大于1！", errorMaxLengthMsg = "物品名称长度必须小于1024！")
    @Column(name = "content",nullable = false, length = 1024)
    private String content;//物品详情描述

    @Column(name = "view_number",nullable = false,length = 8)
    private Integer viewNumber = 0;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GoodsCategory getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(GoodsCategory goodsCategory) {
        this.goodsCategory = goodsCategory;
    }

    public Float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(Integer viewNumber) {
        this.viewNumber = viewNumber;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "student=" + student +
                ", name='" + name + '\'' +
                ", goodsCategory=" + goodsCategory +
                ", buyPrice=" + buyPrice +
                ", sellPrice=" + sellPrice +
                ", photo='" + photo + '\'' +
                ", status=" + status +
                ", flag=" + flag +
                ", recommend=" + recommend +
                ", content='" + content + '\'' +
                ", viewNumber=" + viewNumber +
                '}';
    }
}

