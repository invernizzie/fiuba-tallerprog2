package main.model.filters.impl;

import main.model.filters.Filter;
import main.model.filters.masks.Mask;

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
