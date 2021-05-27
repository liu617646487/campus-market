package com.ljc.market.entity.admin;

/**
 * 后台用户实体类
 */

import com.ljc.market.annotation.ValidateEntity;
import com.ljc.market.entity.common.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "admin_user")
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity implements Serializable {

    private final static Long serialVersionUID = 1L;

    public final static Integer USER_GANDER_MAN = 1;//性别男
    public final static Integer USER_GANDER_WOMAN = 2;//性别女
    public final static Integer USER_GANDER_UNKNOWN = 0;//性别未知

    public static final Integer ADMIN_USER_STATUS_ENABLE = 1;//用户状态可用
    public static final Integer ADMIN_USER_STATUS_UNABLE = 0;//用户状态不可用

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;//用户对应的角色

    @Column(name = "username",nullable = false,length = 18,unique = true)
    @ValidateEntity(required = true,requiredLength = true,minLength = 4,maxLength = 18,errorRequiredMsg = "用户名不能为空!",errorMinLengthMsg = "用户名长度需大于4！",errorMaxLengthMsg = "用户名长度需小于18！")
    private String username;//用户名

    @Column(name = "password",nullable = false,length = 32)
    @ValidateEntity(required = true,requiredLength = true,minLength = 4,maxLength = 32,errorRequiredMsg = "密码不能为空!",errorMinLengthMsg = "密码长度需大于4！",errorMaxLengthMsg = "密码长度需小于32！")
    private String password;//密码

    @ValidateEntity(required = false)
    @Column(name = "status",length = 1)
    private Integer status = ADMIN_USER_STATUS_ENABLE;//用户状态,默认可用

    @ValidateEntity(required = false)
    @Column(name = "gander",length = 1)
    private Integer gander = USER_GANDER_UNKNOWN;//用户性别

    @ValidateEntity(required = false)
    @Column(name = "head_pic",length = 128)
    private String headPic;//用户头像

    @ValidateEntity(required = false)
    @Column(name = "mobile",length = 12)
    private String mobile;//用户手机号

    @ValidateEntity(required = false)
    @Column(name = "email",length = 32)
    private String email;//用户邮箱

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGander() {
        return gander;
    }

    public void setGander(Integer gander) {
        this.gander = gander;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "role=" + role +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", gander=" + gander +
                ", headPic='" + headPic + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
