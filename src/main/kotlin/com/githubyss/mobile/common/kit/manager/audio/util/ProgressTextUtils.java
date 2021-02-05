package com.githubyss.mobile.common.kit.manager.audio.util;

import com.githubyss.mobile.common.kit.util.LogcatUtils;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 88396251
 * 2018/5/23
 * 时间格式化类
 */

public class ProgressTextUtils {
    
    public static String getProgressText(long time) {
        Calendar calendar = Calendar.getInstance();
        LogcatUtils.INSTANCE.d("getProgressText", "time: " + time);
        long roundTime = Double.valueOf(Math.round(time / 1000F) * 1000).longValue();
        LogcatUtils.INSTANCE.d("getProgressText", "roundTime: " + roundTime);
        calendar.setTime(new Date(roundTime));
        double minute = calendar.get(Calendar.MINUTE);
        double second = calendar.get(Calendar.SECOND);
        
        DecimalFormat format = new DecimalFormat("00");
        return format.format(minute) + ":" + format.format(second);
    }
}
