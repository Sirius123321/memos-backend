package org.lgz.memosbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/memo")
public class MemoController {
    @GetMapping
    public Mono<String> hello(){
        return Mono.just("hello");
    }

}
