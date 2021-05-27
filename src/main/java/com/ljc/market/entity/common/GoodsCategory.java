package com.ljc.market.entity.common;


import com.ljc.market.annotation.ValidateEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


/**
 * 物品分类实体类
 */
@Entity
@Table(name = "home_goods_category")
@EntityListeners(AuditingEntityListener.class)
public class GoodsCategory extends BaseEntity {

    private final static Long serialVersionUID = 1L;

    @ValidateEntity(required = true, requiredLength = true, minLength = 1, maxLength = 18, errorRequiredMsg = "菜单名称不能为空！", errorMinLengthMsg = "菜单名称长度必须大于1！", errorMaxLengthMsg = "菜单名称长度必须小于18！")
    @Column(name = "name", nullable = false, length = 18)
    private String name;//分类名称

    @ManyToOne
    @JoinColumn(name="parent_id")
    private GoodsCategory parent;//分类父分类

    @ValidateEntity(required = true, errorRequiredMsg = "菜单图标不能为空！")
    @Column(name = "icon", nullable = false, length = 64)
    private String icon;//分类图标

    @Column(name = "sort", nullable = false, length = 4)
    private Integer sort;//分类顺序，默认升序排列,默认是0

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GoodsCategory getParent() {
        return parent;
    }

    public void setParent(GoodsCategory parent) {
        this.parent = parent;
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

    @Override
    public String toString() {
        return "GoodsCategory{" +
                "name='" + name + '\'' +
                ", parent=" + parent +
                ", icon='" + icon + '\'' +
                ", sort=" + sort +
                '}';
    }
}
