package components;

import global.Global;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import utils.EasyButton;
import utils.EasyShadow;
import utils.ListGroupInfo;
import utils.ListItemInfo;


public class ListItem {
    private ListItem(){}
    private static final double CUT_WIDTH = 48.0;
    public static StackPane getListItem(double width, ListItemInfo itemInfo){
        StackPane item = new StackPane();
        VBox.setMargin(item, new Insets(12.0));
        item.setId(itemInfo.getCreateDate().getTime()+"");
        // 背景
        Rectangle background = new Rectangle(width-CUT_WIDTH, 64.0);
        background.setArcWidth(12.0);
        background.setArcHeight(12.0);
        background.setFill(Color.WHITE);
//        EasyShadow.addTo(background);
        background.setStroke(Global.BORDER_COLOR);
        background.setStrokeWidth(2.0);
        item.getChildren().add(background);

        // 内容
        BorderPane content = new BorderPane();
        content.setMinSize(width-CUT_WIDTH, 64.0);
        content.setPadding(new Insets(18.0));

        // 文本
        Text text = new Text(itemInfo.getText());
        BorderPane.setMargin(text, new Insets(6.0,0,0,6.0));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        // 右边按钮组容器
        FlowPane buttonGroup = new FlowPane();
        buttonGroup.setPrefWidth(140.0);
        buttonGroup.setHgap(16.0);
        buttonGroup.setAlignment(Pos.CENTER);
        // 提示文字
        Text notice = new Text("Done.");
        notice.setFill(Global.MAIN_GREEN);
        notice.setStyle("-fx-font-size: 14px; -fx-font-weight:bold;");
        // 已完成-按钮
        Pane doneButton = EasyButton.getButton(
                48.0,24.0,"rectangle",
                Global.MAIN_GREEN,Color.WHITE,
                new Text("done"),"-fx-font-size: 14px; -fx-font-weight:bold;",
                ()->{
                    System.out.println("done."+item.getId());
                    // 完成任务
                    Global.listGroup.setDoneByIndex(
                        Global.listGroup.getListItemIndex(item.getId())
                    );
                    Global.listMap.saveToStorage();
                    ListBody.updateListBody();
                },true
        );
        // 删除-按钮
        Pane deleteButton = EasyButton.getButton(
                56.0,24.0,"rectangle",
                Global.DELETE_RED,Color.WHITE,
                new Text("delete"),"-fx-font-size: 14px; -fx-font-weight:bold;",
                ()->{
                    System.out.println("delete."+item.getId());
                    // 删除任务
                    Global.listGroup.setDeleteByIndex(
                            Global.listGroup.getListItemIndex(item.getId())
                    );
                    Global.listMap.saveToStorage();
                    ListBody.updateListBody();
                },true
        );
        // 根据该任务是否已完成，显示 按钮 或 提示文字（已完成）
        buttonGroup.getChildren().add(itemInfo.hasDone() ? notice : doneButton);
        buttonGroup.getChildren().add(deleteButton);

        content.setLeft(text);
        content.setRight(buttonGroup);
        item.getChildren().add(content);

        return item;
    }
}
