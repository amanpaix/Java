/* 
 * Note: these methods are public in order for them to be used by other files
 * in this assignment; DO NOT change them to private.  You may add additional
 * private methods to implement required functionality if you would like.
 * 
 * You should remove the stub lines from each method and replace them with your
 * implementation that returns an updated image.
 */

// TODO: comment this file explaining its behavior

import java.util.*;
import acm.graphics.*;

public class ImageShopAlgorithms implements ImageShopAlgorithmsInterface {

	public GImage flipHorizontal(GImage source) {
		// TODO
		int[][] pixels = source.getPixelArray();
		int[][] flipImagePixels = new int[pixels.length][pixels[0].length];
		int c = pixels[0].length - 1;
		for(int i = 0; i < flipImagePixels.length; i++) {
			for(int j = 0; j < flipImagePixels[i].length; j++) {
				flipImagePixels[i][j] = pixels[i][c];
				c--;
			}
			c = pixels[i].length - 1;
		}
		GImage flipImage = new GImage(flipImagePixels);
		return flipImage;
	}

	public GImage rotateLeft(GImage source) {
		// TODO
		int[][] pixels = source.getPixelArray();
		int[][] leftImgPixels = new int[ pixels[0].length ][ pixels.length ]; 
		int c = pixels[0].length - 1;
		for(int i = 0; i < leftImgPixels[0].length; i++) {
			for(int j = 0; j < leftImgPixels.length; j++) {
				leftImgPixels[j][i] = pixels[i][c];
				c--;
			}
			c = pixels[i].length - 1;
		}
		GImage leftImage = new GImage(leftImgPixels);
		return leftImage;
	}

	public GImage rotateRight(GImage source) {
		// TODO
		int[][] pixels = source.getPixelArray();
		int[][] rightImgPixels = new int[ pixels[0].length ][ pixels.length ]; 
		int c = 0;
		int r = pixels.length - 1;
		for(int i = 0; i < rightImgPixels[0].length; i++) {
			for(int j = 0; j < rightImgPixels.length; j++) {
				rightImgPixels[j][i] = pixels[r][c];
				c++;
			}
			r--;
			c = 0;
		}
		GImage rightImage = new GImage(rightImgPixels);
		return rightImage;
	}

	@SuppressWarnings("static-access")
	public GImage greenScreen(GImage source) {
		// TODO
		int[][] pixels = source.getPixelArray();
		for(int r = 0; r < pixels.length; r++) {
			for(int c = 0; c < pixels[r].length; c++) {
				int red = source.getRed(pixels[r][c]);
				int green = source.getGreen(pixels[r][c]);
				int blue = source.getBlue(pixels[r][c]);
				// you should treat a pixel as green if its green component is at least twice as
				// large as the maximum of its red and blue component. 
				int bigger = Math.max(red, blue);
				if( green >= bigger * 2 ) {
					pixels[r][c] = GImage.createRGBPixel(red, green, blue , 0);
				}				
			}
		}
		source.setPixelArray(pixels);
		return source;
	}

	public GImage equalize(GImage source) {
		// TODO
		return null;
	}

	@SuppressWarnings("static-access")
	public GImage negative(GImage source) {
		// TODO
		int[][] pixels = source.getPixelArray();
		for(int r = 0; r < pixels.length; r++) {
			for(int c = 0; c < pixels[r].length; c++) {
				int red = source.getRed(pixels[r][c]);
				int green = source.getGreen(pixels[r][c]);
				int blue = source.getBlue(pixels[r][c]);
				red = 255 - red;
				green = 255 - green;
				blue = 255 - blue;
				pixels[r][c] = GImage.createRGBPixel(red, green, blue);
			}
		}
		source.setPixelArray(pixels);
		return source;
	}

	public GImage translate(GImage source, int dx, int dy) {
		// TODO
		return null;
	}

	@SuppressWarnings("static-access")
	public GImage blur(GImage source) {
		// TODO
		int[][] pixels = source.getPixelArray();
		int[][] blurPixels = new int[pixels.length][pixels[0].length];
		
		for(int r = 0; r < pixels.length; r++) {
			for(int c = 0; c < pixels[r].length; c++) {
				int i = r - 1;
				int iend = r + 1;
				int j = c - 1;
				int jend = c + 1;
				if(i < 0) {
					i = 0;
				}
				if(j < 0) {
					j = 0;
				}
				if(iend > pixels.length - 1) {
					iend = r;
				}
				if(jend > pixels[r].length - 1) {
					jend = c;
				}

				int sumRed = 0;
				int sumBlue = 0;
				int sumGreen = 0;
				int count = 0;
				while(i <= iend) {
					while(j <= jend) {
						sumRed += source.getRed(pixels[i][j]);
						sumBlue += source.getBlue(pixels[i][j]);
						sumGreen += source.getGreen(pixels[i][j]);
						j++;
						count++;
					}
					j = c - 1;
					if(j <= 0) {
						j = 0;
					}
					i++;
					
				}
				int avgRed = sumRed / count;
				int avgBlue = sumBlue / count;
				int avgGreen = sumGreen / count;
				blurPixels[r][c] = GImage.createRGBPixel(avgRed , avgGreen , avgBlue);
			}
		}
		
		GImage blurImage = new GImage(blurPixels);
		return blurImage;
	}

}
