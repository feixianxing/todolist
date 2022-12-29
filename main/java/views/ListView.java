package views;

import components.ListBody;
import components.ListFooter;
import components.ListHeader;
import global.Global;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ListView {
    // 不可实例化
    private ListView(){};

    // 待办事项--区域
    public static Pane getListView(double width, double height){
        // 创建布局容器
        BorderPane listView = new BorderPane();
        // 设置容器大小
        listView.setPrefSize(width, height);

        // 设置左边框
        listView.setBorder(new Border(new BorderStroke(
                null,null,null,
                Global.BORDER_COLOR,
                null,null,null,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(2.0),
                new Insets(0)
        )));

        // 添加顶部内容--当前选中日期
        listView.setTop(ListHeader.getListHeader(width, 72.0));

        // 添加中间内容--待办事项列表
        listView.setCenter(ListBody.getListBody(width));

        // 添加底部内容--新增待办事项按钮
        listView.setBottom(ListFooter.getListFooter(width, 72.0));
        return listView;
    }
}
