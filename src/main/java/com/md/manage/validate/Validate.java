package com.md.manage.validate;

import com.md.manage.exception.BaseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Validate {



    public abstract   boolean goCheck();

    protected   boolean isPositiveInt(String id) throws BaseException {
        if(isBlank(id))
            return false;
        String regx ="^[1-9]\\d*$";
        Matcher matcher;
        Pattern pattern =Pattern.compile(regx);
        matcher =pattern.matcher(id);
        if(matcher.matches()){
            return true;
        }else{
            return false;
        }

    }

    public static boolean isBlank(String id){
        return id==null||id.isEmpty()?true:false;
    }


}
