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

    // 并不是Button类型，而是StackPane类型，可以通过设置点击事件模拟按钮
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
        // 设置按钮大小
        btn.setPrefSize(width,height);
        btn.setMaxSize(width+10,height+10);

        // 设置按钮样式
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

        // 设置文字、放置文字
        text.setFill(textColor);
        text.setStyle(textStyle);
        btn.getChildren().add(text);

        // 添加点击事件
        btn.setOnMouseClicked(e->{
            new Thread(onClicked).start();
        });

        // 添加暗色交互效果
        btn.setOnMouseEntered(e->{
            // 添加scale交互效果
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
            // 添加scale交互效果
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
