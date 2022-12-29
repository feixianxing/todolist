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

    private static final String[] labelList = {"��","һ","��","��","��","��","��"};

    private static int[][] daysArray = new int[6][7];

    private static Pane tableBody;

    private static boolean hasInit = false;

    public static Pane getCalendarTable(
            double width,
            int year,
            // ���ﴫ������monthȡֵ��Χ��[1,12]
            int month
    ){
        // ��������
        GridPane table = new GridPane();
        table.setMinWidth(width);

        // ��ʼ��
        updateCalendar(year, month-1);

        // ������һ�п�ʼ
        //==================================================================================
        // ��������ͷ(�����ڼ���ʶ)
        FlowPane tableHeadRow = new FlowPane();
        tableHeadRow.setAlignment(Pos.CENTER);
        tableHeadRow.setPrefWidth(width);
        tableHeadRow.setPrefHeight(48.0);
        GridPane.setMargin(tableHeadRow,new Insets(6));
        tableHeadRow.setHgap(14);
        // �����ӰЧ��
        EasyShadow.addTo(tableHeadRow);

        // ��д�ı�
        for(int i=0; i<labelList.length;i++){
            BorderPane tableHeadItem = TableHead.getTableHead(
                    36,36,
                    new Text(labelList[i]),
                    Color.BLACK,"-fx-font-weight:bold; -fx-font-size: 20px;",
                    Color.WHITE, 12
            );
            tableHeadRow.getChildren().add(tableHeadItem);
        }
        // ���ص�����������
        table.addRow(0,tableHeadRow);
        // ������һ�н���
        //==================================================================================

        // �������忪ʼ
        //==================================================================================
        tableBody = TableBody.getTableBody(width,year,month,daysArray);
        GridPane.setMargin(tableBody, new Insets(6));
        EasyShadow.addTo(tableBody);
        // ���ص�����������
        table.addRow(1, tableBody);
        // �����������
        //==================================================================================


        return table;
    }

    // ��������
    public static void updateCalendar(int year, int month){
        // �����monthȡֵΪ[0,11]
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        // ������ж�����
        int dayCount = calendar.getActualMaximum(Calendar.DATE);
        // ��һ�������ڼ�(Sun.->Sat. := 1->7)
        int firstDay = calendar.get(Calendar.DAY_OF_WEEK);
        // ����,������(Sun.->Sat. := 0->6)
        firstDay--;

        int day = 1;
        // ��д��1��
        for(int i=0;i<firstDay;i++){
            daysArray[0][i] = 0;
        }
        for(int i=firstDay;i<7;i++){
            daysArray[0][i] = day++;
        }
        // ������
        for(int i=1;i<6;i++){
            for(int j=0;j<7;j++){
                if(day > dayCount){
                    daysArray[i][j] = 0;
                }else{
                    daysArray[i][j] = day++;
                }
            }
        }
        // ��Ⱦ
        if(!hasInit){
            hasInit = true;
        }else {
            // �����monthȡֵΪ[0,11]
            TableBody.updateTableBody(year,month+1,daysArray);
        }
    }
}
