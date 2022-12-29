package views;

import components.ListBody;
import components.ListFooter;
import components.ListHeader;
import global.Global;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ListView {
    // ����ʵ����
    private ListView(){};

    // ��������--����
    public static Pane getListView(double width, double height){
        // ������������
        BorderPane listView = new BorderPane();
        // ����������С
        listView.setPrefSize(width, height);

        // ������߿�
        listView.setBorder(new Border(new BorderStroke(
                null,null,null,
                Global.BORDER_COLOR,
                null,null,null,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(2.0),
                new Insets(0)
        )));

        // ��Ӷ�������--��ǰѡ������
        listView.setTop(ListHeader.getListHeader(width, 72.0));

        // ����м�����--���������б�
        listView.setCenter(ListBody.getListBody(width));

        // ��ӵײ�����--�����������ť
        listView.setBottom(ListFooter.getListFooter(width, 72.0));
        return listView;
    }
}
