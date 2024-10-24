package com.bootcamp.bc_forum.appConfig;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerConfig {

    @Scheduled (fixedDelay = 5000) // 5000ms = 5 seconds
    public void sayHello(){
        System.out.println("Hello World! Task A:  " + System.currentTimeMillis());
    }

    // fixedRate
    @Scheduled (fixedRate = 5000)
    public void sayBye() throws Exception{
        System.out.println("Task B Start:  " + System.currentTimeMillis());
        Thread.sleep(6000); // try 扮個task run左3秒
        System.out.println("Task B End:  " + System.currentTimeMillis());
    }
//!!! @Scheduled 係per method的, 以上個task, run 6s 但set 左5s run 1次
//     唔會未完就run, 會6秒完先即run. 否則時間愈耐會爆memory的 
    
}
