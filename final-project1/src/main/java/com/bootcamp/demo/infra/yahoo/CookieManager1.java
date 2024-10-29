package com.bootcamp.demo.infra.yahoo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Service
@Qualifier ("MyVer")
public class CookieManager1 {
    private String response;
    private Map<String, List<String>> headers;

    public String getResponse() throws IOException, InterruptedException {
        String urlString = "https://fc.yahoo.com";
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .GET()
                .build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

                // Get the response body
                // response = httpResponse.body();
                // System.out.println("Response Content: " + response);
        
                // Get the response headers
                headers = httpResponse.headers().map();
        
                // Extract the "Set-Cookie" header value
                List<String> setCookieHeader = headers.get("Set-Cookie");
                if (setCookieHeader != null && !setCookieHeader.isEmpty()) {
                    String setCookieValue = setCookieHeader.get(0).split(";")[0];
                    System.out.println("Set-Cookie Header: " + setCookieValue);
                    return setCookieValue;
                } else {
                    return null;
                }
    //     client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
    //             .thenApply(HttpResponse::body)
    //             .thenAccept(responseBody -> {
    //                 System.out.println("Response Content: " + responseBody);
    //             })
    //             .join(); // 等待异步请求完成
    //         response = responseBody;
    //     return response;
    // }
            }  
}
