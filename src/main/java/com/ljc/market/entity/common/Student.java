package com.ljc.market.entity.common;


import com.ljc.market.annotation.ValidateEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


/**
 * 物品分类实体类
 */
@Entity
@Table(name = "home_student")
@EntityListeners(AuditingEntityListener.class)
public class Student extends BaseEntity {

    private static final Long serialVersionUID = 1L;
    public static final int STUDENT_STATUS_ENABLE = 1;//状态可用
    public static  final int STUDENT_STATUS_UNABLE = 0;//状态不可用

    @ValidateEntity(required = true, requiredLength = true, minLength = 6, maxLength = 18, errorRequiredMsg = "学号不能为空！", errorMinLengthMsg = "学号长度必须大于6！", errorMaxLengthMsg = "学号长度必须小于18！")
    @Column(name = "sno", nullable = false, length = 18,unique = true)
    private String sno;//学号

    @ValidateEntity(required = true, requiredLength = true, minLength = 6, maxLength = 18, errorRequiredMsg = "密码不能为空！", errorMinLengthMsg = "密码长度必须大于6！", errorMaxLengthMsg = "密码长度必须小于18！")
    @Column(name = "password", nullable = false, length = 18)
    private String password;//密码

    @ValidateEntity(required = false)
    @Column(name = "head_pic", length = 128)
    private String headPic;//头像

    @ValidateEntity(required = false)
    @Column(name = "nickname", length = 32)
    private String nickname;//昵称

    @ValidateEntity(required = false)
    @Column(name = "mobile", length = 18)
    private String mobile;//手机号

    @ValidateEntity(required = true,errorRequiredMsg = "qq不能为空",minLength = 5,maxLength = 12,errorMinLengthMsg = "qq长度需大于5",errorMaxLengthMsg = "qq长须需小于12")
    @Column(name = "qq", nullable = false, length = 18)
    private String qq;//qq号

    @ValidateEntity(required = false)
    @Column(name = "school", length = 18)
    private String school;//学校

    @ValidateEntity(required = false)
    @Column(name = "academy", length = 18)
    private String academy;//学院

    @ValidateEntity(required = false)
    @Column(name = "grade", length = 18)
    private String grade;//年级

    @ValidateEntity(required = false)
    @Column(name = "status", nullable = false, length = 1)
    private int status = STUDENT_STATUS_ENABLE;//状态

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno='" + sno + '\'' +
                ", password='" + password + '\'' +
                ", headPic='" + headPic + '\'' +
                ", nickname='" + nickname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", qq='" + qq + '\'' +
                ", school='" + school + '\'' +
                ", academy='" + academy + '\'' +
                ", grade='" + grade + '\'' +
                ", status=" + status +
                '}';
    }
}
