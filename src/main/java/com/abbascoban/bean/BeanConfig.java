package com.abbascoban.bean;

import com.abbascoban.dto.BeanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfig {


    @Bean(initMethod = "initialBeanMethod",destroyMethod = "destroyBeanMethod")
    @Scope("singleton")  //request,session
    public BeanDto beanDto(){
        return BeanDto.builder()
                .id(0L)
                .beanName("DTO Adı")
                .benaData("DTO DATASI")
        .build();
    }

}
