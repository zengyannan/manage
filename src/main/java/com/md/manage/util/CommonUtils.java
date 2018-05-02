package com.md.manage.util;

import com.md.manage.exception.BaseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


    public static void validateParams(BindingResult result){
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                throw new BaseException("参数错误", 404, 10001);
            }
        }
    }

    public static  List<Integer> StringToListOfInteger(String ids){
        String[] strIds = ids.split(",");
        List<Integer> list = new ArrayList<>();
        for (String id:strIds){
            list.add(Integer.parseInt(id));
        }
        return list;
    }

    /**
     * 计算检验指标结果
     * @param result
     * @param ref
     * @return
     */
    public static Integer computeTips(String result,String ref){
        String[] refs = ref.split("~");
        if(refs.length==2){
            Double min;
            Double max;
            Double r;
            try{
                min = Double.parseDouble(refs[0]);
                max = Double.parseDouble(refs[1]);
                r = Double.parseDouble(result);
            }catch (NumberFormatException ex){
                throw new BaseException("结果值或浮动参考值错误!");
            }
            if(r<min)
                return 4;
            else if(r>max)
                return 3;
            else
                return 2;
        }else{
            return 5;
        }
    }
}
