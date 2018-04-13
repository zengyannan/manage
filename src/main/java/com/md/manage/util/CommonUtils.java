package com.md.manage.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class CommonUtils {



    public static Date getCurrentDate(){
//        SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        ZoneId zoneId = ZoneId.systemDefault();
//        LocalDate localDate = LocalDate.now();
//        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
//        Date date = Date.from(zdt.toInstant());
        Date date = new Date();
        return date;
    }


}
