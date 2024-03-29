package com.neo.farms.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 * 注意，要把 com.ruoyi.framework.config.ApplicationConfig 类前面的注解改为：
 * @MapperScan(basePackages={"com.ruoyi.**.mapper","com.runjian.wlsq.attendance.**.mapper"})
 */
@SpringBootApplication(scanBasePackages = {"com.neo.*"},exclude = { DataSourceAutoConfiguration.class })
public class FarmsMain
{
    public static void main( String[] args )
    {
    	SpringApplication.run(FarmsMain.class, args);
    	System.out.println("\n(♥◠‿◠)ﾉﾞ       >>>>> 智慧农园系统启动成功 >>>>     ლ(´ڡ`ლ)ﾞ  \n");
    }
}
