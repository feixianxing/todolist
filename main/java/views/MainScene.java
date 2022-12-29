package views;

import global.Global;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;
import org.kordamp.bootstrapfx.BootstrapFX;
import utils.ListMap;

public class MainScene{
    // 不可实例化
    private MainScene(){};

    public static @NotNull Scene getMainScene(double width, double height){
        // 主布局
        GridPane mainPane = new GridPane();
        // 设置宽高
        mainPane.setPrefWidth(width);
        mainPane.setPrefHeight(height);
        // 设置背景颜色
        mainPane.setBackground(new Background(new BackgroundFill(
                Global.BACKGROUND_COLOR ,
                CornerRadii.EMPTY,
                new Insets(0)
        )));

        // 读入数据
        Global.listMap = new ListMap("list_map.dat");

        //挂载日历和备忘录
        mainPane.add(CalendarView.getCalendar(width*0.4, height),0,0);
        mainPane.add(ListView.getListView(width*0.6, height),1,0);

        Scene mainScene = new Scene(mainPane);
        mainScene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        return mainScene;
    }
}
