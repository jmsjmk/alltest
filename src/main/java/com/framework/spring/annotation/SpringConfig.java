package com.framework.spring.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
@Configuration
public class SpringConfig {
 
	@Bean
	public Piano piano(){
		return new Piano();
	}
//	@Bean(name = "counter")
//	public Counter counter(){
//		return  new Counter(12.1,"Shake it Off", null);
//	}
}