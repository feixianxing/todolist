package components;

import global.Global;
import javafx.animation.FillTransition;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import utils.EasyButton;
import utils.EasyShadow;
import utils.EasyTransition;

public class TableItem {
    private TableItem(){};

    public static StackPane getTableItem(
            double width,
            double height,
            Text text,
            Runnable onClickHandler,
            boolean active,
            boolean setTransition,
            boolean selected
    ){
        // ���ô�С
        StackPane item = new StackPane();
        item.setPrefSize(width,height);
        item.setMinSize(width,height);
        // ������߾�(�ƺ�û��Ч������̫��)
        StackPane.setMargin(item, new Insets(12));
        // ���ñ�����ʽ
        Rectangle background = new Rectangle(width,height);
        Color backgroundColor = active ? Global.MAIN_GREEN : Color.WHITE;
        background.setFill(backgroundColor);
        background.setArcWidth(8.0);
        background.setArcHeight(8.0);
        // ��ӵ����ֶ���
        item.getChildren().add(background);

        // �������ֶ���
        Color textColor = active ? Color.WHITE : Global.FONT_COLOR;
        text.setFill(textColor);
        text.setStyle("-fx-font-size: 16px; -fx-font-weight:bold;");
        // ��ӵ����ֶ���
        item.getChildren().add(text);

        // ���á��Ƿ�ѡ�С��ı��
        if(selected){
            Rectangle selectedMark = new Rectangle(width, height);
            // ���͸��
            selectedMark.setFill(Color.TRANSPARENT);
            // ���ñ߿�
            selectedMark.setStroke(Global.MARK_GREEN);
            selectedMark.setStrokeWidth(2);
            // ����Բ��
            selectedMark.setArcWidth(8.0);
            selectedMark.setArcHeight(8.0);
            // ��ӵ������
            item.getChildren().add(selectedMark);
        }

        // ���¼�
        // �ƶ�����
        item.setOnMouseEntered(e->{
            // ȷ���Ƿ���ӹ���Ч��
            if(setTransition){
                FillTransition enterTransition =  EasyTransition.getFillTransition(
                        300,
                        (Shape)((StackPane)e.getSource()).getChildren().get(0),
                        backgroundColor,
                        backgroundColor.darker()
                );
                enterTransition.play();
            }
        });
        item.setOnMouseExited(e->{
            // ȷ���Ƿ���ӹ���Ч��
            if(setTransition){
                FillTransition exitTransition = EasyTransition.getFillTransition(
                        300,
                        (Shape)((StackPane)e.getSource()).getChildren().get(0),
                        backgroundColor.darker(),
                        backgroundColor
                );
                exitTransition.play();
            }
        });

        // ���õ���¼�
        // ���setTransitionΪfalse��˵����ռλ�õĿո��򲻰󶨵���¼�
        if(setTransition){
            item.setOnMouseClicked(e->{
                new Thread(onClickHandler).start();
            });
        }

        return item;
    }
}
