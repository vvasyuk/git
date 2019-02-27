package com.example.demo.jmx;

import javax.management.AttributeChangeNotification;
import javax.management.NotificationBroadcasterSupport;

public class JmxMXBeanImpl extends NotificationBroadcasterSupport implements JmxMXBean {
    @Override
    public void execute(String s) {
        sendNotification(new AttributeChangeNotification(this, 0,
                System.currentTimeMillis(), s, " ", " ", " ", " "));
    }
}
