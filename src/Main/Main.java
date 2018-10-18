package Main;

import Functions.Generator;

import java.io.IOException;

public class Main  {




    public static void main(String[] args) throws IOException {
        String images="train-images.idx3-ubyte";
        String labels ="train-labels.idx1-ubyte";
        Generator gen= new Generator();
        gen.CreateTrainingSet(images,labels);

    }
}
