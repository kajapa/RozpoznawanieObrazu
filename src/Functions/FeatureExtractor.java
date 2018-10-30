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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public int GetPixelNumber(BufferedImage img) {

        int count = 0;
        for (int i = 1; i < img.getWidth()-1; i++) {
            for (int j = 1; j < img.getHeight()-1; j++) {
                if (img.getRGB(i, j) == Color.BLACK.getRGB()) {

                    count++;
                }
            }
        }

        System.out.println(count);
        return count;

    }
    public int GetCross(BufferedImage img){
        int count=0;
        for (int i = 1; i < img.getWidth()-1; i++) {
            for (int j = 1; j < img.getHeight() - 1; j++) {
                if ((img.getRGB(i, j) == Color.BLACK.getRGB())
                        && img.getRGB(i + P2.getX(), j + P2.getY()) == Color.BLACK.getRGB()
                        && img.getRGB(i + P4.getX(), j + P4.getY()) == Color.BLACK.getRGB()
                        && img.getRGB(i + P8.getX(), j + P8.getY()) == Color.BLACK.getRGB()) {

                    count++;
                }
                else if((img.getRGB(i, j) == Color.BLACK.getRGB())
                        && img.getRGB(i + P2.getX(), j + P2.getY()) == Color.BLACK.getRGB()
                        && img.getRGB(i + P4.getX(), j + P4.getY()) == Color.BLACK.getRGB()
                        && img.getRGB(i + P6.getX(), j + P6.getY()) == Color.BLACK.getRGB()) {

                    count++;
                }
                else if((img.getRGB(i, j) == Color.BLACK.getRGB())
                        && img.getRGB(i + P4.getX(), j + P4.getY()) == Color.BLACK.getRGB()
                        && img.getRGB(i + P6.getX(), j + P6.getY()) == Color.BLACK.getRGB()
                        && img.getRGB(i + P8.getX(), j + P8.getY()) == Color.BLACK.getRGB()) {

                    count++;
                }
                else if((img.getRGB(i, j) == Color.BLACK.getRGB())
                        && img.getRGB(i + P2.getX(), j + P2.getY()) == Color.BLACK.getRGB()
                        && img.getRGB(i + P6.getX(), j + P6.getY()) == Color.BLACK.getRGB()
                        && img.getRGB(i + P8.getX(), j + P8.getY()) == Color.BLACK.getRGB()) {

                    count++;
                }
            }

        }
        return count;}



    public int GetLineEnds(BufferedImage img) {
        int count=0;
        FillList();
        for (int i = 1; i < img.getWidth()-1; i++) {
            for (int j = 1; j < img.getHeight()-1; j++) {
                if (img.getRGB(i, j) == Color.BLACK.getRGB()) {
                    for(Position pos :nbrs){
                        if((img.getRGB(i, j)== Color.BLACK.getRGB())&&(img.getRGB(i+pos.getX(), j+pos.getY())== Color.BLACK.getRGB()))
                            count++;
                    }
                }
            }
        }
        return count;
    }

    public void Extract(String in, String out) {
        BufferedImage img = ReadImage(in);
        int ln= GetLineEnds(img);
        int cn=GetCross(img);
        System.out.println("Lines "+ln);
        System.out.println("Cross "+cn);


        int bp = GetPixelNumber(img);
        out = dir + "/FeatureExtractor/" + out + ".txt";
        try {
            // Assume default encoding.
            FileWriter fileWriter =
                    new FileWriter(out);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write("BP: " + bp);
            bufferedWriter.newLine();
            bufferedWriter.write("LN: "+ln);
            bufferedWriter.newLine();
            bufferedWriter.write("CN: "+cn);
            bufferedWriter.write(" the text to the file.");

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
