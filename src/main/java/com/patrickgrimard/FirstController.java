package com.patrickgrimard;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;  
  
/** 
 * Created by wuwf on 17/4/27. 
 * 
 */  
@RestController  
public class FirstController {  
  
    @RequestMapping("/first")  
    public Object first() {  
        return "first controller";  
    }  
  
    @RequestMapping("/doError")  
    public Object error() {  
        return 1 / 0;  
    }  
}  