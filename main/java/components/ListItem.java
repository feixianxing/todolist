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
        // ����
        Rectangle background = new Rectangle(width-CUT_WIDTH, 64.0);
        background.setArcWidth(12.0);
        background.setArcHeight(12.0);
        background.setFill(Color.WHITE);
//        EasyShadow.addTo(background);
        background.setStroke(Global.BORDER_COLOR);
        background.setStrokeWidth(2.0);
        item.getChildren().add(background);

        // ����
        BorderPane content = new BorderPane();
        content.setMinSize(width-CUT_WIDTH, 64.0);
        content.setPadding(new Insets(18.0));

        // �ı�
        Text text = new Text(itemInfo.getText());
        BorderPane.setMargin(text, new Insets(6.0,0,0,6.0));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        // �ұ߰�ť������
        FlowPane buttonGroup = new FlowPane();
        buttonGroup.setPrefWidth(140.0);
        buttonGroup.setHgap(16.0);
        buttonGroup.setAlignment(Pos.CENTER);
        // ��ʾ����
        Text notice = new Text("Done.");
        notice.setFill(Global.MAIN_GREEN);
        notice.setStyle("-fx-font-size: 14px; -fx-font-weight:bold;");
        // �����-��ť
        Pane doneButton = EasyButton.getButton(
                48.0,24.0,"rectangle",
                Global.MAIN_GREEN,Color.WHITE,
                new Text("done"),"-fx-font-size: 14px; -fx-font-weight:bold;",
                ()->{
                    System.out.println("done."+item.getId());
                    // �������
                    Global.listGroup.setDoneByIndex(
                        Global.listGroup.getListItemIndex(item.getId())
                    );
                    Global.listMap.saveToStorage();
                    ListBody.updateListBody();
                },true
        );
        // ɾ��-��ť
        Pane deleteButton = EasyButton.getButton(
                56.0,24.0,"rectangle",
                Global.DELETE_RED,Color.WHITE,
                new Text("delete"),"-fx-font-size: 14px; -fx-font-weight:bold;",
                ()->{
                    System.out.println("delete."+item.getId());
                    // ɾ������
                    Global.listGroup.setDeleteByIndex(
                            Global.listGroup.getListItemIndex(item.getId())
                    );
                    Global.listMap.saveToStorage();
                    ListBody.updateListBody();
                },true
        );
        // ���ݸ������Ƿ�����ɣ���ʾ ��ť �� ��ʾ���֣�����ɣ�
        buttonGroup.getChildren().add(itemInfo.hasDone() ? notice : doneButton);
        buttonGroup.getChildren().add(deleteButton);

        content.setLeft(text);
        content.setRight(buttonGroup);
        item.getChildren().add(content);

        return item;
    }
}
