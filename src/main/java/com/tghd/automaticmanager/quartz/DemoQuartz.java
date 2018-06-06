package com.tghd.automaticmanager.quartz;

import com.tghd.automaticmanager.utils.DateFormatUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DemoQuartz {
    private int i=0;
    @Scheduled(fixedDelay = 5000)
    public void test(){
        System.out.println("第"+(++i)+"次执行"+ DateFormatUtil.nowDateTimeToString(DateFormatUtil.DATA_TIME));
    }
}
