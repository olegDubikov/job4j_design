package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 127;
        short s = 32767;
        int i = -123456789;
        long l = 12345678987654321L;
        float f = 2.13F;
        double d = 6587.452;
        char c = 'X';
        boolean bl = false;
        LOG.debug("""
                Primitive types in Java:
                byte={}, short={}, int={}, long={},
                float={}, double={}, char={}, boolean={}.""", b, s, i, l, f, d, c, bl);
    }
}
