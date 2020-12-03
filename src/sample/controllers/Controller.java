package sample.controllers;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import sample.models.*;
import sample.models.Object;

import java.io.*;
import java.util.List;

public class Controller {
    public static boolean GAME_OVER = false;
    public static boolean PHA_DAO = false;
    public static int TONG_SO_LEVEL = 1;
    public static int LEVEL_NOW = 1;
    public static int bot = 0;

    public static int kichThuocVuNo = 1; //Kích thước của vụ nổ tính từ tâm ra
    public final static int SCALESIZE = 32;
    public static Bomber player;
    public static char[][] MAP;
    public static Node getNode(String id, Pane root) {
        Node result = null;
        for (Node i : root.getChildren()) {
            if (i.getId().toString().equals(id)) {
                result = i;
            }
        }
        return result;
    }

    public static int[] loadObjectToList(List<Object> ObjectToChange, List<Object> staticObject) {
        bot = 0;
        player = null;
        MAP = null;
        Controller.kichThuocVuNo = 1;
        int row = 0;
        int col = 0;
        try {
            String urlMap = new File("").getAbsolutePath() + "\\levels\\";
            File file = new File(urlMap + "Level1.txt");
            InputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String firstLine[] = reader.readLine().split(" ");
            int level = Integer.parseInt(firstLine[0]);
            row = Integer.parseInt(firstLine[1]);
            col = Integer.parseInt(firstLine[2]);
            System.out.println("Level: " + level);
            System.out.println("Rows:  " + row);
            System.out.println("Colums:  " + col);
            MAP = new char[row][col];

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    char read = (char) reader.read();
                    if (read == '\n') j--;
                    else {MAP[i][j] = read;System.out.print(MAP[i][j]);}

                    if (read == '#') staticObject.add(new Wall(i * SCALESIZE, j * SCALESIZE));
                    if (read != '#')
                    {
                        staticObject.add(new Grass(i * SCALESIZE, j * SCALESIZE));
                        if (read == 'p')
                        {
                            player = new Bomber(i*SCALESIZE,j*SCALESIZE);
                            ObjectToChange.add(player);
                        }
                        else if(read == '*') ObjectToChange.add(new Brick(i*SCALESIZE,j*SCALESIZE));
                        else if(read == '1') {
                            ObjectToChange.add(new Balloom(i*SCALESIZE,j*SCALESIZE));
                            bot++;
                        }
                        else if(read == 'x')ObjectToChange.add(new Portal(i*SCALESIZE,j*SCALESIZE));
                        else if(read == 'f')ObjectToChange.add(new FrameItem(i*SCALESIZE,j*SCALESIZE));
                    }
                }
                System.out.println();

            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        int[] result = {row, col};
        return result;
    }
}
