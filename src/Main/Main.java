package Main;

import Functions.FeatureExtractor;
import Functions.Generator;
import Functions.ImageThinning;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        String images = "train-images.idx3-ubyte";
        String labels = "train-labels.idx1-ubyte";
        String dir = Generator.class.getResource("/").getFile();
        Generator gen = new Generator();
        // gen.CreateTrainingSet(images,labels);


        Color myWhite = new Color(255, 255, 255);
        //System.out.println("black: " + Color.BLACK.getRGB());

        //ImageThinning IT;

        // IT.DoThinning2("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Images/img"+2+".png",2,1);
        //==========0====================
        /*for(int i=0;i<5923;i++){
            IT= new ImageThinning();
            IT.DoThinning2("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Images/0/0"+i+".png",i,3,0);
            System.out.println("image 0: "+i);

        }
        //=========1======================
        for(int i=0;i<6742;i++){
            IT= new ImageThinning();
            IT.DoThinning2("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Images/1/1"+i+".png",i,3,1);
            System.out.println("image 1: "+i);

        }
        //================2=================
        for(int i=0;i<5958;i++){
            IT= new ImageThinning();
            IT.DoThinning2("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Images/2/2"+i+".png",i,3,2);
            System.out.println("image 2: "+i);

        }
        //============3===========================
        for(int i=0;i<6131;i++){
            IT= new ImageThinning();
            IT.DoThinning2("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Images/3/3"+i+"_"+".png",i,3,3);
            System.out.println("image 3: "+i);

        }
        //==============4=====================
        for(int i=0;i<5842;i++){
            IT= new ImageThinning();
            IT.DoThinning2("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Images/4/4"+i+".png",i,3,4);
            System.out.println("image 4: "+i);

        }
        //============5=========================
        for(int i=0;i<5421;i++){
            IT= new ImageThinning();
            IT.DoThinning2("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Images/5/5"+i+".png",i,3,5);
            System.out.println("image 5: "+i);

        }
        //===========6=======================
        for(int i=0;i<5918;i++){
            IT= new ImageThinning();
            IT.DoThinning2("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Images/6/6"+i+".png",i,3,6);
            System.out.println("image 6: "+i);

        }
        //===========7=====================
        for(int i=0;i<6265;i++){
            IT= new ImageThinning();
            IT.DoThinning2("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Images/7/7"+i+".png",i,3,7);
            System.out.println("image 7: "+i);

        }
        //==========8===================
        for(int i=0;i<5851;i++){
            IT= new ImageThinning();
            IT.DoThinning2("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Images/8/8"+i+".png",i,3,8);
            System.out.println("image 8: "+i);

        }
        //===============9============================
        for(int i=0;i<5949;i++){
            IT= new ImageThinning();
            IT.DoThinning2("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Images/9/9"+i+".png",i,3,9);
            System.out.println("image 9: "+i);

        }*/

        System.out.println(dir);
        FeatureExtractor FE = new FeatureExtractor();



        FE.Extract("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Thinned/0/", "data", 1000, 0);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        FE.Extract("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Thinned/1/", "data", 1000, 1);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        FE.Extract("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Thinned/2/", "data", 1000, 2);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        FE.Extract("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Thinned/3/", "data", 1000, 3);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        FE.Extract("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Thinned/4/", "data", 1000, 4);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        FE.Extract("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Thinned/5/", "data", 1000, 5);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        FE.Extract("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Thinned/6/", "data", 1000, 6);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        FE.Extract("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Thinned/7/", "data", 1000, 7);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        FE.Extract("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Thinned/8/", "data", 1000, 8);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        FE.Extract("D:/Studia/RozpoznawanieObrazu/out/production/RozpoznawanieObrazu/Thinned/9/", "data", 1000, 9);


        // Always wrap FileWriter in BufferedWriter.


    }
}
