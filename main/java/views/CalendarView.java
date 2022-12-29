package views;

import components.Calendar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class CalendarView {
    // 不可实例化
    private CalendarView(){};

    // 创建日历模块，并返回
    public static Pane getCalendar(double width, double height){
        GridPane calenderContainer = new GridPane();
        // 设置区域大小
        calenderContainer.setPrefWidth(width);
        calenderContainer.setPrefHeight(height);
        // 挂载日历内容
        calenderContainer.add(Calendar.getCalender(width, height),0,0);
        // 返回日历模块
        return calenderContainer;
    }
}
