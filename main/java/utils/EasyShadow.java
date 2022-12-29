package utils;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class EasyShadow {
    private EasyShadow(){};

    public static void addTo(Node target){
        // …Ë÷√“ı”∞
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(3.0);
        dropShadow.setOffsetX(1.0);
        dropShadow.setOffsetY(1.2);
        dropShadow.setSpread(0.4);
        dropShadow.setBlurType(BlurType.GAUSSIAN);
        dropShadow.setColor(Color.color(197.0/256, 197.0/256, 197.0/256, 0.7));
        target.setEffect(dropShadow);

        if(target instanceof Pane){
            ((Pane)target).setBackground(new Background(new BackgroundFill(
                    Color.WHITE,
                    new CornerRadii(8.0),
                    Insets.EMPTY
            )));
        }

    }
}
