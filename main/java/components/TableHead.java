package components;

import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TableHead{
    private TableHead(){};

    public static BorderPane getTableHead(
            double width,
            double height,
            Text text,
            Color textColor,
            String textStyle,
            Color backgroundColor,
            double radius
    ){
        BorderPane tableHead = new BorderPane();
//        tableHead.setPadding(new Insets(8,12,8,12));

        // ���ô�С
        tableHead.setPrefSize(width,height);
        tableHead.setMaxSize(width,height);
        tableHead.setMinSize(width,height);
        // ����������ʽ
        text.setFill(textColor);
        text.setStyle(textStyle);
        tableHead.setCenter(text);
        // ���ñ�����Բ��
        tableHead.setBackground(new Background(new BackgroundFill(
                backgroundColor,
                new CornerRadii(radius),
                Insets.EMPTY
        )));

        return tableHead;
    }
}
