import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import views.MainScene;

public class Main extends Application {
    public static final double STAGE_WIDTH = 960.0;
    public static final double STAGE_HEIGHT= 480.0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setScene(MainScene.getMainScene(STAGE_WIDTH, STAGE_HEIGHT));

        primaryStage.setTitle("Todo List");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("logo.png")));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
