package com.connext.wms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Marcus @Date: 2018/12/21 10:18 @Version 1.0
 */
@SpringBootApplication//(exclude = {SecurityAutoConfiguration.class})
public class WmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmsApplication.class, args);
    }
}
