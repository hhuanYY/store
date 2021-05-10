package cn.huan.t_store.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author hhuan_YY
 * @create 2021-05-10-10:31
 */
public class DateTimeUtil {

    /**
     * 获取上周的当前时间
     * @return
     */
    public static String getLastWeekTime() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.WEEK_OF_YEAR, -1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(instance.getTime());
    }

    /**
     * Date 转成 字符串
     * @return
     */
    public static String getStringByDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
