package model.filters.impl;

import model.filters.Filter;
import model.filters.masks.Mask;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 * @created 07/04/2010
 */
public class MaskFilter implements Filter {

    List<Mask> masks = new ArrayList<Mask>();

    public void addMask(int index, Mask mask) {
        masks.add(index, mask);
    }

    public void addMask(Mask mask) {
        masks.add(mask);
    }

    public Image filter(Image image) {
        for(Mask mask: masks)
            image = mask.apply(image);
        return image;
    }

}
