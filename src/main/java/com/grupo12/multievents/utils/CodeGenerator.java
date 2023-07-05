package com.grupo12.multievents.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CodeGenerator {

    public String generate() {
        Random rand = new Random();

        String code = rand.ints(48, 123)
                .filter(num -> (num<58 || num>64) && (num<91 || num>96))
                .limit(15)
                .mapToObj(c -> (char)c).collect(StringBuffer::new, StringBuffer::append, StringBuffer::append)
                .toString();

        return code;
    }

}
