package com.bootcamp.demo_jsonplaceholder;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest  // mvn clean tes -> server start
//!!! Full Spring Environment (Full Beans)
class DemoJsonplacehlderApplicationTests {

	@Test  // server start -> create conext
	void contextLoads() {
		// This test case is just for sever start + bean dependency validations
		// i.e @Autowired (required bean), @Value({yml})
	}

}
