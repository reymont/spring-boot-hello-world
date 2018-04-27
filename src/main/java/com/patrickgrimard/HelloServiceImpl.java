package com.patrickgrimard;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements IHelloSevice {
    @Override
    public String say(String name) {
        return "Hello "+name;
    }
}
