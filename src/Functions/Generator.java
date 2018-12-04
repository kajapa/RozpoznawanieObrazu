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

import static com.sun.javafx.scene.control.skin.Utils.getResource;


public class Generator {
    String dir = Generator.class.getResource("/").getFile();
    int a,b,c,d,e,f,g,h,i,j=0;

    public TrainingSet CreateTrainingSet(String imagefile, String labelsfile) throws IOException {

        TrainingSet trainingSet = new TrainingSet();
        List<double[]> samples = new ArrayList<double[]>();
        List<List<Double>> labels = new ArrayList<List<Double>>();
        List<List<Double>> images = new ArrayList<List<Double>>();



        DataInputStream brLabels = new DataInputStream(new FileInputStream(labelsfile));
        DataInputStream brImages = new DataInputStream(new FileInputStream(imagefile));

        int magicNumber = brImages.readInt();
        int ImagesNumber = brImages.readInt();
        int RowsNumber = brImages.readInt();
        int ColsNumber = brImages.readInt();
        int magicLabel = brLabels.readInt();
        int numberOfLabels = brLabels.readInt();
        BufferedImage image = new BufferedImage(ColsNumber, RowsNumber, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();


        int[] pixels = new int[RowsNumber * ColsNumber];
        for (int Counter = 0; Counter < ImagesNumber; Counter++) {
            List<Double> imageInput = new ArrayList<Double>();
            List<Double> exspectedOutput = new ArrayList<Double>();
            for (int i = 0; i < 10; i++)
                exspectedOutput.add(0d);

            for (int p = 0; p < pixels.length; p++) {
                int bb = brImages.readUnsignedByte();
               // byte b = (byte) bb;
              //  System.out.println(bb + " - " + b);
                pixels[p] = bb;

                imageInput.add((double) (bb / 255.0f)); //scale in 0 to 1 range
            }
            byte lbl = brLabels.readByte();
            int pos = lbl;
            exspectedOutput.set(pos, 1d); //modify exspected output

            labels.add(exspectedOutput);
            images.add(imageInput);

            for (int y = 0; y < RowsNumber; y++) {
                for (int x = 0; x < ColsNumber; x++) {
                    int idx = KeepinRange(255 - pixels[x + y * ColsNumber]);
                    g2.setColor(new Color(idx, idx, idx));
                    g2.fillRect(x, y, 1, 1);

                }
            }
            save(image,lbl);
            image.flush();


        }
        brImages.close();
        brLabels.close();
        for (int i = 0; i < images.size(); i++) {
            trainingSet.data.put(images.get(i), labels.get(i));
        }
        return trainingSet;
    }

    public void save(BufferedImage img,int lb) throws IOException {

        switch (lb) {
            case 0:
                ImageIO.write(img, "PNG", new File(dir + "/Images/0/"+lb+a+".png"));
                a++;
                break;
            case 1:
                ImageIO.write(img, "PNG", new File(dir + "/Images/1/"+lb+b+".png"));
                b++;
                break;
            case 2:
                ImageIO.write(img, "PNG", new File(dir + "/Images/2/"+lb+c+".png"));
                c++;
                break;
            case 3:
                ImageIO.write(img, "PNG", new File(dir + "/Images/3/"+lb+d+".png"));
                d++;
                break;
            case 4:
                ImageIO.write(img, "PNG", new File(dir + "/Images/4/"+lb+e+".png"));
                e++;
                break;
            case 5:
                ImageIO.write(img, "PNG", new File(dir + "/Images/5/"+lb+f+".png"));
                f++;
                break;
            case 6:
                ImageIO.write(img, "PNG", new File(dir + "/Images/6/"+lb+g+".png"));
                g++;
                break;
            case 7:
                ImageIO.write(img, "PNG", new File(dir + "/Images/7/"+lb+h+".png"));
                h++;
                break;
            case 8:
                ImageIO.write(img, "PNG", new File(dir + "/Images/8/"+lb+i+".png"));
                i++;
                break;
            case 9:
                ImageIO.write(img, "PNG", new File(dir + "/Images/9/"+lb+j+".png"));
                j++;
                break;

            default:
                break;
        }

    }

    public int KeepinRange(int a) {
        if (a > 255) {
            a = 255;
        }
        if (a < 0) {
            a = 0;
        }

        return a;
    }
}
