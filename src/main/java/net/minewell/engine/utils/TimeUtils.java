package net.minewell.engine.utils;

public class TimeUtils {

    public static double getTime() {
        return System.nanoTime() / Math.pow(10, 9);
    }

}
