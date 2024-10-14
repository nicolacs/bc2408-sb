package com.bootcamp.demo_jsonplaceholder;

import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.List;
import com.bootcamp.demo_jsonplaceholder.controller.impl.JPHController;
import com.bootcamp.demo_jsonplaceholder.model.UserDTO;
import com.bootcamp.demo_jsonplaceholder.service.JPHService;

//!!! Test Controller Only   
@WebMvcTest (JPHController.class) //可以加試咩class既OBJ
class JPHControllerTest {  //黎個TEST class唔係俾人CAL, 唔好寫public
    //!!! WebMvcTest -> MockMvc -> call Controller Endpoints
    // Validate f

    // 2. Must be Integration Test (好似真實環境咁)

    @MockBean // 似係testing 入面用既Autowised
              // 另外: as main code using Sutowised for bean OBJ, we use MockBean俾返粒豆佢TEST
    private JPHService jphService;

    @Autowired
    private MockMvc mockMvc; // Similar to postman, for testing only
    //!!! In real spring env, No MockMvc bean.

    @Test
    void testJPHGetAllUsers() throws Exception{   //throws Exception 自己鐘意,可唔寫
        // Mock Result
        UserDTO user1 = UserDTO.builder()
            .username("vincentlau")
            .website("vincent@gmail.com")
            .phone("12345678")
            .build();

        UserDTO user2 = UserDTO.builder()
            .username("nicola")
            .website("nicsin@gmail.com")
            .phone("234345672")
            .build();

    // Define how the Result is being mocked
        Mockito.when(jphService.getUsers()).thenReturn(List.of(user1, user2));

    //!!! Test Web Layer to call controller
            mockMvc.perform(get("/jph/users"))
                .andExpect(jsonPath("$[0].username", Matchers.is("vincentlau")))
                .andExpect(jsonPath("$[0].website", Matchers.is("vincent@gmail.com")))
                .andExpect(jsonPath("$[1].username", Matchers.is("nicola")))
                .andExpect(jsonPath("$[1].website", Matchers.is("nicsin@gmail.com")));
        verify(jphService, times(1)).getUsers();  // 測試成個method只係穿透左個OBJ 1次
    }
    
}
