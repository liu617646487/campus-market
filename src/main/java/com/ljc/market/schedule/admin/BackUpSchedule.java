package com.ljc.market.schedule.admin;

import com.ljc.market.entity.admin.DatabaseBak;
import com.ljc.market.service.admin.DatabaseBakService;
import com.ljc.market.service.admin.OperatorLogService;
import com.ljc.market.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * 备份数据库定时器
 */

@Configuration
@EnableScheduling
public class BackUpSchedule {

    @Autowired
    private OperatorLogService operatorLogService;
    @Autowired
    private DatabaseBakService databaseBakService;

    private Logger log = LoggerFactory.getLogger(BackUpSchedule.class);

    //延迟十秒后执行，之后每隔五秒执行一次
    //@Scheduled(initialDelay = 10000,fixedRate = 5000)
    @Scheduled(cron = "0 0 0 1 * ?")//每月一号的0点备份
    public void backUpDatabase(){
        log.info("定时器开始执行！"+ StringUtil.getFormatterDate(new Date(),"yyyy-MM-dd HH:mm:ss SSS"));
        DatabaseBak add = databaseBakService.add();
        operatorLogService.add("自动定时任务，数据库备份成功！"+add);

    }

}
