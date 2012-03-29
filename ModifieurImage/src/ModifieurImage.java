import java.awt.image.BufferedImage;
import java.util.List;

import boofcv.gui.image.ShowImages;
import boofcv.io.image.UtilImageIO;


public class ModifieurImage {

	
	public BufferedImage traiter( BufferedImage image, List<Double> list ) {
		
		int max_index = -1;
		double max_value = -1;
		for (int i=0; i<list.size(); i++) {
			if ( list.get(i) > max_value ) {
				max_value = list.get(i);
				max_index = i;
				
			}
		}
		
		BufferedImage imageOut = Methodes.convertToGrayMan( image, max_index );
		
		return imageOut;
		
	}
	
	public static void main( String[] args ) {
		
	
		BufferedImage input = UtilImageIO.loadImage("res/img.jpg");

		BufferedImage output = Methodes.convertToGrayMan(input,1);
		ShowImages.showWindow(output,"gray");
	}
	
}
