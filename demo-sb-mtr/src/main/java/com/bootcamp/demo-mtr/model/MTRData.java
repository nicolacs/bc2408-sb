package com.bootcamp.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MTRData {
    private int status;
    private String message;
    @JsonProperty("sys_time")
    private String sysTime;
    @JsonProperty("curr_time")
    private String currTime;
    private Data data;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Data {
        private Line line;
        //MAP??

        @Getter
        @Builder
        @AllArgsConstructor
        public static class Line{
            @JsonProperty("curr_time")
            private String currTime;
            @JsonProperty("sys_time")
            private String sysTime;
            private Direction direction;
            // private List<String> Up;
            // private List<String> Down;

            @Getter
            @Builder
            @AllArgsConstructor
            public static class Direction{
                private int seq;
                private String dest;
                private int plat;
                private String time;
                private int ttnt;
                private String valid;
                private String source;

            
            }
        }
    }
}
