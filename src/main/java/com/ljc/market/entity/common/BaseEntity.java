package com.ljc.market.entity.common;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

    @Column(name = "id",nullable = false,length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;//唯一id

    @Column(name = "create_time",nullable = false)
    @CreatedDate
    private Date createTime;//创建时间

    @Column(name = "update_time",nullable = false)
    @LastModifiedDate
    private Date updateTime;//更新时间

    public BaseEntity() {
    }

    public BaseEntity(Long id,Date createTime,Date updateTime) {
        this.id=id;
        this.createTime=createTime;
        this.updateTime=createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
