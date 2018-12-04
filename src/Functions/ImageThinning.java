package Functions;

import Utilities.Position;
import javafx.geometry.Pos;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageThinning {

    List<Position> nbrs= new ArrayList<Position>();
    List<Position> toWhite = new ArrayList<Position>();
    Position P2 = new Position(0, -1);
    Position P3 = new Position(1, -1);
    Position P4 = new Position(1, 0);
    Position P5 = new Position(1, 1);
    Position P6 = new Position(0, 1);
    Position P7 = new Position(-1, 1);
    Position P8 = new Position(-1, 0);
    Position P9 = new Position(-1, -1);



    public int GetBlackpixels(int x, int y, int[][] table,List<Position> list) {
        int res = 0;

        for(Position pos:list){
            if(table[x+pos.getX()][y+pos.getY()]==1){
                res++;
            }
        }



        return res;
    }

    public int GetTransitionNumber(int x, int y, int[][] table,List<Position> list) {
        int count = 0;

        for(int j=0;j<list.size()-1;j++){

            if((table[x+list.get(j).getX()][y+list.get(j).getY()]==0)&&(table[x+list.get(j+1).getX()][y+list.get(j+1).getY()]==1)){

                count++;
            }
        }

        return count;
    }

    public int[][] DoThinning(String file) throws IOException {


        BufferedImage img = ReadImage(file);
        int[][] grid = GenerateTable(img);
        for (int i = 1; i < img.getWidth() - 1; i++) {
            for (int j = 1; j < img.getHeight() - 1; j++) {
                int black = GetBlackpixels(i, j, grid,nbrs);
                //System.out.println("Black "+black);
                int trans = GetTransitionNumber(i, j, grid,nbrs);
               // System.out.println("trans "+trans);
                if ((grid[i][j] == 1) && (black >= 2 || black <= 6) &&(trans==1)&&CheckWhitePixels(i,j,grid,P2,P4,P6)&&CheckWhitePixels(i,j,grid,P4,P6,P8)){
                    toWhite.add(new Position(i,j));
                }

            }
        }

        for(Position p:toWhite){
                grid[p.getX()][p.getY()]=0;
        }
        toWhite.clear();
       return grid;

    }

    public void DoThinning2(String file,int n,int loops,int lb) throws IOException {
        nbrs.add(P2);
        nbrs.add(P3);
        nbrs.add(P4);
        nbrs.add(P5);
        nbrs.add(P6);
        nbrs.add(P7);
        nbrs.add(P8);
        nbrs.add(P9);
        nbrs.add(P2);


        int [][]  step1=DoThinning(file);

            for(int k=0;k<loops;k++){
        for (int i = 1; i < step1.length - 1; i++) {
            for (int j = 1; j < step1[0].length - 1; j++) {
                int black = GetBlackpixels(i, j, step1,nbrs);
                //System.out.println("Black "+black);
                int trans = GetTransitionNumber(i, j, step1,nbrs);
                // System.out.println("trans "+trans);
                if ((step1[i][j] == 1) && (black >= 2 || black <= 6) &&(trans==1)&&CheckWhitePixels(i,j,step1,P2,P4,P8)&&CheckWhitePixels(i,j,step1,P2,P6,P8)){
                    toWhite.add(new Position(i,j));
                }

            }
        }

        for(Position p:toWhite){
            step1[p.getX()][p.getY()]=0;
        }
        toWhite.clear();

        }
        save(step1,n,lb);

    }

    public BufferedImage ReadImage(String file) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;

    }

    public int[][] GenerateTable(BufferedImage img) {
        int[][] res = new int[img.getWidth()][img.getHeight()];

        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {

                if (img.getRGB(i, j) == -1) {
                    res[i][j] = 0;
                    //white++;
                } else {
                    res[i][j] = 1;

                }
            }
        }

        //System.out.println("White pixels: " + white);
        return res;
    }

    public void save(int[][] list, int n,int lb) throws IOException {
        BufferedImage image = new BufferedImage(list.length, list.length, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();

        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                if (list[i][j] == 0) {
                    g2.setColor(Color.WHITE);
                    g2.fillRect(i, j, 1, 1);
                } else {
                    g2.setColor(Color.BLACK);
                    g2.fillRect(i, j, 1, 1);
                }
            }
        }


        String dir = Generator.class.getResource("/").getFile();
        ImageIO.write(image, "PNG", new File(dir + "/Thinned/"+lb+"/"+lb + n + ".png"));
    }
    public boolean CheckWhitePixels(int x, int y, int[][] table,Position p1,Position p2,Position p3){
        if((table[x+p1.getX()][y+p1.getY()])==0||(table[x+p2.getX()][y+p2.getY()])==0||(table[x+p3.getX()][y+p3.getY()])==0)
            return true;
        else
            return false;

    }
}
