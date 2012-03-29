import java.awt.image.BufferedImage;
import java.util.List;

import boofcv.io.image.UtilImageIO;


public class ModifieurImage {

	
	public BufferedImage traiter( BufferedImage image, List<Double> list ) {
		
		BufferedImage imageOut = Methodes.convertToGray( image );
		
		return imageOut;
		
	}
	
	public static void main( String[] args ) {
		
		BufferedImage input = UtilImageIO.loadImage("res/sea.jpg");

		Methodes.convertToGray(input);
		
	}
	
}
