package com.ljc.market.entity.admin;

/**
 * 后台操作日志表
 */


import com.ljc.market.entity.common.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "admin_operator_log")
@EntityListeners(AuditingEntityListener.class)
public class OperatorLog extends BaseEntity implements Serializable {

    private final static Long serialVersionUID = 1L;

    @Column(name = "operator",nullable = false,length = 18)
    private String operator;//操作员

    @Column(name = "content",nullable = false,length = 1024)
    private String content;//操作内容

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
