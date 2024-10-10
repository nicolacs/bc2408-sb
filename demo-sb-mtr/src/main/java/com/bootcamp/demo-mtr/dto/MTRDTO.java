package com.bootcamp.demo.dto;

import com.bootcamp.demo.model.MTRData;
import com.bootcamp.demo.model.MTRData.Data;
import com.bootcamp.demo.model.MTRData.Data.Line;
import com.bootcamp.demo.model.MTRData.Data.Line.Direction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MTRDTO {
    private MTRData curr_timeMtrData;
    private Data line;
    private Line directionLine;
    private Direction desDirection;
    private Direction timDirection;
}
