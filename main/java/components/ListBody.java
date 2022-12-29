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
        // �ɹ�������
        ScrollPane container = new ScrollPane();
        BorderPane.setMargin(container, new Insets(0,0,0,8.0));
        container.setPrefWidth(width-24.0);
        container.setBackground(new Background(new BackgroundFill(Global.BACKGROUND_COLOR,new CornerRadii(0), Insets.EMPTY)));
        container.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        container.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // ��ֱ����
        listBox = new VBox();
        listBox.setPrefWidth(width-24.0);
        listBox.setBackground(new Background(new BackgroundFill(Global.BACKGROUND_COLOR,new CornerRadii(0), Insets.EMPTY)));
        EasyShadow.addTo(listBox);
        // �������ݣ���������
        updateListBody();

        container.setContent(listBox);
        return container;
    }

    public static void updateListBody(){
        // ���ԭ����
        Platform.runLater(()->{
            listBox.getChildren().clear();
        });
        // �������ڵ�
        // ��������ݣ���ʾ�б�
        if(Global.listGroup.getList().size()>0){
            for(int i=0;i<Global.listGroup.getList().size(); i++){
                int index = i;
                Platform.runLater(()->{
                    listBox.getChildren().add(index, ListItem.getListItem(width, Global.listGroup.getList().get(index)));
                });
            }
        }else{// ���û�����ݣ�����ʾ
            Text notice = new Text("��һ�컹û����Ӵ���������.");
            notice.setFill(Global.FONT_COLOR);
            notice.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
            VBox.setMargin(notice, new Insets(16.0));
            Platform.runLater(()->{
                listBox.getChildren().add(notice);
            });
        }
    }
}
