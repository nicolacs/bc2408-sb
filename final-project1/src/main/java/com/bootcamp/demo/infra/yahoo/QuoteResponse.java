package com.bootcamp.demo.infra.yahoo;

import java.util.ArrayList;
import java.util.List;
import com.bootcamp.demo.model.Quote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
public class QuoteResponse {
    private QuoteResponseData  quoteResponse ;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    @ToString
    public static class QuoteResponseData  {
        @Builder.Default
        private List<Quote> result = new ArrayList<>();
        private String error;

    }

}
