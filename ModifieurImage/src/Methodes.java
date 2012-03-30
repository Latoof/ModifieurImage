import java.awt.image.BufferedImage;
import java.util.SortedSet;
import java.util.TreeSet;

import boofcv.alg.misc.GPixelMath;
import boofcv.core.image.ConvertBufferedImage;
import boofcv.gui.image.ShowImages;
import boofcv.struct.image.ImageUInt8;
import boofcv.struct.image.MultiSpectral;


public class Methodes {
	
	
	public static BufferedImage convertToGray( BufferedImage input, int preserve_color ) {
		
		BufferedImage output = input;
		
		/* Conversion en une strcture tri-bande (RGB) */
		MultiSpectral<ImageUInt8> image = ConvertBufferedImage.convertFromMulti(output,null,ImageUInt8.class);

		/* Pour chaque pixel de l'image */
		for ( int h = 0; h < image.getHeight(); h++ ) {
			for ( int w = 0; w < image.getWidth(); w++ ) {
				
				int max = -1;
				int major = -1;

				/* Pour chaque bande de couleur (RGB) */
				for( int i = 0; i < image.getNumBands(); i++ ) {

					/* Si une des couleurs suplombe d'au moins 10% les autres ... */
					if ( max != -1 && image.getBand(i).get(w, h) > max + 20 ) {
						max = image.getBand(i).get(w, h);
						major = i;
					}
					
					else if ( image.getBand(i).get(w, h) > max ) {
						max = image.getBand(i).get(w, h);
						major = -1;
					}
					
				
				}
				
				/* ... alors le pixel correspondant ne sera pas grise */
				if ( major != preserve_color ) {
				
					int total = 0;
					for( int i = 0; i < image.getNumBands(); i++ ) {
	
						total += image.getBand(i).get(w, h);
					
					}
					
					double moy = total /  image.getNumBands();
							
					/* Pour griser un pixel, on fait la moyenne de la valeur des trois bandes de couleur, 
					 * et on l'assigne a toutes les bandes. Si l'image devait etre uniquement en niveaux de gris (sans couleur du tout),
					 * ou pourrait alors n'utiliser qu'une seule bande.
					 */
					if ( total != 0 ) {
						for( int i = 0; i < image.getNumBands(); i++ ) {
	
							image.getBand(i).set(w, h, (int)moy);
						
						}
					}
					
				}
				
				
			}
		}
		
		/* Reconversion en buffer de pixels pour le renvoi */
		output = ConvertBufferedImage.convertTo(image, output);
		
		
		return output;

	}
	
}
