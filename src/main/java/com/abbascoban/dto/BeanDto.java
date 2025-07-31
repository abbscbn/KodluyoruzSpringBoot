package com.abbascoban.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class BeanDto {
    private Long id;
    private String beanName;
    private String benaData;

    public void initialBeanMethod(){
        log.info("Ben başlamadan önce ben varım");
        System.out.println("Bean başlamadan önce çalışacak method");
    }

    public void destroyBeanMethod(){
        log.info("Bean bittikten sonra ben varım");
        System.err.println("Bean bittikten sonra çalışacak method");
    }
}
