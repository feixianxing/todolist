package components;

import com.sun.source.tree.ContinueTree;
import global.Global;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Flow;

public class TableBody {
    private TableBody(){};

    private static BorderPane container;
    private static GridPane table;

    public static BorderPane getTableBody(
            double width,
            int year,
            // 这里的month取值范围是[1,12]
            int month,
            int[][] daysArray){
        // 主容器
        container = new BorderPane();
        container.setPrefWidth(width);
        // 日历表格
        table = new GridPane();
        table.setPrefWidth(width);
        table.setVgap(12);
        // 计算当前日期
        Calendar calendar = Calendar.getInstance();
        int initialYear = calendar.get(Calendar.YEAR);
        int initialMonth = calendar.get(Calendar.MONTH)+1;
        int initialDate = calendar.get(Calendar.DATE);
        // 默认当天为选中的日期
        Global.setSelectedDate(initialYear, initialMonth, initialDate);
        Global.listGroup = Global.listMap.getListGroup(initialYear, initialMonth, initialDate);
        // 日历是否处于当前月份
        boolean inCurrMonth = (year == calendar.get(Calendar.YEAR)) && (month == calendar.get(Calendar.MONTH)+1);

        // 填充日历表格
        for(int i=0;i<6;i++){
            // 日历行
            FlowPane tableRow = new FlowPane();
            tableRow.setAlignment(Pos.CENTER);
            tableRow.setHgap(14);
            for(int j=0;j<7;j++){
                // 每一行的各个元素
                int day = daysArray[i][j];
                Pane tableItem = TableItem.getTableItem(
                        36,36,
                        new Text(String.valueOf(
                                // 只填非零数字
                                day != 0 ? day : " "
                        )),
                        // 点击事件
                        ()->{
                            // 点击选中，更新当前全局日期
                            Global.setSelectedDate(year,month,day);
                            updateTableBody(year, month, daysArray);
                            // 右上角日期更新
                            ListHeader.updateText();
                            // 全局数据更新
                            Global.listGroup = Global.listMap.getListGroup(year, month, day);
                            // 右边列表更新
                            ListBody.updateListBody();
                        },
                        (inCurrMonth && daysArray[i][j] == calendar.get(Calendar.DATE)),
                        daysArray[i][j]!=0,
                        (Global.getSelectedYear()==year && Global.getSelectedMonth()==month) && day==Global.getSelectedDate()
                );
                tableRow.getChildren().add(tableItem);
            }
            table.addRow(i,tableRow);
        }

        container.setCenter(table);
        // 传入的是引用数据类型，建立一次绑定即可
        Global.updateGlobalTable(table);
        // 设置间距
        BorderPane.setMargin(table, new Insets(12.0));

        return container;
    }

    public static void updateTableBody(int year, int month, int[][] daysArray){
        // 这里的month取值范围是[1,12]
        // 首先检查是否当前月份
        Calendar calendar = Calendar.getInstance();
        boolean inCurrMonth = (year == calendar.get(Calendar.YEAR)) && (month == calendar.get(Calendar.MONTH)+1);

        // 修改日历表格
        for(int i=0;i<6;i++){
            FlowPane tableRow = (FlowPane)table.getChildren().get(i);
            // 填写内容
            for(int j=0;j<7;j++){
                int day = daysArray[i][j];
                Pane tableItem = TableItem.getTableItem(
                        36,36,
                        new Text(String.valueOf(
                                // 只填非零数字
                                day != 0 ? day : " "
                        )),
                        ()->{
                            // 点击选中，更新当前全局日期
                            Global.setSelectedDate(year,month,day);
                            updateTableBody(year, month, daysArray);
                            // 右上角日期更新
                            ListHeader.updateText();
                            // 全局信息更新
                            Global.listGroup = Global.listMap.getListGroup(year, month, day);
                            // 右边列表更新
                            ListBody.updateListBody();
                        },
                        (inCurrMonth && daysArray[i][j] == calendar.get(Calendar.DATE)),
                        daysArray[i][j]!=0,
                        (Global.getSelectedYear()==year && Global.getSelectedMonth()==month) && day==Global.getSelectedDate()
                );
                int targetColumn = j;
                Platform.runLater(()->{
                    tableRow.getChildren().set(targetColumn, tableItem);
                });
            }
            int targetRow = i;
            Platform.runLater(()->{
                table.getChildren().set(targetRow,tableRow);
            });
        }
    }
}
