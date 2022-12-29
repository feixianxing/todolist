package components;

import global.Global;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.Date;

public class Calendar{
    // ����ʵ����
    private Calendar(){};

    private static java.util.Calendar date;
    private static SimpleIntegerProperty year = new SimpleIntegerProperty();
    private static SimpleIntegerProperty month = new SimpleIntegerProperty();   // ȡֵ[0,11]

    public static Pane getCalender(double width, double height){
        // ��ʼ����ǰ���е�ʱ��
        date = java.util.Calendar.getInstance();
        date.setTime(new Date());
        year.setValue(date.get(java.util.Calendar.YEAR));
        month.setValue(date.get(java.util.Calendar.MONTH));

        // ���ò��ִ�С
        BorderPane calender = new BorderPane();
        calender.setPrefWidth(width);
        calender.setPrefHeight(height);
        // ��ȡ������������
        calender.setTop(CalendarHeader.getCalenderHeader(width, 68.0,
                year.getValue(),
                month.getValue()+1,
                // prev����¼����л�����һ����
                () -> {
                    prevMonth();
                    CalendarBody.updateCalendar(year.getValue(), month.getValue());
                },
                // next����¼����л�����һ����
                () -> {
                    nextMonth();
                    CalendarBody.updateCalendar(year.getValue(), month.getValue());
                }
        ));

        // ��ȡ�����������
        calender.setCenter(CalendarBody.getCalendarTable(width, year.getValue(), month.getValue()+1));

        return calender;
    }

    // ��������
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
        // ��������ʱ��
        // java.util.Calendar��monthȡֵΪ[0,11],�����������month+1
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
        // ��������ʱ��
        // java.util.Calender��monthȡֵΪ[0,11],�����������month+1.
        CalendarHeader.updateDate(year.getValue(), month.getValue()+1);
    }
}
