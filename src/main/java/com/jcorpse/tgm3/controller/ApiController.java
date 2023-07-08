package com.jcorpse.tgm3.controller;

import io.swagger.v3.oas.annotations.OpenAPI31;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ApiController {

    @GetMapping("/")
    private ResponseEntity index(){
        return  new ResponseEntity<>("ㄟˋㄍㄢˋ！，ㄔㄨㄢ ㄕㄢ ㄐㄧㄚˇㄟˋ！，ㄨ ㄏㄨ ！ㄓㄜˋㄎㄜˇ一 ㄧㄤˇㄇㄚ ！？ㄟˋㄓㄤ ㄌㄤˊㄓㄜˋㄎㄜˇ一 ㄧㄤˇㄇㄚ ！，ㄨㄛˇ ㄅㄨˋ ㄓ ㄉㄠˋㄋㄧˇ ㄓㄨㄚ ㄏㄨㄟˊㄐㄧㄚ ！，ㄟˋ！ㄐㄧㄝˋㄍㄨㄛˋㄐㄧㄝˋㄍㄨㄛˋㄅㄨˊㄧㄠˋㄆㄠˇ！，ㄨ ㄨ， ㄊㄚ ㄆㄠˇㄉㄧㄠˋㄌㄜ˙！，ㄨ ㄏㄨ ㄏㄨ ！ㄛˋㄏㄠˇㄉㄧㄠˇㄛˋ！", HttpStatus.OK);
    }
}
