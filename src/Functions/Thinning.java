package Functions;

import Utilities.Pixel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Thinning {
    public BufferedImage ReadImage(String file) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;

    }
    public int[][] GenerateTable(BufferedImage img)
    {
        int [][] res= new int[img.getWidth()][img.getHeight()];
        for (int i = 0; i < img.getWidth(); i++) {
            for(int j=0;j<img.getHeight();j++){

                if(img.getRGB(i,j)==-1)
                {
                    res[i][j]=0;
                }
                else
                {
                    res[i][j]=0;

                }
            }
        }

        return res;
    }


    public List<Pixel> ImageThinning(String file) {
        BufferedImage img = ReadImage(file);
        List<Pixel> pixelList = new ArrayList<Pixel>();
        int[][] res= new int[img.getWidth()][img.getHeight()];
        //BufferedImage res = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        //Graphics2D g2 = res.createGraphics();
        //Graphics2D g = (Graphics2D) img.getGraphics();
        for (int i = 0; i < img.getWidth(); i++) {
            for(int j=0;j<img.getHeight();j++){
                res[i][j]=(int)(img.getRGB(i,j));
                if(res[i][j]==-1)
                {
                    pixelList.add(new Pixel(i,j,Color.WHITE));
                }
                else
                    {
                        pixelList.add(new Pixel(i,j,Color.BLACK));

                    }
            }
        }
            System.out.println("Size:  "+pixelList.size() );
        return pixelList;
    }

    public void save(List<Pixel> list, int n) throws IOException {
       BufferedImage image = new BufferedImage((int)Math.sqrt(list.size()), (int)Math.sqrt(list.size()), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        for(Pixel p:list)
        {
            g2.setColor(p.getCol());
            g2.fillRect(p.getX(), p.getY(), 1, 1);
        }

        String dir = Generator.class.getResource("/").getFile();
        ImageIO.write(image, "PNG", new File(dir + "/Thinned/img" + n + ".png"));
    }
}
