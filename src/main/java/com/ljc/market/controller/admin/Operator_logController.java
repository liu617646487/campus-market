package com.ljc.market.controller.admin;

import com.ljc.market.bean.PageBean;
import com.ljc.market.bean.Result;
import com.ljc.market.config.SiteConfig;
import com.ljc.market.entity.admin.OperatorLog;
import com.ljc.market.entity.admin.Role;
import com.ljc.market.service.admin.OperatorLogService;
import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin/operator_log")
public class Operator_logController {

    @Autowired
    private SiteConfig siteConfig;

    @Autowired
    private OperatorLogService operatorLogService;

    private Logger log = LoggerFactory.getLogger(Operator_logController.class);

    @GetMapping("/list")
    public String list(Model model, OperatorLog operatorLog, PageBean<OperatorLog> pageBean){
        model.addAttribute("siteName",siteConfig.getName());
        model.addAttribute("siteUrl",siteConfig.getUrl());
        model.addAttribute("title","日志列表");
        model.addAttribute("operator",operatorLog.getOperator());
        model.addAttribute("pageBean",operatorLogService.findList(operatorLog,pageBean));
        return "admin/operator_log/list";
    }

    @PostMapping("/delete")
    @ResponseBody
    public Result<Boolean> delete(@RequestParam(name = "ids" ,required = true)String ids){
        if (!StringUtils.isEmpty(ids)){
            String[] split = ids.split(",");
            for (String id:split){
                operatorLogService.deleteById(Long.valueOf(id));
            }
        }
        return Result.success(true);
    }

    @PostMapping("/deleteAll")
    @ResponseBody
    public Result<Boolean> deleteAll(){
        operatorLogService.deleteAll();
        return Result.success(true);
    }

}
