package com.ljc.market.controller.admin;

import com.ljc.market.bean.CodeMsg;
import com.ljc.market.bean.PageBean;
import com.ljc.market.bean.Result;
import com.ljc.market.config.SiteConfig;
import com.ljc.market.entity.admin.DatabaseBak;
import com.ljc.market.service.admin.DatabaseBakService;
import com.ljc.market.service.admin.OperatorLogService;
import com.ljc.market.util.StringUtil;
import com.sun.prism.shader.Mask_TextureSuper_AlphaTest_Loader;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MethodInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 数据库备份管理控制器
 */
@Controller
@RequestMapping("admin/database_bak")
public class DatabaseBakController {

    @Autowired
    private SiteConfig siteConfig;

    @Autowired
    private OperatorLogService operatorLogService;

    @Autowired
    private DatabaseBakService databaseBakService;

    @Value("${ljc.database.backup.dir}")
    private String backupPath;



    private Logger log = LoggerFactory.getLogger(DatabaseBakController.class);

    @GetMapping("/list")
    public String list(Model model,PageBean<DatabaseBak> pageBean){
        model.addAttribute("siteName",siteConfig.getName());
        model.addAttribute("siteUrl",siteConfig.getUrl());
        model.addAttribute("title","数据库备份列表");
        model.addAttribute("pageBean",databaseBakService.findList(pageBean));
        return "admin/database_bak/list";
    }


    /**
     * 数据库备份添加
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result<Boolean> add(){
        DatabaseBak databaseBak = databaseBakService.add();
        log.info("数据库备份成功！");
        operatorLogService.add("数据库备份成功,备份文件信息"+databaseBak);
        return Result.success(true);
    }


    /**
     * 删除备份的记录及文件
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Result<Boolean> delete(@RequestParam("ids") String ids){
        String[] split = ids.split(",");
        for (String id:split){
            DatabaseBak databaseBak = databaseBakService.find(Long.valueOf(id));
            if (databaseBak!=null){
                databaseBakService.delete(Long.valueOf(id));
                File file = new File(databaseBak.getFilepath()+databaseBak.getFilename());
                if (!file.exists()){
                    //说明文件不存在，按照配置文件重新寻找文件
                    file = new File(backupPath+databaseBak.getFilename());
                }
                file.delete();
            }
        }
        return Result.success(true);
    }


    /**
     * 数据库备份还原
     * @param id
     * @return
     */
    @PostMapping("/restore")
    @ResponseBody
    public Result<Boolean> restore(@RequestParam("id") String id){
        DatabaseBak databaseBak = databaseBakService.restore(id);
        log.info("数据库还原成功！");
        operatorLogService.add("数据库还原成功,还原文件信息"+databaseBak);
        return Result.success(true);
    }


}
