package components;

import global.Global;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import utils.EasyShadow;

public class ListBody {
    private ListBody(){}
    private static double width;
    private static VBox listBox;

    public static ScrollPane getListBody(
            double w
    ){
        width = w;
        // 可滚动窗口
        ScrollPane container = new ScrollPane();
        BorderPane.setMargin(container, new Insets(0,0,0,8.0));
        container.setPrefWidth(width-24.0);
        container.setBackground(new Background(new BackgroundFill(Global.BACKGROUND_COLOR,new CornerRadii(0), Insets.EMPTY)));
        container.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        container.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // 垂直布局
        listBox = new VBox();
        listBox.setPrefWidth(width-24.0);
        listBox.setBackground(new Background(new BackgroundFill(Global.BACKGROUND_COLOR,new CornerRadii(0), Insets.EMPTY)));
        EasyShadow.addTo(listBox);
        // 插入内容：待办事项
        updateListBody();

        container.setContent(listBox);
        return container;
    }

    public static void updateListBody(){
        // 清除原来的
        Platform.runLater(()->{
            listBox.getChildren().clear();
        });
        // 更新现在的
        // 如果有数据，显示列表
        if(Global.listGroup.getList().size()>0){
            for(int i=0;i<Global.listGroup.getList().size(); i++){
                int index = i;
                Platform.runLater(()->{
                    listBox.getChildren().add(index, ListItem.getListItem(width, Global.listGroup.getList().get(index)));
                });
            }
        }else{// 如果没有数据，则提示
            Text notice = new Text("这一天还没有添加待办事项噢.");
            notice.setFill(Global.FONT_COLOR);
            notice.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
            VBox.setMargin(notice, new Insets(16.0));
            Platform.runLater(()->{
                listBox.getChildren().add(notice);
            });
        }
    }
}
