package com.lisi4ka.lab4webdb.utils;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.text.DecimalFormat;

public class DotValidator extends NotificationBroadcasterSupport implements DotValidatorMBean{
    DecimalFormat df = new DecimalFormat("#.##");
    private int dotCount = 0;
    private int missDotCount = 0;
    private int _sequence = 0;
    private double averageTime = 0;
    private long lastTime = System.currentTimeMillis();
    public String[] result(Float X, Float Y, Float R, Float random) {
        int gr;
        int re;
        int bl;
        String text;
        if (((X >= 0 && X < R) && (Y >= 0 && Y < R)) || ((X <= 0 && Y <= 0) && ((-X - Y) < R / 2)) || ((X >= 0 && Y <= 0) && ((Math.pow(X, 2) + Math.pow(Y, 2) < Math.pow(R, 2))))) {
            if (random < 0.5) {
                gr = 60;
                re = 60;
                bl = 60;
                text = "Не пробил!";
                calculateAverageTime(lastTime, averageTime, dotCount);
                dotCount++;
                return new String[]{String.valueOf(re), String.valueOf(gr), String.valueOf(bl), text};
            } else {
                gr = 255;
                re = 0;
                bl = 0;
                text = "Есть пробитие!";
                calculateAverageTime(lastTime, averageTime, dotCount);
                dotCount++;
                return new String[]{String.valueOf(re), String.valueOf(gr), String.valueOf(bl), text};
            }
        } else {
            if (((X >= 0 && Y >= 0) && (X == R || Y == R) || ((X <= 0) && (Y <= 0)) && (-X - Y == R / 2)) || ((X >= 0 && Y <= 0) && ((Math.pow(X, 2) + Math.pow(Y, 2) == Math.pow(R, 2))))) {
                gr = 150;
                re = 150;
                bl = 150;
                text = "Рикошет!";
                calculateAverageTime(lastTime, averageTime, dotCount);
                dotCount++;
                return new String[]{String.valueOf(re), String.valueOf(gr), String.valueOf(bl), text};
            } else {
                gr = 0;
                re = 255;
                bl = 0;
                text = "Не попал!";
                calculateAverageTime(lastTime, averageTime, dotCount);
                dotCount++;
                missDotCount++;
                if (missDotCount % 4 == 0) {
                    Notification notification = new Notification("info", this, _sequence++, "Пользователь промахнулся 4 раза!");
                    sendNotification(notification);
                }
                return new String[]{String.valueOf(re), String.valueOf(gr), String.valueOf(bl), text};
            }
        }
    }

    void calculateAverageTime(long lastTime, double averageTime, int dotCount) {
        this.averageTime = (averageTime * dotCount + (System.currentTimeMillis() - lastTime)) / (dotCount + 1);
        this.lastTime = System.currentTimeMillis();
    }

    @Override
    public int getDotCount() {
        return dotCount;
    }

    @Override
    public int getMissDotCount() {
        return missDotCount;
    }

    @Override
    public String getAverageTimeSeconds() {
        return df.format(averageTime / 1000.0);
    }
}
