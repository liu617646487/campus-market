package com.ljc.market.util;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 项目通用工具类
 */
public class StringUtil {

    /**
     * 返回指定格式的日期字符串
     * @param date
     * @param formatter
     * @return
     */
    public static String getFormatterDate(Date date,String formatter){
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        return sdf.format(date);
    }

    /**
     * 判断是否为ajax请求
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request){
        String header = request.getHeader("X-Requested-With");
        if("XMLHttpRequest".equals(header)){
            return true;
        }
        return false;
    }

    /**
     * 从流读取字符串
     * @param inputStream
     * @return
     */
    public static String getStringInputStream(InputStream inputStream){
        String buf = null;
        String string = "";
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "GB2312");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            try {
                while ((buf = bufferedReader.readLine())!=null){
                    string += buf;
                }
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return string;
    }

}
