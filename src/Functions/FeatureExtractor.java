package Functions;

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

public class FeatureExtractor {
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
    public int GetPixelNumber(String file){
      BufferedImage img=  ReadImage(file);
      int count=0;
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight() ; j++) {
                if(img.getRGB(i,j)== Color.BLACK.getRGB()){
                    count++;
                }
            }}

            System.out.println(count);
    return count;

    }

    public void Extract(String in,String out){

        int bp= GetPixelNumber(in);
        out=dir + "/FeatureExtractor/"+out+".txt";
        try {
            // Assume default encoding.
            FileWriter fileWriter =
                    new FileWriter(out);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write("Black pixels: "+bp);
            bufferedWriter.write(" here is some text.");
            bufferedWriter.newLine();
            bufferedWriter.write("We are writing");
            bufferedWriter.write(" the text to the file.");

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + out + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }


}
