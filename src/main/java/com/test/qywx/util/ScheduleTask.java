package com.test.qywx.util;

import com.test.qywx.service.UserAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@EnableScheduling
public class ScheduleTask {
    @Autowired
    private UserAccessService userAccessService;

    @Autowired


    @Scheduled(fixedDelay = 1000*60)
    @Transactional
    public void updateMysql(){
//        Date date=new Date();
//        userAccessService.deleteTimeOutData(date);
//        System.out.println("时间"+new Date());
    }
}
