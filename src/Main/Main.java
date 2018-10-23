package Main;

import Functions.Generator;
import Functions.Thinning;

import java.awt.*;
import java.io.IOException;

public class Main  {




    public static void main(String[] args) throws IOException {
        String images="train-images.idx3-ubyte";
        String labels ="train-labels.idx1-ubyte";
        Generator gen= new Generator();
       // gen.CreateTrainingSet(images,labels);
        Thinning thin= new Thinning();
        Color myWhite = new Color(255, 255, 255);
        System.out.println("white: "+myWhite.getRGB());
        thin.ImageThinning("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Images/img0.png");
        thin.save(thin.ImageThinning("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Images/img0.png"),0);
    }
}
