package components;

import global.Global;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.Date;

public class Calendar{
    // 不可实例化
    private Calendar(){};

    private static java.util.Calendar date;
    private static SimpleIntegerProperty year = new SimpleIntegerProperty();
    private static SimpleIntegerProperty month = new SimpleIntegerProperty();   // 取值[0,11]

    public static Pane getCalender(double width, double height){
        // 初始化当前类中的时间
        date = java.util.Calendar.getInstance();
        date.setTime(new Date());
        year.setValue(date.get(java.util.Calendar.YEAR));
        month.setValue(date.get(java.util.Calendar.MONTH));

        // 设置布局大小
        BorderPane calender = new BorderPane();
        calender.setPrefWidth(width);
        calender.setPrefHeight(height);
        // 获取日历顶部内容
        calender.setTop(CalendarHeader.getCalenderHeader(width, 68.0,
                year.getValue(),
                month.getValue()+1,
                // prev点击事件：切换到上一个月
                () -> {
                    prevMonth();
                    CalendarBody.updateCalendar(year.getValue(), month.getValue());
                },
                // next点击事件：切换到下一个月
                () -> {
                    nextMonth();
                    CalendarBody.updateCalendar(year.getValue(), month.getValue());
                }
        ));

        // 获取日历表格内容
        calender.setCenter(CalendarBody.getCalendarTable(width, year.getValue(), month.getValue()+1));

        return calender;
    }

    // 更新日期
    private static void prevMonth(){
        int tempMonth = month.getValue();
        int tempYear = year.getValue();
        if(tempMonth == 0){
            tempMonth = 11;
            tempYear--;
        }else{
            tempMonth--;
        }
        date.set(java.util.Calendar.YEAR, tempYear);
        date.set(java.util.Calendar.MONTH, tempMonth);
        year.setValue(tempYear);
        month.setValue(tempMonth);
        // 顶部更新时间
        // java.util.Calendar的month取值为[0,11],这里参数传入month+1
        CalendarHeader.updateDate(year.getValue(), month.getValue()+1);
    }
    private static void nextMonth(){
        int tempMonth = month.getValue();
        int tempYear = year.getValue();
        if(tempMonth == 11){
            tempMonth = 0;
            tempYear++;
        }else{
            tempMonth++;
        }
        date.set(java.util.Calendar.YEAR, tempYear);
        date.set(java.util.Calendar.MONTH, tempMonth);
        year.setValue(tempYear);
        month.setValue(tempMonth);
        // 顶部更新时间
        // java.util.Calender的month取值为[0,11],这里参数传入month+1.
        CalendarHeader.updateDate(year.getValue(), month.getValue()+1);
    }
}
