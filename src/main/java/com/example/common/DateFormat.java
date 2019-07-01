package com.example.common;

/**
 * Company: www.leadmap.net
 * Description:
 *
 * @author: 49022
 * @Date: 2019/1/24 10:29
 */
public enum DateFormat {

    DateFull("yyyy-MM-dd HH:mm:ss");
//    DateSignYMH("yyyy-MM-dd");

    private String formatString;

    private DateFormat(String formatString) {
        this.formatString = formatString;
    }


    public String getFormatString() {
        return formatString;
    }

    public void setFormatString(String formatString) {
        this.formatString = formatString;
    }
}
