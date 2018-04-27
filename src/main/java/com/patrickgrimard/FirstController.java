package com.patrickgrimard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;  
  
/** 
 * Created by wuwf on 17/4/27. 
 * 
 */  
@RestController  
public class FirstController {
    @Autowired
    private IHelloSevice helloSevice;
  
    @RequestMapping("/first")  
    public Object first() {  
        return "first controller";  
    }  
  
    @RequestMapping("/doError")  
    public Object error() {  
        return 1 / 0;  
    }

    @RequestMapping("/hello")
    public Object hello(String name){
        return helloSevice.say(name);
    }
}  