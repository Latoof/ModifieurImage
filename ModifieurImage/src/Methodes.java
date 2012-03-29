import java.awt.image.BufferedImage;
import java.util.SortedSet;
import java.util.TreeSet;

import boofcv.alg.misc.GPixelMath;
import boofcv.core.image.ConvertBufferedImage;
import boofcv.gui.image.ShowImages;
import boofcv.struct.image.ImageUInt8;
import boofcv.struct.image.MultiSpectral;


public class Methodes {

	/**
	 * There is no real perfect way that everyone agrees on for converting color images into gray scale
	 * images.  Two examples of how to convert a MultiSpectral image into a gray scale image are shown 
	 * in this example.
	 */
	public static BufferedImage convertToGray( BufferedImage input ) {
		// convert the BufferedImage into a MultiSpectral
		MultiSpectral<ImageUInt8> image = ConvertBufferedImage.convertFromMulti(input,null,ImageUInt8.class);
 
		ImageUInt8 gray = new ImageUInt8( image.width,image.height);
 
		// creates a gray scale image by averaging intensity value across pixels
		GPixelMath.bandAve(image, gray);
		BufferedImage outputAve = ConvertBufferedImage.convertTo(gray,null);
 
		// create an output image just from the first band
		BufferedImage outputBand0 = ConvertBufferedImage.convertTo(image.getBand(0),null);

		
		return outputAve;
	}
	
	public static BufferedImage convertToGrayMan( BufferedImage input, int preserve_color ) {
		
		BufferedImage output = input;
		MultiSpectral<ImageUInt8> image = ConvertBufferedImage.convertFromMulti(output,null,ImageUInt8.class);

		for ( int h = 0; h < image.getHeight(); h++ ) {
			for ( int w = 0; w < image.getWidth(); w++ ) {
				
				int max = -1;
				int major = -1;
				//TreeSet<Integer> set = new TreeSet<Integer>();
				for( int i = 0; i < image.getNumBands(); i++ ) {

					//set.add(image.getBand(i).get(w, h));
					if ( max != -1 && image.getBand(i).get(w, h) > max + 20 ) {
						max = image.getBand(i).get(w, h);
						major = i;
					}
					
					else if ( image.getBand(i).get(w, h) > max ) {
						max = image.getBand(i).get(w, h);
						major = -1;
					}
					
				
				}
					
				if ( major != preserve_color ) {
				
					int total = 0;
					for( int i = 0; i < image.getNumBands(); i++ ) {
	
						total += image.getBand(i).get(w, h);
					
					}
					
					double moy = total /  image.getNumBands();
					
					//System.out.println("ttt "+moy);
					
					if ( total != 0 ) {
						for( int i = 0; i < image.getNumBands(); i++ ) {
	
							image.getBand(i).set(w, h, (int)moy);
						
						}
					}
					
				}
				
			}
		}
		
		output = ConvertBufferedImage.convertTo(image, output);
		
		
		return output;

	}
	
}
