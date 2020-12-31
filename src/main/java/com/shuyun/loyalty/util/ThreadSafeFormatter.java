package com.shuyun.loyalty.util;

import java.text.SimpleDateFormat;

public class ThreadSafeFormatter {

    private static final ThreadLocal<SimpleDateFormat> DATE_FORMAT =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    private static final ThreadLocal<SimpleDateFormat> DATE_TIME_FORMAT =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static SimpleDateFormat dateFormatter() {
        return DATE_FORMAT.get();
    }

    public static SimpleDateFormat dateTimeFormatter() {
        return DATE_TIME_FORMAT.get();
    }

    private ThreadSafeFormatter() {
        throw new IllegalStateException("Utility class");
    }

}
