package sample.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.models.*;
import sample.models.Object;
import sample.controllers.Controller;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application{

    private String phaDaoURl = "sample/images/phaDao.png";
    public static List<Object> ObjectToChange = new ArrayList<Object>();
    public static List<Object> staticObject = new ArrayList<Object>();
    public static List<Object> Explosion = new ArrayList<Object>();
    private int WIDTH;
    private int HEIGH;
    private final int FPS = 60;
    private final int speed = 30;
    //Menu:
    /*
        Nếu menu = 0: Load Home
        Nếu menu = 1: Load game
     */
    //public static int menu = 0
    //NÚT:
    public static Stage stage = null;

    public static boolean upkey = false; //
    public static boolean rightkey = false; // go right
    public static boolean downkey = false; // stop
    public static boolean leftkey = false; // go left
    public static boolean spaceKey = false;

    static Timeline gameloop;
    //
    public void LoadFX(int menu)
    {
        if(menu == 1)
        {

            batDauGame(this.stage);
        }
        else if(menu == 0)
        {
            //gameloop.stop();
            Home(this.stage);
        }
        else if(menu == 2)
        {
            //Chơi chức năng solo:

        }
    }
    public Main()
    {
        this.gameloop = null;
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stage = primaryStage;
        //playSound.play_anItem();
        Home(primaryStage);
    }
    public void Home(Stage primaryStage)
    {
        try
        {
            Pane root = (Pane)FXMLLoader.load(getClass().getResource("Home.fxml"));
            primaryStage.setTitle("Home");
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e){}
    }
    public void batDauGame(Stage primaryStage)
    {
        try
        {
            System.out.println("Chạy vào đây");
            ResetGame();
            Pane root = (Pane)FXMLLoader.load(getClass().getResource("sample.fxml"));
            Canvas display = (Canvas)Controller.getNode("display", root);
            GraphicsContext gc = display.getGraphicsContext2D();
            //Lấy size
            int[] size = Controller.loadObjectToList(ObjectToChange,staticObject);
            root.setPrefSize(size[1]* Controller.SCALESIZE + 50, size[0]* Controller.SCALESIZE + 50);
            primaryStage.setTitle("Bomberman Game");
            Scene scene = new Scene(root);
            //GET SIZE MAP
            HEIGH = size[0]* Controller.SCALESIZE;
            display.setHeight(HEIGH);
            WIDTH = size[1]* Controller.SCALESIZE;
            display.setWidth(WIDTH);

            //renderManHinhPhaDao(gc);

            //CREATE MAP
            render(gc);

            scene.setOnKeyPressed(event -> KeyPress(event));
            scene.setOnKeyReleased(event -> keyRelease(event));

            //Game loop:

            gameloop = new Timeline(new KeyFrame(Duration.millis(speed), event -> {
                // todo: Tốc độ của player:
                if(rightkey) {
                    Controller.player.Tien();
                }
                if(leftkey) {
                    Controller.player.Lui();
                }
                if(downkey) {
                    Controller.player.Xuong();
                }
                if(upkey)
                {
                    Controller.player.Len();
                }

            }), new KeyFrame(Duration.millis(1000/FPS), event -> {
                // todo: render theo FPS:
                if(Controller.GAME_OVER)
                {
                    //Nếu game over thì load lại luôn
                    ResetGame();
                    Controller.loadObjectToList(ObjectToChange,staticObject);
                    render(gc);
                    Controller.GAME_OVER = false;
                    //gameloop.stop();
                }
                if(Controller.PHA_DAO == true)
                {
                    renderManHinhPhaDao(gc);
                    //Controller.PHA_DAO = false;
                    gameloop.stop();
                }
                else
                {
                    update();
                    render(gc);
                }

            }));

            gameloop.setCycleCount(Timeline.INDEFINITE);
            gameloop.play();

            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e){}
    }

    public void KeyPress(KeyEvent e) {
        System.out.println(e.getCode().toString());
        switch (e.getCode().toString()) {
            case "LEFT":
                Main.upkey = false;
                Main.downkey = false;
                Main.rightkey = false;
                Main.leftkey = true;
                break;
            case "RIGHT":
                Main.upkey = false;
                Main.downkey = false;
                Main.rightkey = true;
                Main.leftkey = false;
                break;
            case "UP":
                Main.upkey = true;
                Main.downkey = false;
                Main.rightkey = false;
                Main.leftkey = false;
                break;
            case "DOWN":
                Main.upkey = false;
                Main.downkey = true;
                Main.rightkey = false;
                Main.leftkey = false;
                break;
            case "SPACE":
                playSound.play_datBom();
                Controller.player.thaBom();
                break;
            case "END":
                Controller.PHA_DAO = true;
                break;
            case "ESCAPE":
                Controller.PHA_DAO = false;
                this.gameloop.stop();
                this.LoadFX(0);
                break;
        }
    }

    public void keyRelease(KeyEvent e) {

        Main.upkey = false;
        Main.downkey = false;
        Main.rightkey = false;
        Main.leftkey = false;
        Main.spaceKey = false;

        Controller.player.moveStepX = 0;
        Controller.player.moveStepY = 0;

    }
    private void renderManHinhPhaDao(GraphicsContext gc)
    {
        Image phaDao = new Image(phaDaoURl);
        gc.clearRect(0,0,WIDTH,HEIGH);
        gc.drawImage(phaDao,0+(WIDTH/2 - phaDao.getWidth()/2),0);
        //gc.fillRect(0,0,WIDTH,HEIGH);


        System.out.println("Xóa màn hình");

    }
    private void render(GraphicsContext gc)
    {
        gc.clearRect(0,0,WIDTH,HEIGH);

        for (Object o: staticObject) {
            o.render(gc);
        }
        for(Object o:Explosion)
        {
            o.render(gc);
        }
        for (Object o:ObjectToChange) {
            o.render(gc);
        }
    }
    private void update()
    {
        try{
            for(Object o:Explosion)
            {
                o.update();
            }
//            for (Object o: staticObject) {
//                o.update();
//            }
            for (Object o:ObjectToChange) {
                o.update();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void ResetGame()
    {
        ObjectToChange = new ArrayList<Object>();
        staticObject = new ArrayList<Object>();
    }
    public static void main(String[] args) {
        launch(args);
    }


    @FXML
    public void play1()
    {
        this.LoadFX(1);
    }
    public void play2()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Chức năng này đang phát triển, nếu thày cho thêm thời gian thì em mới làm kịp");
        alert.show();
    }
}
