package sample.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sample.controllers.Controller;

public class Object {
    protected int x;
    protected int y;
    protected Image img;
    protected int width;
    protected int height;


    public Object(int x, int y, Image img)
    {
        this.x = y;
        this.y = x;
        this.width = Controller.SCALESIZE;
        this.height = Controller.SCALESIZE;
        this.img = img;
    }
    public Object(int x, int y, Image img, int witdth, int height)
    {
        this.x = y;
        this.y = x;
        this.width = witdth;
        this.height = height;
        this.img = img;
    }
    public Object(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public Object(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public void render(GraphicsContext gc)
    {
        gc.drawImage(img,x,y, Controller.SCALESIZE, Controller.SCALESIZE);
    }
    public void update(){}
    public Boolean collision(Object entities) {
        if (entities.x >= this.x && entities.x - this.x < this.width && entities.y >= this.y && entities.y - this.y < this.height) {
            return true;
        } else if (this.x >= entities.x && this.x - entities.x < entities.width && entities.y >= this.y && entities.y - this.y < this.height) {
            return true;
        } else if (entities.x >= this.x && entities.x - this.x < this.width && this.y >= entities.y && this.y - entities.y < entities.height) {
            return true;
        } else {
            return this.x >= entities.x && this.x - entities.x < entities.width && this.y >= entities.y && this.y - entities.y < entities.height ? true : false;
        }
    }
}
