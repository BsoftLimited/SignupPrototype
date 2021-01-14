package com.bsoft.signupprototype.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.Calendar;

public class Time implements Comparable<Time>, Serializable {
    int year, month, day, hour, minute, second;

    public Time(String form){
        String[] init = form.split(" ");
        this.initDate(init[0]);
        this.initTime(init[1]);
    }

    private Time(int year, int month, int day, int hour, int minute, int second){
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public static Time CurrentTime(){
        Calendar calendar = Calendar.getInstance();
        return new Time(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND));
    }

    private void initDate(String date){
        String[] init = date.split("-");
        year = Integer.parseInt(init[0]);
        month = Integer.parseInt(init[1]);
        day = Integer.parseInt(init[2]);
    }

    private void initTime(String time){
        String[] init = time.split(":");
        hour = Integer.parseInt(init[0]);
        minute = Integer.parseInt(init[1]);
        second = Integer.parseInt(init[2]);
    }

    public int getYear() {
        return year;
    }
    public long getYearValue(){
        return year;
    }

    public int getMonth() {
        return month;
    }
    public long getMonthValue(){
        long initYear = year * 12 * 30 * 24 * 60 * 60;
        long initMonth = month * 30 * 24 * 60 * 60;
        return initYear + initMonth;
    }

    public int getDay() {
        return day;
    }
    public long getDayValue(){
        long initYear = year * 12 * 30 * 24 * 60 * 60;
        long initMonth = month * 30 * 24 * 60 * 60;
        long initDay = day * 24 * 60 * 60;
        return initYear + initMonth + initDay;
    }

    public int getHour() {
        return hour;
    }
    public long getHourValue(){
        long initYear = year * 12 * 30 * 24 * 60 * 60;
        long initMonth = month * 30 * 24 * 60 * 60;
        long initDay = day * 24 * 60 * 60;
        long initHour = hour * 60 * 60;
        return initYear + initMonth + initDay + initHour;
    }

    public int getMinute() {
        return minute;
    }
    public long getMinuteValue(){
        long initYear = year * 12 * 30 * 24 * 60 * 60;
        long initMonth = month * 30 * 24 * 60 * 60;
        long initDay = day * 24 * 60 * 60;
        long initHour = hour * 60 * 60;
        long initMinute = minute * 60;
        return initYear + initMonth + initDay + initHour + initMinute;
    }

    public int getSecond() {
        return second;
    }

    private long getValue(){
        long initYear = year * 12 * 30 * 24 * 60 * 60;
        long initMonth = month * 30 * 24 * 60 * 60;
        long initDay = day * 24 * 60 * 60;
        long initHour = hour * 60 * 60;
        long initMinute = minute * 60;
        long initSecond = second;
        return initYear + initMonth + initDay + initHour + initMinute + initSecond;
    }

    public String TimeSpan(){
        Time current = CurrentTime();
        if(current.year > this.year){
            return Time.compareYears(current.year, this.year);
        }else if(current.month > this.month){
            return Time.compareMonths(current.month, this.month);
        }else if(current.day > this.day){
            return Time.compareDays(current.day, this.day);
        }else if(current.hour > this.hour){
            return Time.compareHours(current.hour, this.hour);
        }else if (current.minute > this.minute){
            return Time.compareMinute(current.minute, this.minute);
        }else if(current.second > this.second){
            return Time.compareSeconds(current.second, this.second);
        }
        return  "now";
    }

    private static String compareYears(int current, int year){
        if (current - year == 1) {
            return "last year";
        }
        return (current - year) + " years ago";
    }

    private static String compareMonths(int current, int month){
        if (current - month == 1) {
            return "last month";
        }
        return (current - month) + " months ago";
    }

    private static String compareDays(int current, int day){
        if (current - day == 1) {
            return "yesterday";
        }
        return (current - day) + " days ago";
    }

    private static String compareHours(int current, int hours){
        if (current - hours == 1) {
            return "an hour ago";
        }
        return (current - hours) + " hours ago";
    }

    private static String compareMinute(int current, int minute){
        if (current - minute == 1) {
            return "a minute ago";
        }
        return (current - minute) + " minutes ago";
    }

    private static String compareSeconds(int current, int second){
        if (current - second == 1) {
            return "a seconds ago";
        }
        return (current - second) + " seconds ago";
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int compareTo(Time time) {
        return Long.compare(this.getValue(), time.getValue());
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
    }
}
