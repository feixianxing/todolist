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
            // �����monthȡֵ��Χ��[1,12]
            int month,
            int[][] daysArray){
        // ������
        container = new BorderPane();
        container.setPrefWidth(width);
        // �������
        table = new GridPane();
        table.setPrefWidth(width);
        table.setVgap(12);
        // ���㵱ǰ����
        Calendar calendar = Calendar.getInstance();
        int initialYear = calendar.get(Calendar.YEAR);
        int initialMonth = calendar.get(Calendar.MONTH)+1;
        int initialDate = calendar.get(Calendar.DATE);
        // Ĭ�ϵ���Ϊѡ�е�����
        Global.setSelectedDate(initialYear, initialMonth, initialDate);
        Global.listGroup = Global.listMap.getListGroup(initialYear, initialMonth, initialDate);
        // �����Ƿ��ڵ�ǰ�·�
        boolean inCurrMonth = (year == calendar.get(Calendar.YEAR)) && (month == calendar.get(Calendar.MONTH)+1);

        // ����������
        for(int i=0;i<6;i++){
            // ������
            FlowPane tableRow = new FlowPane();
            tableRow.setAlignment(Pos.CENTER);
            tableRow.setHgap(14);
            for(int j=0;j<7;j++){
                // ÿһ�еĸ���Ԫ��
                int day = daysArray[i][j];
                Pane tableItem = TableItem.getTableItem(
                        36,36,
                        new Text(String.valueOf(
                                // ֻ���������
                                day != 0 ? day : " "
                        )),
                        // ����¼�
                        ()->{
                            // ���ѡ�У����µ�ǰȫ������
                            Global.setSelectedDate(year,month,day);
                            updateTableBody(year, month, daysArray);
                            // ���Ͻ����ڸ���
                            ListHeader.updateText();
                            // ȫ�����ݸ���
                            Global.listGroup = Global.listMap.getListGroup(year, month, day);
                            // �ұ��б����
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
        // ������������������ͣ�����һ�ΰ󶨼���
        Global.updateGlobalTable(table);
        // ���ü��
        BorderPane.setMargin(table, new Insets(12.0));

        return container;
    }

    public static void updateTableBody(int year, int month, int[][] daysArray){
        // �����monthȡֵ��Χ��[1,12]
        // ���ȼ���Ƿ�ǰ�·�
        Calendar calendar = Calendar.getInstance();
        boolean inCurrMonth = (year == calendar.get(Calendar.YEAR)) && (month == calendar.get(Calendar.MONTH)+1);

        // �޸��������
        for(int i=0;i<6;i++){
            FlowPane tableRow = (FlowPane)table.getChildren().get(i);
            // ��д����
            for(int j=0;j<7;j++){
                int day = daysArray[i][j];
                Pane tableItem = TableItem.getTableItem(
                        36,36,
                        new Text(String.valueOf(
                                // ֻ���������
                                day != 0 ? day : " "
                        )),
                        ()->{
                            // ���ѡ�У����µ�ǰȫ������
                            Global.setSelectedDate(year,month,day);
                            updateTableBody(year, month, daysArray);
                            // ���Ͻ����ڸ���
                            ListHeader.updateText();
                            // ȫ����Ϣ����
                            Global.listGroup = Global.listMap.getListGroup(year, month, day);
                            // �ұ��б����
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
