package sample.models;

import javafx.scene.image.Image;

public class Grass extends Object {
    private final static String urlImage = "sample/images/grass.png";
    public Grass(int x, int y)
    {
        super(x, y, new Image(urlImage));
    }
}
