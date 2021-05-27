package com.ljc.market.service.admin;

import com.ljc.market.bean.PageBean;
import com.ljc.market.dao.admin.DatabaseBakDao;
import com.ljc.market.entity.admin.DatabaseBak;
import com.ljc.market.util.StringUtil;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 数据库备份Service
 */
@Service
public class DatabaseBakService {

    @Autowired
    private DatabaseBakDao databaseBakDao;
    @Value("${ljc.database.backup.dir}")
    private String backupPath;
    @Value("${ljc.database.backup.username}")
    private String dbUsername;
    @Value("${ljc.database.backup.password}")
    private String dbPwd;
    @Value("${ljc.database.backup.database.name}")
    private String dbName;


    /**
     * 分页查找数据库备份记录
     * @param pageBean
     * @return
     */
    public PageBean<DatabaseBak> findList( PageBean<DatabaseBak> pageBean){
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
        Page<DatabaseBak> findAll = databaseBakDao.findAll(pageable);
        pageBean.setContent(findAll.getContent());
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }

    /**
     * 添加/修改数据库备份信息记录
     * @param databaseBak
     * @return
     */
    public DatabaseBak save(DatabaseBak databaseBak){
        return databaseBakDao.save(databaseBak);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public DatabaseBak find(Long id){
        return databaseBakDao.find(id);
    }

    /**
     * 根据id删除
     * @param id
     */
    public void delete(Long id){
        databaseBakDao.deleteById(id);
    }

    /**
     * 数据库备份添加
     * @return
     */
    public DatabaseBak add(){
        DatabaseBak databaseBak = null;
        File file = new File(backupPath);
        if (!file.exists()){
            file.mkdirs();
        }
        String fileName = dbName+"_"+ StringUtil.getFormatterDate(new Date(),"yyyyMMddHHmmss")+".sql";
        String cmd = "mysqldump -u"+dbUsername+" -p"+dbPwd+" "+dbName+" -r "+backupPath+fileName;
        try {
            Runtime.getRuntime().exec(cmd);
            databaseBak = new DatabaseBak();
            databaseBak.setFilename(fileName);
            databaseBak.setFilepath(backupPath);
            databaseBak = save(databaseBak);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return databaseBak;
    }



    public DatabaseBak restore(String id){
        DatabaseBak databaseBak = find(Long.valueOf(id));
        if (databaseBak!=null){
            String fileName = databaseBak.getFilename();
            String cmd = "mysql -u"+dbUsername+" -p"+dbPwd+" "+dbName+" < "+backupPath+fileName;
            File file = new File(databaseBak.getFilepath()+databaseBak.getFilename());
            if (!file.exists()){
                cmd = "mysql -u"+dbUsername+" -p"+dbPwd+" "+dbName+" < " + databaseBak.getFilepath()+databaseBak.getFilename();
            }
            String stmt1 = "mysqladmin -u"+dbUsername+" -p"+dbPwd+" create "+dbName;
            //打开cmd命令，‘/c命令运行之后关闭窗口’，运行的命令
            String[] cmds = { "cmd", "/c", cmd };
            try {
                Runtime.getRuntime().exec(stmt1);
                Runtime.getRuntime().exec(cmds);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return databaseBak;
    }


    /**
     * 备份总数
     * @return
     */
    public Long total(){
        return databaseBakDao.count();
    }



}
