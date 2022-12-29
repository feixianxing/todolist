package utils;

import javafx.animation.FillTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Skin;
import javafx.scene.control.Skinnable;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class EasyButton {
    private EasyButton(){};

    // ������Button���ͣ�����StackPane���ͣ�����ͨ�����õ���¼�ģ�ⰴť
    public static StackPane getButton(
            double width,
            double height,
            String shape,
            Color backgroundColor,
            Color textColor,
            Text text,
            String textStyle,
            Runnable onClicked,
            boolean scaleBehavior
    ){
        StackPane btn = new StackPane();
        // ���ð�ť��С
        btn.setPrefSize(width,height);
        btn.setMaxSize(width+10,height+10);

        // ���ð�ť��ʽ
        Shape background = null;
        if(shape.equals("circle")){
            background = new Circle(width, backgroundColor);
        } else if (shape.equals("rectangle")) {
            background = new Rectangle(width,height);
            background.setFill(backgroundColor);
            ((Rectangle)background).setArcWidth(8.0);
            ((Rectangle)background).setArcHeight(8.0);
        }
        btn.getChildren().add(background);

        // �������֡���������
        text.setFill(textColor);
        text.setStyle(textStyle);
        btn.getChildren().add(text);

        // ��ӵ���¼�
        btn.setOnMouseClicked(e->{
            new Thread(onClicked).start();
        });

        // ��Ӱ�ɫ����Ч��
        btn.setOnMouseEntered(e->{
            // ���scale����Ч��
            if(scaleBehavior){
                ScaleTransition buttonEnter = EasyTransition.getScaleTransition(200,(Node)e.getSource(),1.0,1.05);
                buttonEnter.play();
            }
            FillTransition buttonEnter = EasyTransition.getFillTransition(
                    200,
                    (Shape)((StackPane)e.getSource()).getChildren().get(0),
                    backgroundColor,
                    backgroundColor.darker()
            );
            buttonEnter.play();
        });
        btn.setOnMouseExited(e->{
            // ���scale����Ч��
            if(scaleBehavior){
                Transition buttonExit = EasyTransition.getScaleTransition(200,(Node)e.getSource(),1.05,1.0);
                buttonExit.play();
            }
            FillTransition buttonExit = EasyTransition.getFillTransition(
                    200,
                    (Shape)((StackPane)e.getSource()).getChildren().get(0),
                    backgroundColor.darker(),
                    backgroundColor);
            buttonExit.play();
        });

        return btn;
    }
}
