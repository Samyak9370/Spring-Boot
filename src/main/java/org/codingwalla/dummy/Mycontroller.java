package org.codingwalla.dummy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Mycontroller {
    
    @PostMapping("process-form")
    public static String getData(@RequestParam int num1,@RequestParam int num2){
    int result=num1+num2;
        return "Result :-  "+result;
}
}
