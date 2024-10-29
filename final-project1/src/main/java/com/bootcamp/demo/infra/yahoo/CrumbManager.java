package com.bootcamp.demo.infra.yahoo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// get Key by cookie String
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Service
public class CrumbManager {
    private String cookie;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public CrumbManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    private CookieManager1 cookieManager1;

    public String getCrumb() throws IOException, InterruptedException {
        String cookie = cookieManager1.getResponse();
        this.redisTemplate.opsForValue().set("cookie", cookie);
        String url = "https://query1.finance.yahoo.com/v1/test/getcrumb";

        // 创建 HTTP 客户端
        HttpClient client = HttpClient.newHttpClient();

        // 创建 HTTP 请求，设置必要的头信息
        HttpRequest request = HttpRequest.newBuilder()//
                .uri(URI.create(url))//
                .header("User-Agent", "Mozilla/5.0") // 确保拼写正确
                .header("Accept", "*/*")//
                .header("Cookie", cookie) // 设置 Cookie
                .GET() // 使用 GET 方法
                .build();

        // 发送请求并获取响应
        HttpResponse<String> httpResponse =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        // 检查响应状态
        // if (httpResponse.statusCode() == 200) {
        String crumb = httpResponse.body();
        System.out.println("Crumb>>>>>>>>>: " + crumb);// -> for testing
        this.redisTemplate.opsForValue().set("crumb", crumb);
        return crumb;
        // } else {
        // System.out.println("Error: " + httpResponse.statusCode() + " - "
        // + httpResponse.body());
        // return null;
        // }
    }
} 

// ResponseEntity<String> responseEntity = this.restTemplate.getForEntity(
  // "https://query1.finance.yahoo.com/v1/test/getcrumb",
  // String.class);
  // responseEntity.getHeaders().set("Cookie", cookieManager1.getResponse());
  // responseEntity.getHeaders().set("User-Agent", "Mozila/5.0");

// HttpEntity<Void> entity = new HttpEntity<>(headers);
// ResponseEntity<String> responseEntity = restTemplate.exchange(url,
// HttpMethod.GET, entity, String.class);

// return responseEntity.getBody();
