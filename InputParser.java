package main;

import images.*;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class InputParser {
	
	public static float [][] getMatrix(String image,String hole ) throws IOException{
		
		  BufferedImage bufferedImage = ImageIO.read(new File(image));
		  BufferedImage holeImage = ImageIO.read(new File(hole));
		  float imageMatrix[][] = toMatrix(bufferedImage);
		  float holeMatrix[][] = toMatrix(holeImage);
		  float[][] combineImage = combineImageWithHole(imageMatrix , holeMatrix , bufferedImage.getWidth(), bufferedImage.getHeight());
		  return combineImage;
		
	}
	
	public static Connectivity getConnectivity(String connectivityNumberInput) {
		int connectivityNumber=Integer.parseInt(connectivityNumberInput);
		  return  connectivityNumber == 4 ?
		          Connectivity.FOUR : connectivityNumber == 8 ?
		          Connectivity.EIGHT : null;


	}

    /**
     * convert image to float matrix
     * return the matrix
     */
    private static float[][] toMatrix(BufferedImage image){

        Raster raster = image.getData();

        float[][] imageMatrix = new float[image.getWidth()][image.getHeight()];
        for(int i = 0;i < image.getWidth(); i++)
        {
            for(int j = 0;j < image.getHeight(); j++)
            {
                imageMatrix[i][j] = (float) raster.getSample(i, j, 0) / (float) 255;
            }
        }
        return imageMatrix;
    }


    /**
     * combine the image and the hole (we assume that we receive two image one is the image and the other is the hole- because there is no -1 pixel.)
     * return  matrix of combined image and hole
     */
    private static float[][] combineImageWithHole(float[][] image, float[][] holeImage, int width, int height)
    {
        for( int i = 0; i < width; i++ )
            for(int j = 0; j < height; j++)
                if (holeImage[i][j] != 0)
                    image[i][j] = Image.HOLE_VALUE;
        return image;
    }
	


}
