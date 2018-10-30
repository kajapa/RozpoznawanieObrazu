package Main;

import Functions.FeatureExtractor;
import Functions.Generator;
import Functions.ImageThinning;

import java.awt.*;
import java.io.IOException;

public class Main  {




    public static void main(String[] args) throws IOException {
        String images="train-images.idx3-ubyte";
        String labels ="train-labels.idx1-ubyte";
        String dir = Generator.class.getResource("/").getFile();
       // Generator gen= new Generator();
       //gen.CreateTrainingSet(images,labels);

        //ImageThinning IT= new ImageThinning();

        Color myWhite = new Color(255, 255, 255);
        System.out.println("black: "+Color.BLACK.getRGB());
      // thin.GetThinnedImage("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Images/img0.png");
        ImageThinning IT;
       // IT.DoThinning2("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Images/img"+2+".png",2,1);
        /*for(int i=0;i<60000;i++){
            IT= new ImageThinning();
            IT.DoThinning2("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Images/img"+i+".png",i,3);
            System.out.println("image: "+i);

        }*/
        System.out.println(dir);
        FeatureExtractor FE= new FeatureExtractor();
        FE.Extract("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Thinned/imgt2.png","imgt2");


    }
}
