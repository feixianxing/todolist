package components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utils.EasyShadow;

import java.util.Calendar;


public class CalendarBody {
    private CalendarBody(){};

    private static final String[] labelList = {"日","一","二","三","四","五","六"};

    private static int[][] daysArray = new int[6][7];

    private static Pane tableBody;

    private static boolean hasInit = false;

    public static Pane getCalendarTable(
            double width,
            int year,
            // 这里传进来的month取值范围是[1,12]
            int month
    ){
        // 日历主体
        GridPane table = new GridPane();
        table.setMinWidth(width);

        // 初始化
        updateCalendar(year, month-1);

        // 日历第一行开始
        //==================================================================================
        // 日历表格表头(即星期几标识)
        FlowPane tableHeadRow = new FlowPane();
        tableHeadRow.setAlignment(Pos.CENTER);
        tableHeadRow.setPrefWidth(width);
        tableHeadRow.setPrefHeight(48.0);
        GridPane.setMargin(tableHeadRow,new Insets(6));
        tableHeadRow.setHgap(14);
        // 添加阴影效果
        EasyShadow.addTo(tableHeadRow);

        // 填写文本
        for(int i=0; i<labelList.length;i++){
            BorderPane tableHeadItem = TableHead.getTableHead(
                    36,36,
                    new Text(labelList[i]),
                    Color.BLACK,"-fx-font-weight:bold; -fx-font-size: 20px;",
                    Color.WHITE, 12
            );
            tableHeadRow.getChildren().add(tableHeadItem);
        }
        // 挂载到日历容器上
        table.addRow(0,tableHeadRow);
        // 日历第一行结束
        //==================================================================================

        // 日历主体开始
        //==================================================================================
        tableBody = TableBody.getTableBody(width,year,month,daysArray);
        GridPane.setMargin(tableBody, new Insets(6));
        EasyShadow.addTo(tableBody);
        // 挂载到日历容器上
        table.addRow(1, tableBody);
        // 日历主体结束
        //==================================================================================


        return table;
    }

    // 更新日历
    public static void updateCalendar(int year, int month){
        // 这里的month取值为[0,11]
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        // 这个月有多少天
        int dayCount = calendar.getActualMaximum(Calendar.DATE);
        // 第一天是星期几(Sun.->Sat. := 1->7)
        int firstDay = calendar.get(Calendar.DAY_OF_WEEK);
        // 修正,修正后(Sun.->Sat. := 0->6)
        firstDay--;

        int day = 1;
        // 填写第1周
        for(int i=0;i<firstDay;i++){
            daysArray[0][i] = 0;
        }
        for(int i=firstDay;i<7;i++){
            daysArray[0][i] = day++;
        }
        // 其他周
        for(int i=1;i<6;i++){
            for(int j=0;j<7;j++){
                if(day > dayCount){
                    daysArray[i][j] = 0;
                }else{
                    daysArray[i][j] = day++;
                }
            }
        }
        // 渲染
        if(!hasInit){
            hasInit = true;
        }else {
            // 这里的month取值为[0,11]
            TableBody.updateTableBody(year,month+1,daysArray);
        }
    }
}
