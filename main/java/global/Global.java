package global;

import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import utils.ListGroupInfo;
import utils.ListMap;

public class Global {
    // 全局常用颜色
    public static final Color MAIN_GREEN = new Color(12.0/256, 128.0/256, 3.0/256,1);
    public static final Color BACKGROUND_COLOR = new Color(245.0/256,245.0/256,245.0/256,1);
    public static final Color BORDER_COLOR = new Color(240.0/256,240.0/256,240.0/256,1);
    public static final Color FONT_COLOR = new Color(96.0/256,96.0/256,96.0/256,1);
    public static final Color MARK_GREEN = new Color(6.0/256, 71.0/256, 1.0/256,1);
    public static final Color DELETE_RED = new Color(207.0/256, 19.0/256, 34.0/256,1);

    // 全局：当前点击选中的日期
    private static int selectedYear;
    private static int selectedMonth;   // 取值[1,12]
    private static int selectedDate;

    public static void setSelectedDate(int year, int month, int date){
        selectedYear = year;
        selectedMonth = month;
        selectedDate = date;
        System.out.println("selected: "+selectedYear+" "+selectedMonth+" "+selectedDate);
    }
    public static int getSelectedYear(){return selectedYear;};
    public static int getSelectedMonth(){return selectedMonth;};
    public static int getSelectedDate(){return selectedDate;};

    // 全局：当前日历视图所处于的年与月
    private static int currViewYear;
    private static int currViewMonth;   // 取值[1,12]
    public static void setCurrView(int year, int month){
        currViewYear = year;
        currViewMonth = month;
        System.out.println("currView: "+currViewYear + " "+currViewMonth);
    }
    public static int getCurrViewYear(){return currViewYear;};
    public static int getCurrViewMonth(){return currViewMonth;};

    // 全局：当前月份的日历对象
    private static GridPane globalTable;
    public static void updateGlobalTable(GridPane table){
        globalTable = table;
    }

    // 代办事项数据
    public static ListMap listMap;
    public static ListGroupInfo listGroup;
}
