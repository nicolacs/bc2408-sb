package com.bootcamp.demo.infra.yahoo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// !!! Vincent ver.
// @Service
// @Qualifier ("Vinver")
public class CookieManager {
    private RestTemplate restTemplate; // Tool A (this is a Dependency of CookieManager)

    // 唔自己NEW Tool A, 往外扔, 等人地創建個條友 自己俾restTemplate
    // 黎個名堂叫 : Dependency Injection(Constructor Injection)
    // 黎個係LIBERLY特性, 唔自己NEW OBJ
    public CookieManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Action B, requires Tool A
    // 新既METHOD: restTemplate.getForEntity() , 入面有method 可以.getBody() / .getHeander()
    // 可以用String Class, 但如果接返黎既資料係一大串JSON, 就會一個STRING接曬, 用唔到
    public String getCookieString() {
        ResponseEntity<String> responseEntity = this.restTemplate
                .getForEntity("https://fc.yahoo.com", String.class);

        String result = responseEntity.getHeaders().get("Set-Cookie").get(0);
        return result.split(";")[0]; // String -> String[] -> String

    //!!! 俾人throw左, 我地攞返返黎, 用個NEW OBJ header 接返住繼續.黎用
    //!!! NOTE
    }

}
