package main.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/main") @CrossOrigin(origins = {"https://49888.github.io", "https://argentina-programa-abb9b.web.app", "https://www.test-cors.org", "*"})
public class MainController {
    
    @RequestMapping("/cors-test")
    public String getAdmin(){

        return "Funciona";
    }
}
