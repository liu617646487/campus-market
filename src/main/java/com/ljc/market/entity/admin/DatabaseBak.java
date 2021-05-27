package com.ljc.market.entity.admin;

import com.ljc.market.entity.common.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;


/**
 * 数据库备份实体类
 */
@Entity
@Table(name = "admin_database_bak")
@EntityListeners(AuditingEntityListener.class)
public class DatabaseBak extends BaseEntity implements Serializable {

    private final static Long serialVersionUID = 1L;

    @Column(name = "filename", nullable = false, length = 32)
    private String filename;//备份的时间名

    @Column(name = "filepath", nullable = false, length = 128)
    private String filepath;//备份的问价路径

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public String toString() {
        return "DatabaseBak{" +
                "filename='" + filename + '\'' +
                ", filepath='" + filepath + '\'' +
                '}';
    }
}
