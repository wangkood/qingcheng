package cn.wangxing.qing.service;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*@Component*/
public class GoodsTask {



    @Scheduled(cron="0/10 * * * * *")
    public void sayHello(){
        System.out.println(" hello ");
    }

}
