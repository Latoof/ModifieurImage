import java.awt.image.BufferedImage;

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
 
		ShowImages.showWindow(outputAve,"Average");
		ShowImages.showWindow(outputBand0,"Band 0");
		
		return outputBand0;
	}
	
}
