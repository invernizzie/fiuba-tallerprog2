package model.filters.masks.impl;

import java.awt.Image;

import model.filters.masks.Mask;

/**
 * @author Esteban I. Invernizzi
 * @created 08/04/2010
 */
public class NormalizedMask implements Mask {

    // TODO Solucionar la repeticion de codigo de ClampingMask

    private double[][] coefs;
    private int width, height, offset;
    private double factor;
    int imgpixels[], newimgpixels[];

    public static NormalizedMask create(double[][] coefs, int offset, double factor) throws InvalidMaskException {

        testCreate(coefs);
        NormalizedMask result = new NormalizedMask();
        result.coefs = coefs;
        result.offset = offset;
        result.factor = factor;
        return result;
    }

    public Image apply(Image image) {
        NormalizedMaskConvolver convolver = new NormalizedMaskConvolver();
        return convolver.filter(image);
    }

    private static void testCreate(double[][] coefs) throws InvalidMaskException {
        int width = coefs.length;
        if ((width < 3) || (width % 2 == 0))
            throw new InvalidMaskException();
        int height = coefs[0].length;
        if ((height < 3) || (height % 2 == 0))
            throw new InvalidMaskException();
        for(int i = 0; i < coefs.length; i++)
            if (coefs[i].length != height)
                throw new InvalidMaskException();
    }

    private NormalizedMask() {}

    private class NormalizedMaskConvolver extends Convolver {

        @Override
        public void convolve() {

            for(int y=1; y<height-1; y++) {
                for(int x=1; x<width-1; x++) {
                    int newR = 0;
                    int newG = 0;
                    int newB = 0;

                    for(int k=-1; k<=1; k++) {
                        for(int j=-1; j<=1; j++){
                            int rgb = getImgPixel(x+j, y+k);
                            int r = (rgb >> 16) & 0xff;
                            int g = (rgb >> 8) & 0xff;
                            int b = rgb & 0xff;

                            newR += r * coefs[j+1][k+1];
                            newG += g * coefs[j+1][k+1];
                            newB += b * coefs[j+1][k+1];
                        }
                    }
                    setNewImgPixel(x, y,
                            (0xff000000 | clamp(newR) << 16 | clamp(newG) << 8 | clamp(newB)));
                }
            }
        }

        private int clamp(int c) {
            /* TODO Implementar una normalizacion con cambio de
             * escala para filtros Laplaciano, Gaussiano y de Prewitt */
            if (c > 255)
                return (c * factor) > 255 ? 255 : ((int)(c * factor)); 
            if (c + offset > 255)
                if (c * factor > 255)
                    return 255;
                else
                    return (int)(c * factor);
            return c + offset;
        }

    }

}
