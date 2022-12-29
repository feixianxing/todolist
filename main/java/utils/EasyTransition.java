package utils;

import javafx.animation.FillTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class EasyTransition {
    private EasyTransition(){};

    // Ëõ·Å¹ý¶É
    public static ScaleTransition getScaleTransition(double duration, Node target, double from, double to){
        ScaleTransition st = new ScaleTransition(Duration.millis(duration), target);
        st.setFromX(from);
        st.setFromY(from);
        st.setToX(to);
        st.setToY(to);
        return st;
    }

    // Ìî³ä¹ý¶É
    public static FillTransition getFillTransition(double duration, Shape target, Color from, Color to){
        FillTransition ft = new FillTransition(Duration.millis(duration), target);
        ft.setFromValue(from);
        ft.setToValue(to);
        return ft;
    }
}
