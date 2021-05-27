package com.ljc.market.entity.admin;

import com.ljc.market.annotation.ValidateEntity;
import com.ljc.market.entity.common.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


/**
 * 后台菜单实体类
 */
@Entity
@Table(name = "admin_menu")
@EntityListeners(AuditingEntityListener.class)
public class Menu extends BaseEntity {

    private final static Long serialVersionUID = 1L;

    @ValidateEntity(required = true,requiredLength = true,minLength = 1,maxLength = 18,errorRequiredMsg = "菜单名称不能为空！",errorMinLengthMsg = "菜单名称长度必须大于1！",errorMaxLengthMsg = "菜单名称长度必须小于18！")
    @Column(name = "name",nullable = false,length = 18)
    private String name;//菜单名称

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Menu parent;//菜单父分类

    @ValidateEntity(required = false)
    @Column(name = "url",length = 128)
    private String url;//菜单url

    @ValidateEntity(required = true,errorRequiredMsg = "菜单图标不能为空！")
    @Column(name = "icon",nullable = false,length = 64)
    private String icon;//菜单图标

    @Column(name = "sort",nullable = false, length = 4)
    private Integer sort;//菜单顺序，默认升序排列,默认是0

    @Column(name = "is_button",nullable = false)
    private Boolean isbutton = false;

    @Column(name = "is_show",nullable = false)
    private Boolean isshow = true;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getIsbutton() {
        return isbutton;
    }

    public void setIsbutton(Boolean isbutton) {
        this.isbutton = isbutton;
    }

    public Boolean getIsshow() {
        return isshow;
    }

    public void setIsshow(Boolean isshow) {
        this.isshow = isshow;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", parent=" + parent +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", sort=" + sort +
                ", isbutton=" + isbutton +
                ", isshow=" + isshow +
                '}';
    }
}
