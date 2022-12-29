package components;

import global.Global;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class ListHeader {
    private ListHeader(){};

    private static Text text;

    public static BorderPane getListHeader(
            double width,
            double height
    ){
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(width,height);

        text = new Text();
        text.setFill(Global.FONT_COLOR.darker());
        text.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        updateText();

        BorderPane.setMargin(text, new Insets(24.0));
        borderPane.setLeft(text);
        return borderPane;
    }

    public static void updateText(){
        text.setText(
                Global.getSelectedYear()+"Äê"
                +Global.getSelectedMonth()+"ÔÂ"
                +Global.getSelectedDate()+"ÈÕ");
    }

}
