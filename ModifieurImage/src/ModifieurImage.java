import java.awt.image.BufferedImage;
import java.util.List;

import boofcv.gui.image.ShowImages;
import boofcv.io.image.UtilImageIO;


public class ModifieurImage {

	/* Pour le moment, la methode fait une conversion de l'image en niveaux de gris. En preservant eventuellement une couleur */
	public BufferedImage traiter( BufferedImage image, List<Double> list ) {
		
		/* Detection d'une couleur dominante */
		int max_index = -1;
		double max_value = -1;
		for (int i=0; i<list.size(); i++) {
			if ( list.get(i) > max_value ) {
				max_value = list.get(i);
				max_index = i;
				
			}
		}
		
		/* Mise en niveaux de gris. Le deuxieme argument est le canal correspondant a la couleur a préserver */
		BufferedImage imageOut = Methodes.convertToGray( image, max_index );
		
		return imageOut;
		
	}
	
	public static void main( String[] args ) {
	
		BufferedImage input = UtilImageIO.loadImage("res/img.jpg");

		BufferedImage output = Methodes.convertToGray(input,1);
		ShowImages.showWindow(output,"gray");
	}
	
	
}
