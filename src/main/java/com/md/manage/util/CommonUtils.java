package com.md.manage.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Component
public class CommonUtils {



    @Value("md.token.salt")
    private static String salt;


    public static Date getCurrentDate(){
//        SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        ZoneId zoneId = ZoneId.systemDefault();
//        LocalDate localDate = LocalDate.now();
//        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
//        Date date = Date.from(zdt.toInstant());
        Date date = new Date();
        return date;
    }


    public static String  generatorToken(String id)throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String token;
        try{
            token =md5(salt+"-"+id+"-"+getCurrentDate().getTime());
        }catch (Exception e){
            throw  e;
        }return token;
    }



    public static String md5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }
}
