import javafx.animation.FillTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

class TestToStart{
    public static void main(String[] args) {
        Test.main(args);
    }
}

public class Test extends Application {
    public static void main(String[] args) {
//        Calendar calendar = Calendar.getInstance();
//
//        System.out.println(calendar.get(Calendar.YEAR));
//        System.out.println(calendar.get(Calendar.MONTH)+1);
//        System.out.println(calendar.get(Calendar.DATE));

//        while(true){
//            Scanner input = new Scanner(System.in);
//            int year = input.nextInt();
//            int month = input.nextInt();
//            calendar.set(year, month-1, 1);
//            System.out.println("days: " + calendar.getActualMaximum(Calendar.DATE));
//        }


//        launch(args);


    }


    @Override
    public void start(Stage stage) throws Exception {
        StackPane sp = new StackPane();
        sp.setPrefSize(600.0,600.0);
        Circle circle = new Circle(48.0, Color.GREEN);
        sp.getChildren().add(circle);
        Text text = new Text("S");
        text.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        text.setFill(Color.WHITE);
        sp.getChildren().add(text);

        circle.setOnMouseEntered(e->{
            Shape background = (Shape) (e.getSource());
            FillTransition ft = new FillTransition(Duration.millis(300),background);
            ft.setFromValue(Color.GREEN);
            ft.setToValue(Color.GREEN.darker());
            ft.play();
        });
        circle.setOnMouseExited(e->{
            Shape background = (Shape) (e.getSource());
            FillTransition ft = new FillTransition(Duration.millis(300),background);
            ft.setFromValue(Color.GREEN.darker());
            ft.setToValue(Color.GREEN);
            ft.play();
        });

        Scene scene = new Scene(sp);
        stage.setScene(scene);
        stage.show();
    }
}
