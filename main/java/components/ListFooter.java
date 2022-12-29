package components;

import global.Global;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utils.EasyButton;
import utils.EasyShadow;

public class ListFooter {
    private ListFooter(){}

    public static BorderPane getListFooter(
            double width,
            double height
    ){
        BorderPane container = new BorderPane();
        container.setPrefSize(width, height);

        // 设置文本输入框
        TextField inputBox = new TextField();
        inputBox.setPrefSize(width-64.0, 48.0);
        inputBox.setStyle("-fx-font-size:16px;-fx-font-weight:bold;");
        BorderPane.setMargin(inputBox, new Insets(24.0));
        container.setCenter(inputBox);

        // 设置右边的“添加”按钮
        Pane button = EasyButton.getButton(
                48.0,48.0,"rectangle",
                Global.MAIN_GREEN, Color.WHITE,new Text("+"),"-fx-font-size:20px;-fx-font-weight:bold;",
                // 绑定点击事件
                ()->{
                    String text = inputBox.getText();
                    if(text.length()>0){
                        System.out.println(text);
                        inputBox.setText("");
                        // 添加待办事项
                        Global.listGroup.addListItem(text);
                        // 数据持久化
                        Global.listMap.saveToStorage();
                        // 刷新列表
                        ListBody.updateListBody();
                    }
                },
                false
        );
        EasyShadow.addTo(button);
        BorderPane.setMargin(button, new Insets(24.0));
        container.setRight(button);

        return container;
    }
}
