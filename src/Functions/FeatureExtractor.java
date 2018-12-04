package Functions;

import Utilities.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FeatureExtractor {
    Position P2 = new Position(0, -1);
    Position P3 = new Position(1, -1);
    Position P4 = new Position(1, 0);
    Position P5 = new Position(1, 1);
    Position P6 = new Position(0, 1);
    Position P7 = new Position(-1, 1);
    Position P8 = new Position(-1, 0);
    Position P9 = new Position(-1, -1);
    List<Position> nbrs = new ArrayList<Position>();


    public void FillList() {
        nbrs.add(P2);
        nbrs.add(P3);
        nbrs.add(P4);
        nbrs.add(P5);
        nbrs.add(P6);
        nbrs.add(P7);
        nbrs.add(P8);
        nbrs.add(P9);
        nbrs.add(P2);
    }

    final static Charset ENCODING = StandardCharsets.UTF_8;
    String dir = Generator.class.getResource("/").getFile();

    public BufferedImage ReadImage(String file) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;

    }

    public int GetBlackPixelNumber(BufferedImage img) {

        int count = 0;
        for (int i = 1; i < img.getWidth() - 1; i++) {
            for (int j = 1; j < img.getHeight() - 1; j++) {
                if (img.getRGB(i, j) == Color.BLACK.getRGB()) {

                    count++;
                }
            }
        }


        return count;

    }

    public int GetWhitePixelNumber(BufferedImage img) {

        int count = 0;
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                if (img.getRGB(i, j) == Color.WHITE.getRGB()) {

                    count++;
                }
            }
        }


        return count;

    }

    public int GetCross(BufferedImage img) {
        int count = 0;
        for (int i = 1; i < img.getWidth() - 1; i++) {
            for (int j = 1; j < img.getHeight() - 1; j++) {
                if ((img.getRGB(i, j) == Color.BLACK.getRGB())
                        && img.getRGB(i + P2.getX(), j + P2.getY()) == Color.BLACK.getRGB()
                        && img.getRGB(i + P4.getX(), j + P4.getY()) == Color.BLACK.getRGB()
                        && img.getRGB(i + P8.getX(), j + P8.getY()) == Color.BLACK.getRGB()) {

                    count++;
                } else if ((img.getRGB(i, j) == Color.BLACK.getRGB())
                        && img.getRGB(i + P2.getX(), j + P2.getY()) == Color.BLACK.getRGB()
                        && img.getRGB(i + P4.getX(), j + P4.getY()) == Color.BLACK.getRGB()
                        && img.getRGB(i + P6.getX(), j + P6.getY()) == Color.BLACK.getRGB()) {

                    count++;
                } else if ((img.getRGB(i, j) == Color.BLACK.getRGB())
                        && img.getRGB(i + P4.getX(), j + P4.getY()) == Color.BLACK.getRGB()
                        && img.getRGB(i + P6.getX(), j + P6.getY()) == Color.BLACK.getRGB()
                        && img.getRGB(i + P8.getX(), j + P8.getY()) == Color.BLACK.getRGB()) {

                    count++;
                } else if ((img.getRGB(i, j) == Color.BLACK.getRGB())
                        && img.getRGB(i + P2.getX(), j + P2.getY()) == Color.BLACK.getRGB()
                        && img.getRGB(i + P6.getX(), j + P6.getY()) == Color.BLACK.getRGB()
                        && img.getRGB(i + P8.getX(), j + P8.getY()) == Color.BLACK.getRGB()) {

                    count++;
                }
            }

        }
        return count;
    }


    public int GetLineEnds(BufferedImage img) {
        int count = 0;
        int temp;
        FillList();
        for (int i = 1; i < img.getWidth() - 1; i++) {
            for (int j = 1; j < img.getHeight() - 1; j++) {
                if (img.getRGB(i, j) == Color.BLACK.getRGB()) {
                    temp = 0;
                    for (Position pos : nbrs) {
                        if ((img.getRGB(i, j) == Color.BLACK.getRGB()) && (img.getRGB(i + pos.getX(), j + pos.getY()) == Color.BLACK.getRGB()))
                            temp++;

                    }
                    if (temp == 1) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int GetQuarterNumber(BufferedImage img, int number) {
        int count = 0;
        switch (number) {
            case 1:
                for (int i = 0; i < img.getWidth() / 2; i++) {
                    for (int j = 0; j < img.getHeight() / 2; j++) {
                        if (img.getRGB(i, j) == Color.BLACK.getRGB()) {
                            count++;
                        }
                    }
                }
                break;
            case 2:
                for (int i = img.getWidth() / 2; i < img.getWidth(); i++) {
                    for (int j = 0; j < img.getHeight() / 2; j++) {
                        if (img.getRGB(i, j) == Color.BLACK.getRGB()) {
                            count++;
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < img.getWidth() / 2; i++) {
                    for (int j = img.getHeight() / 2; j < img.getHeight(); j++) {
                        if (img.getRGB(i, j) == Color.BLACK.getRGB()) {
                            count++;
                        }
                    }
                }
                break;
            case 4:
                for (int i = img.getWidth() / 2; i < img.getWidth(); i++) {
                    for (int j = img.getHeight() / 2; j < img.getHeight(); j++) {
                        if (img.getRGB(i, j) == Color.BLACK.getRGB()) {
                            count++;
                        }
                    }
                }
                break;

            default:

        }
        return count;

    }

    public void Extract(String in, String out, int size,int num) {
        StringBuilder BP = new StringBuilder();
        StringBuilder WP = new StringBuilder();
        StringBuilder LN = new StringBuilder();
        StringBuilder CN = new StringBuilder();
        StringBuilder Q1 = new StringBuilder();
        StringBuilder Q2 = new StringBuilder();
        StringBuilder Q3 = new StringBuilder();
        StringBuilder Q4 = new StringBuilder();
        for (int i = 0; i < size; i++) {
            System.out.println("Current: "+i);
            BufferedImage img = ReadImage(in +num+ i + ".png");

            int bp = GetBlackPixelNumber(img);
            BP.append(bp).append(",");
            int wp = GetWhitePixelNumber(img);
            WP.append(wp).append(",");
            int ln = GetLineEnds(img);
            LN.append(ln).append(",");
            int cn = GetCross(img);
            CN.append(cn).append(",");
            int q1 = GetQuarterNumber(img, 1);
            Q1.append(q1).append(",");
            int q2 = GetQuarterNumber(img, 2);
            Q2.append(q2).append(",");
            int q3 = GetQuarterNumber(img, 3);
            Q3.append(q3).append(",");
            int q4 = GetQuarterNumber(img, 4);
            Q4.append(q4).append(",");
            //System.out.println("Lines " + ln);
            //System.out.println("Cross " + cn);


        }
        //BufferedImage img = ReadImage(in);
        /*int ln = GetLineEnds(img);
        int cn = GetCross(img);
        int wn = GetWhitePixelNumber(img);
        int q1=GetQuarterNumber(img,1);
        int q2=GetQuarterNumber(img,2);
        int q3=GetQuarterNumber(img,3);
        int q4=GetQuarterNumber(img,4);*/
        //System.out.println("Lines " + ln);
        //System.out.println("Cross " + cn);


        //int bp = GetBlackPixelNumber(img);
        out = dir + "/FeatureExtractor/" + out + ".txt";
        try {
            // Assume default encoding.
            FileWriter fileWriter =
                    new FileWriter(out,true);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.append("========================="+num+"===========================");
            bufferedWriter.newLine();
            bufferedWriter.append("Black Pixels: " + BP.toString());
            bufferedWriter.newLine();
            bufferedWriter.append("White Pixels: " + WP.toString());
            bufferedWriter.newLine();
            bufferedWriter.append("Lines Number: " + LN.toString());
            bufferedWriter.newLine();
            bufferedWriter.append("Crosses Number: " + CN.toString());
            bufferedWriter.newLine();
            bufferedWriter.append("First Quarter Number: " + Q1.toString());
            bufferedWriter.newLine();
            bufferedWriter.append("Second Quarter Number: " + Q2.toString());
            bufferedWriter.newLine();
            bufferedWriter.append("Third Quarter Number: " + Q3.toString());
            bufferedWriter.newLine();
            bufferedWriter.append("Fourth Quarter Number: " + Q4.toString());
            bufferedWriter.newLine();



            // Always close files.
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + out + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }


}
