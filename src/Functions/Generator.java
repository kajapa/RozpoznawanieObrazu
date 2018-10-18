package Functions;

import Utilities.TrainingSet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Generator {
    public TrainingSet CreateTrainingSet(String imagefile, String labelsfile) throws IOException {

        TrainingSet trainingSet = new TrainingSet();
        List<double[]> samples = new ArrayList<double[]>();
        List<List<Double>> labels = new ArrayList<List<Double>>();
        List<List<Double>> images = new ArrayList<List<Double>>();



        DataInputStream brLabels = new DataInputStream(new FileInputStream(labelsfile));
        DataInputStream brImages = new DataInputStream(new FileInputStream(imagefile));
        int bt = brImages.readInt();
        int ImagesNumber = brImages.readInt();
        int RowsNumber = brImages.readInt();
        int ColsNumber = brImages.readInt();
        BufferedImage image = new BufferedImage(ColsNumber,RowsNumber,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();

        int bt2 = brLabels.readInt();
        int LabelsNumber = brLabels.readInt();
        byte[] pixels = new byte[RowsNumber * ColsNumber];
        for (int Counter = 0; Counter < ImagesNumber; Counter++) {
            List<Double> imageInput = new ArrayList<Double>();
            List<Double> exspectedOutput = new ArrayList<Double>();
            for (int i = 0; i < 10; i++)
                exspectedOutput.add(0d);

            for (int p = 0; p < pixels.length; p++) {
                byte b = brImages.readByte();
                pixels[p] = b;

                imageInput.add((double) (b / 255.0f)); //scale in 0 to 1 range
            }
            byte lbl = brLabels.readByte();
            int pos=lbl;
            exspectedOutput.set(pos,1d); //modify exspected output

            labels.add(exspectedOutput);
            images.add(imageInput);

            for (int y = 0; y < RowsNumber; y++)
            {
                for (int x = 0; x < ColsNumber; x++)
                {
                    g2.setColor(new Color(255 - pixels[x * y], 255 - pixels[x * y], 255 - pixels[x * y]));
                    g2.fillRect(x, y, 1, 1);

                }
            }
            save(image,Counter);
           image.flush();


        }
        brImages.close();
        brLabels.close();
        for (int i = 0; i < images.size(); i++)
        {
            trainingSet.data.put(images.get(i), labels.get(i));
        }
        return trainingSet;
    }
    public void save(BufferedImage img,int n) throws IOException {
        ImageIO.write(img, "PNG", new File("D:/Studia/Rozpoznawanie Obrazu/Imagesimg"+n+".png"));
    }
}
