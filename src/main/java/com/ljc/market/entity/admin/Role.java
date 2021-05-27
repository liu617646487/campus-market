package com.ljc.market.entity.admin;

import com.ljc.market.annotation.ValidateEntity;
import com.ljc.market.entity.common.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;


/**
 * 后台角色实体类
 */
@Entity
@Table(name = "admin_role")
@EntityListeners(AuditingEntityListener.class)
public class Role extends BaseEntity {

    private final static Long serialVersionUID = 1L;

    public static final Integer ADMIN_ROLE_STATUS_ENABLE = 1;//角色状态可用
    public static final Integer ADMIN_ROLE_STATUS_UNABLE = 0;//角色状态不可用


    @ValidateEntity(required = true,requiredLength = true,minLength = 1,maxLength = 18,errorRequiredMsg = "角色名称不能为空！",errorMinLengthMsg = "角色名称长度必须大于1！",errorMaxLengthMsg = "角色名称长度必须小于18！")
    @Column(name = "name",nullable = false,length = 18)
    private String name;//角色名称

    @ValidateEntity(required = false)
    @ManyToMany
    @JoinColumn(name = "authorities")
    private List<Menu> authorities;//角色所对应的权限（菜单）列表

    @ValidateEntity(required = false)
    @Column(name = "status",length = 1)
    private Integer status = ADMIN_ROLE_STATUS_ENABLE;//角色状态

    @ValidateEntity(required = false)
    @Column(name = "remark",nullable = false,length = 128)
    private String remark;//角色备注

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Menu> authorities) {
        this.authorities = authorities;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", authorities=" + authorities +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                '}';
    }
}
