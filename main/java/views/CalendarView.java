package views;

import components.Calendar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class CalendarView {
    // ����ʵ����
    private CalendarView(){};

    // ��������ģ�飬������
    public static Pane getCalendar(double width, double height){
        GridPane calenderContainer = new GridPane();
        // ���������С
        calenderContainer.setPrefWidth(width);
        calenderContainer.setPrefHeight(height);
        // ������������
        calenderContainer.add(Calendar.getCalender(width, height),0,0);
        // ��������ģ��
        return calenderContainer;
    }
}
