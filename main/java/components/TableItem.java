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
        // 设置大小
        StackPane item = new StackPane();
        item.setPrefSize(width,height);
        item.setMinSize(width,height);
        // 设置外边距(似乎没有效果，不太懂)
        StackPane.setMargin(item, new Insets(12));
        // 设置背景样式
        Rectangle background = new Rectangle(width,height);
        Color backgroundColor = active ? Global.MAIN_GREEN : Color.WHITE;
        background.setFill(backgroundColor);
        background.setArcWidth(8.0);
        background.setArcHeight(8.0);
        // 添加到布局对象
        item.getChildren().add(background);

        // 设置文字对象
        Color textColor = active ? Color.WHITE : Global.FONT_COLOR;
        text.setFill(textColor);
        text.setStyle("-fx-font-size: 16px; -fx-font-weight:bold;");
        // 添加到布局对象
        item.getChildren().add(text);

        // 设置“是否选中”的标记
        if(selected){
            Rectangle selectedMark = new Rectangle(width, height);
            // 填充透明
            selectedMark.setFill(Color.TRANSPARENT);
            // 设置边框
            selectedMark.setStroke(Global.MARK_GREEN);
            selectedMark.setStrokeWidth(2);
            // 设置圆角
            selectedMark.setArcWidth(8.0);
            selectedMark.setArcHeight(8.0);
            // 添加到组件上
            item.getChildren().add(selectedMark);
        }

        // 绑定事件
        // 移动过渡
        item.setOnMouseEntered(e->{
            // 确认是否添加过渡效果
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
            // 确认是否添加过渡效果
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

        // 设置点击事件
        // 如果setTransition为false，说明是占位用的空格，则不绑定点击事件
        if(setTransition){
            item.setOnMouseClicked(e->{
                new Thread(onClickHandler).start();
            });
        }

        return item;
    }
}
