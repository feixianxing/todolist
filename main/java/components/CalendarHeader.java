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
    // ����ʵ����
    private CalendarHeader(){};

    // Pane
    // ����ͷ��������
    private static BorderPane header;
    // �м�Ľ��
    private static BorderPane middleNode;
    // ��ߵĽ��
    private static BorderPane left;
    // �ұߵĽ��
    private static BorderPane right;

    // �����������·�
    private static int year;
    private static int month;
    private static SimpleStringProperty titleText = new SimpleStringProperty("----��--��");

    public static Pane getCalenderHeader(
            double width,
            double height,
            int initYear,
            int initMonth,
            Runnable prevTask,
            Runnable nextTask
            ){
        // ��ʼ������
        year = initYear;
        month = initMonth;
        updateDate(year,month);

        // ����ͷ��������
        header = new BorderPane();
        // �м�Ľ��(���������ı�)
        middleNode = new BorderPane();
        // ��ߵĽ��(����prev��ť)
        left = new BorderPane();
        // �ұߵĽ��(����next��ť)
        right = new BorderPane();

        // ����������
        header.setPrefWidth(width);
        header.setPrefHeight(height);
        header.setPadding(new Insets(10));
        header.setCenter(middleNode);
        header.setLeft(left);
        header.setRight(right);
        // �м�������
        middleNode.setBackground(new Background(new BackgroundFill(
                Global.MAIN_GREEN, // ������ɫ
                new CornerRadii(8.0),   // Բ��
                new Insets(0)   //�޼�϶
        )));
        middleNode.setMaxWidth(200.0);
        middleNode.setMaxHeight(54.0);
        // �������⣬��(���+�·�)
        Text title = new Text();
        title.textProperty().bind(titleText);
        title.setFill(Color.WHITE);
        title.setStyle("-fx-font-size:20px; -fx-font-weight: bold; -fx-font-family: Arial;");
        middleNode.setCenter(title);

        // ��߽��
        left.setPrefWidth(48);
        left.setPrefHeight(24);
        // ���ð�ť
        Pane prev = EasyButton.getButton(
                22,22,"circle",
                Global.MAIN_GREEN,
                Color.WHITE,new Text("<"),"-fx-font-size:20px; -fx-font-weight: bold;",
                prevTask,true
        );
        left.setCenter(prev);

        // �ұ߽��
        right.setPrefWidth(48);
        right.setPrefHeight(height);
        // ���ð�ť
        Pane next = EasyButton.getButton(
                22,22,"circle",
                Global.MAIN_GREEN,
                Color.WHITE,new Text(">"),"-fx-font-size:20px; -fx-font-weight: bold;",
                nextTask,true
        );
        right.setCenter(next);

        return header;
    }

    // ����ʱ��
    public static void updateDate(int y, int m){
        year = y;
        month = m;
        // ���������������·ݵ���ʾ
        titleText.setValue(year+"��"+month+"��");
        // ����ȫ�����ݣ�"��ǰ�����������·�"
        Global.setCurrView(year, month);
    }
}
