package main;

import images.*;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.IOException;
import org.opencv.imgcodecs.Imgcodecs; 


/** main class */
public class FixImage {

    /**Main
     * our utility function receive five parameters : <file image> <file hole image> <connectivityNumber(4 or 8)> <z(parameter in weigh function)> <e parameter in weigh function>
     * **/
    public static void main( String[] args ) throws IOException
    {

    	
    	//input from comand line
    	//--------
    	float[][] matrix=InputParser.getMatrix(args[0],args[1]);
    	Connectivity connectivity=InputParser.getConnectivity(args[2]);
    	
		if (connectivity == null) {
			System.out.println("Illegal connectivity.");
			return;
		}

        int z = Integer.parseInt(args[3]);
        float e = Float.parseFloat(args[4]);
    	//---------
    	
        
        //input from the code
       //---------
    	/*int z=2;
    	float e=0.01f;

        float matrix [][]={
                {0.5f, 0.2f, 0.2f,0.5f},
                {0.3f, -1f, -1f,0.5f},
                {0.3f, -1f, -1f,0.5f},
                {0.5f, 0.6f, 0.6f,0.5f},
        };*/
       //---------
        
        

        
        float[][] result = fixHoleImage(matrix, connectivity, z, e);
        //printImage(result, 4,4);
        
        /*saving the matrix as a file*/
        Imgcodecs.imwrite("C:/fixedImage.jpg", result); 


    }

    /** print image function-just for checking use*/
    public static void printImage(float matrix [][],int width,int height) {
        for(int i = 0; i<height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    private static float[][] fixHoleImage(float[][] imageMatrix, Connectivity connectivity,
                                      int z, float e) {
        Image image = null;
        try {
            image = Image.buildImageFromMatrix(imageMatrix, connectivity);
        } catch (InvalidColorException e1) {
            e1.printStackTrace();
            return null;
        }

        Image fixedImage = ImageProcessingUtils.fixHoles(image,
                new DefaultWeightCalculator(z, e));
        


        return fixedImage.toMatrix();
    }


}
