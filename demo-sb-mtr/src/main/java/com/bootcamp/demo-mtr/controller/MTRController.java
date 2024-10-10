package com.bootcamp.demo.controller.impl;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo.controller.MTROperation;
import com.bootcamp.demo.dto.MTRDTO;


@RestController
public class MTRController implements MTROperation {

    @Override
    public List<MTRDTO> getTime(@PathVariable String line, @PathVariable String sta) {
        MTRDTO[] time = new RestTemplate()
        .getForObject("https://rt.data.gov.hk/v1/transport/mtr/getSchedule.php?line="+line+"&sta="+sta
        , MTRDTO[].class);
        return List.of(time);
    }

    
}
