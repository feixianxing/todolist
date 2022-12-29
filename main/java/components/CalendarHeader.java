package components;

import global.Global;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utils.EasyButton;

public class CalendarHeader {
    // 不可实例化
    private CalendarHeader(){};

    // Pane
    // 日历头部主容器
    private static BorderPane header;
    // 中间的结点
    private static BorderPane middleNode;
    // 左边的结点
    private static BorderPane left;
    // 右边的结点
    private static BorderPane right;

    // 日历顶部年月份
    private static int year;
    private static int month;
    private static SimpleStringProperty titleText = new SimpleStringProperty("----年--月");

    public static Pane getCalenderHeader(
            double width,
            double height,
            int initYear,
            int initMonth,
            Runnable prevTask,
            Runnable nextTask
            ){
        // 初始化日期
        year = initYear;
        month = initMonth;
        updateDate(year,month);

        // 日历头部主容器
        header = new BorderPane();
        // 中间的结点(放置日期文本)
        middleNode = new BorderPane();
        // 左边的结点(放置prev按钮)
        left = new BorderPane();
        // 右边的结点(放置next按钮)
        right = new BorderPane();

        // 主容器设置
        header.setPrefWidth(width);
        header.setPrefHeight(height);
        header.setPadding(new Insets(10));
        header.setCenter(middleNode);
        header.setLeft(left);
        header.setRight(right);
        // 中间结点设置
        middleNode.setBackground(new Background(new BackgroundFill(
                Global.MAIN_GREEN, // 背景颜色
                new CornerRadii(8.0),   // 圆角
                new Insets(0)   //无间隙
        )));
        middleNode.setMaxWidth(200.0);
        middleNode.setMaxHeight(54.0);
        // 日历标题，即(年份+月份)
        Text title = new Text();
        title.textProperty().bind(titleText);
        title.setFill(Color.WHITE);
        title.setStyle("-fx-font-size:20px; -fx-font-weight: bold; -fx-font-family: Arial;");
        middleNode.setCenter(title);

        // 左边结点
        left.setPrefWidth(48);
        left.setPrefHeight(24);
        // 设置按钮
        Pane prev = EasyButton.getButton(
                22,22,"circle",
                Global.MAIN_GREEN,
                Color.WHITE,new Text("<"),"-fx-font-size:20px; -fx-font-weight: bold;",
                prevTask,true
        );
        left.setCenter(prev);

        // 右边结点
        right.setPrefWidth(48);
        right.setPrefHeight(height);
        // 设置按钮
        Pane next = EasyButton.getButton(
                22,22,"circle",
                Global.MAIN_GREEN,
                Color.WHITE,new Text(">"),"-fx-font-size:20px; -fx-font-weight: bold;",
                nextTask,true
        );
        right.setCenter(next);

        return header;
    }

    // 更新时间
    public static void updateDate(int y, int m){
        year = y;
        month = m;
        // 更新日历顶部年月份的显示
        titleText.setValue(year+"年"+month+"月");
        // 更新全局数据："当前日历所在年月份"
        Global.setCurrView(year, month);
    }
}
