package jake.freedev.com.volley_test.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * author: yujie.zhang
 * date: 2018/11/5 18:43
 * content: //TODO
 */
public class DateUtils {
    /** 一天的毫秒数 */
    public static final long DAY_MILLS = 86400000;

    private static final DateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private static final DateFormat shortDateFmt = new SimpleDateFormat("MM-dd", Locale.getDefault());
    private static final DateFormat datetimeFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    private static final DateFormat timeFmt = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    private static final DateFormat shortTimeFmt = new SimpleDateFormat("HH:mm", Locale.getDefault());
    private static final DateFormat shortDatetimeFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

    /**
     * 解析日期。<br>
     * 如果date可转为Long型，则解析date为毫秒数。否则尝试按yyyy-MM-dd或yyyy-MM-dd HH:mm:ss格式解析。
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parse(String date) throws ParseException {
        if (date == null)
            return null;
        try {
            return new Date(Long.parseLong(date));
        } catch (NumberFormatException e) {
            if (date.length() == 19) {
                synchronized (datetimeFmt) {// 保证线程安全,因为DateFormat不是线程安全的
                    return datetimeFmt.parse(date);
                }
            } else if (date.length() == 16) {
                synchronized (shortDatetimeFmt) {
                    return shortDatetimeFmt.parse(date);
                }
            } else if (date.length() == 10) {
                synchronized (dateFmt) {// 保证线程安全,因为DateFormat不是线程安全的
                    return dateFmt.parse(date);
                }
            } else if (date.length() == 8) {
                synchronized (timeFmt) {// 保证线程安全,因为DateFormat不是线程安全的
                    return timeFmt.parse(date);
                }
            } else if (date.length() == 5) {
                synchronized (shortTimeFmt) {// 保证线程安全,因为DateFormat不是线程安全的
                    return shortTimeFmt.parse(date);
                }
            } else {
                throw new ParseException("不能解析的日期: \"" + date + "\"", 0);
            }
        }
    }
}
