package com.bootcamp.demo.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.bootcamp.demo.infra.yahoo.CrumbManager;
import com.bootcamp.demo.infra.yahoo.QuoteResponse;
import com.bootcamp.demo.model.Quote;
import com.bootcamp.demo.model.StockDTO;
import com.bootcamp.demo.service.StockService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StockServiceImpl implements StockService {

        @Autowired
        private CrumbManager crumbManager;

        @Autowired
        private RedisTemplate<String, String> redisTemplate;

        @Value("${api.yahoofinance.domain}")
        private String YFDomain;

        @Value("${api.yahoofinance.path}")
        private String YFPath;

        @Value("${api.yahoofinance.endpoints.quote}")
        private String endPoint;

    @Override
    public List<StockDTO> getPrice(List<String> symbols)
            throws IOException, InterruptedException {
        String crumb1 = crumbManager.getCrumb();
        String crumb = redisTemplate.opsForValue().get("crumb");// crumbManager.getCrumb();
        String cookie = redisTemplate.opsForValue().get("cookie");// X:crumbManager.getCookie();
        String symbolString = String.join(",", symbols);
        String url = String.format(
                "https://query1.finance.yahoo.com/v7/finance/quote?symbols=%s&crumb=%s",
                symbolString, crumb);

        System.out.println("URL###:   " + url);

        // 創建 HTTP 客戶端
        HttpClient client = HttpClient.newHttpClient();

        // 創建 HTTP 請求
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                .header("User-Agent", "Mozilla/5.0").header("Accept", "*/*")
                .header("Cookie", cookie).GET().build();

        // 發送請求並獲取響應
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
     //   System.out.println("response*******:   " + response.body());
        List<StockDTO> stockList = new ArrayList<>(); // 用於存儲結果

        if (response.statusCode() == 200) { // 檢查響應狀態碼
         //   StockMapper stockMapper = new StockMapper();
         //   Quote quoteResponse =
         //   stockMapper.map(response.body());
         QuoteResponse  quoteResponse =   new ObjectMapper().readValue( response.body(), QuoteResponse.class);
                
         if (quoteResponse != null ) {
                     for (Quote quote : quoteResponse.getQuoteResponse().getResult()) {
                         StockDTO stock =StockDTO.builder()//
                        .regularMarketPrice(quote.getRegularMarketPrice())
                        .regularMarketChangePercent(quote.getRegularMarketChangePercent())
                        .bidSize(quote.getBidSize())
                        .askSize(quote.getAskSize())
                        .ask(quote.getAsk())//
                        .bid(quote.getBid())//
                        .symbol(quote.getSymbol())
                        .build();
                        stockList.add(stock); // 將每個 StockDTO 加入列表
                     }
                 }
        }
        return stockList;
    }
}


// // 创建 HTTP 客户端
// HttpClient client = HttpClient.newHttpClient();

// // 创建 HTTP 请求，设置必要的头信息
// HttpRequest request = HttpRequest.newBuilder()//
// .uri(URI.create(url))//
// .header("User-Agent", "Mozilla/5.0") // 确保拼写正确
// .header("Accept", "*/*")//
// .header("Cookie", cookie) // 设置 Cookie
// .GET() // 使用 GET 方法
// .build();

// // 发送请求并获取响应
// HttpResponse<String> response =
// client.send(request, HttpResponse.BodyHandlers.ofString());

// // 调用 Yahoo Finance API
// //StockResponse response = restTemplate.getForObject(url, StockResponse.class);

// // 提取数据并返回 StockDTO
// if (response != null && response.body()!= null) {
// StockDTO quotes = response.body();
// if (quotes != null && quotes.length > 0) {
// StockDTO quote = quotes[0]; // 获取第一个股票信息
// StockDTO stockDTO = new StockDTO();
// stockDTO.setSymbol(quote.getSymbol());
// stockDTO.setShortName(quote.getShortName());
// stockDTO.setRegularMarketPrice(quote.getRegularMarketPrice());
// return stockDTO;
// }
// }
// return null; // 或抛出自定义异常


// String url = Url.builder() //
// .scheme(Scheme.HTTPS) //
// .domain(this.YFDomain) //
// .path(this.YFPath)
// .endpoint(this.endPoint) //
// .build() //
// .toUrlString();
// System.out.println("url=" + url);

// StockDTO[] sData;
// try{
// sData = this.restTemplate.getForObject(url, StockDTO[].class);
// } catch (Exception e){
// throw new Exception();
// }
// return null; //List.of(sData);
// }
// }

