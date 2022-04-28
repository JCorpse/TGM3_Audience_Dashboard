package com.jcorpse.tgm3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api")
public class ApiController {

    @GetMapping("/")
    private ResponseEntity index(){
        return  new ResponseEntity<>("ㄟˋㄍㄢˋ！，ㄔㄨㄢ ㄕㄢ ㄐㄧㄚˇㄟˋ！，ㄨ ㄏㄨ ！ㄓㄜˋㄎㄜˇ一 ㄧㄤˇㄇㄚ ！？ㄟˋㄓㄤ ㄌㄤˊㄓㄜˋㄎㄜˇ一 ㄧㄤˇㄇㄚ ！，ㄨㄛˇ ㄅㄨˋ ㄓ ㄉㄠˋㄋㄧˇ ㄓㄨㄚ ㄏㄨㄟˊㄐㄧㄚ ！，ㄟˋ！ㄐㄧㄝˋㄍㄨㄛˋㄐㄧㄝˋㄍㄨㄛˋㄅㄨˊㄧㄠˋㄆㄠˇ！，ㄨ ㄨ， ㄊㄚ ㄆㄠˇㄉㄧㄠˋㄌㄜ˙！，ㄨ ㄏㄨ ㄏㄨ ！ㄛˋㄏㄠˇㄉㄧㄠˇㄛˋ！", HttpStatus.OK);
    }
}
