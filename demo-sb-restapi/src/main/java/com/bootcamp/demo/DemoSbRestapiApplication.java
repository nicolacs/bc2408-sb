package com.bootcamp.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication // = @ComponentScan + @SpringBootConfiguration + @EnableAutoConfiguration
// !!! @ComponentScan
// !!! 1. Bean is OBJ (只係一個工人)
// - Something like Obj, but NOT created by your written class
// - Bean is Unique
// !!! 2. Spring Context (一個有用既死物, 有用既垃圾桶)
// - is a separated memory area, besides heap memory
// - Store beans
// !!! 3. Spring life cycle
// - 個cycle係由JAR 死物開始
// (jar -> mvn spring-boot:run)
// - it is a Server start jounary
// Before Server start, look up which class is being annotated as component
// - i.e. @Controller & @Service is a component, so Spring will create an OBJ for this class
// server start會scan 下有幾多controller 就new 幾多個OBJ 再放落垃圾桶, i.e.:GovCatController, HanasengCat
// !!! 黎D OBJ 就係Bean / Spring Bean, 唔再叫OBJ
//	bean既名就係個class既名
//	bean 只係一個工具, 唔會用黎儲狀態或value. bean 係無狀態, 儲野係database
//		-所以唔會寫static

// @SpringBootConfiguration
// @EnableAutoConfiguration
// !!! IoC (Inversion of Control)

//!!! <<<TRY>>
//MTR  一條API 攞UP, 一條攞DOWN


//public static ConfigurableApplicationContext context;
public class DemoSbRestapiApplication {
	public static ConfigurableApplicationContext context;
	public static void main(String[] args) {
	context = SpringApplication.run(DemoSbRestapiApplication.class, args); // 7 x 24
	}

}
