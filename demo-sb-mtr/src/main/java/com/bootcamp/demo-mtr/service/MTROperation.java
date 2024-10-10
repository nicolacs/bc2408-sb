package com.bootcamp.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.bootcamp.demo.dto.MTRDTO;

public interface MTROperation {
    @GetMapping ("/mtr/gettime/{line}/{sta}")
    List<MTRDTO> getTime(@PathVariable String line, @PathVariable String sta);
}
