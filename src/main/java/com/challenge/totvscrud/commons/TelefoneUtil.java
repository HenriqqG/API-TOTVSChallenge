package com.challenge.totvscrud.commons;

import org.springframework.util.ObjectUtils;

public class TelefoneUtil {

    public static Boolean isTelefoneValido(String tel){
        return !ObjectUtils.isEmpty(tel) || caracteresDistintos(tel);
    }

    private static Boolean caracteresDistintos(String tel){
        return !(tel.equals("000000000") || tel.equals("111111111") || tel.equals("222222222") ||
                tel.equals("333333333") || tel.equals("444444444") || tel.equals("555555555")
                || tel.equals("666666666") || tel.equals("777777777") || tel.equals("888888888")
                || tel.equals("999999999"));
    }
}
