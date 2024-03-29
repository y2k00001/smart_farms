/**
 * filename   :AttendanceServletInitializer.java
 * createtime :2023年5月4日 下午3:43:09
 * creator    :guoqing
 */
package com.neo.farms.main;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * web容器中进行部署
 * (抄RuoYiServletInitializer的，我暂时不知道有什么用呢)
 * @author guoqing
 * @createtime 2023年5月4日 下午3:43:09
 */
public class FarmsMainServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(FarmsMain.class);
    }
}
