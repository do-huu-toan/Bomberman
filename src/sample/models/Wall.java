package sample.models;

import javafx.scene.image.Image;

public class Wall extends Object {
    private final static String urlImage = "sample/images/wall.png";
    public Wall(int x, int y)
    {
        super(x, y, new Image(urlImage));
    }
}
