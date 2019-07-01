package com.example.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Company: www.leadmap.net
 * Description:
 *
 * @author: 49022
 * @Date: 2019/1/24 10:34
 */
public class DateUtil {
    /**
     * 将字符串转换为时间
     *
     * @param string
     * @param dateFormat
     * @return
     */
    public static Date stringDate(String string, DateFormat dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat.getFormatString());
        try {
            return sdf.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
