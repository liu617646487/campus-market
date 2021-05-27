package com.ljc.market.entity.common;

import com.ljc.market.annotation.ValidateEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


/**
 * 举报物品实体类
 */
@Entity
@Table(name = "home_report_goods")
@EntityListeners(AuditingEntityListener.class)
public class ReportGoods extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;//所属学生

    @ManyToOne
    @JoinColumn(name="goods_id")
    private Goods goods;//举报的物品

    @ValidateEntity(required = true,requiredLength = true, minLength = 1, maxLength = 1000, errorRequiredMsg = "物品描述不能为空！", errorMinLengthMsg = "物品名称长度必须大于1！", errorMaxLengthMsg = "物品名称长度必须小于1024！")
    @Column(name = "content",nullable = false, length = 1024)
    private String content;//举报原因

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ReportGoods{" +
                "student=" + student +
                ", goods=" + goods +
                ", content='" + content + '\'' +
                '}';
    }
}

