package control;

import control.MasksEnum;
import model.filters.masks.impl.InvalidMaskException;
import model.filters.impl.MaskFilter;
import model.filters.masks.Mask;
import model.filters.masks.impl.ClampingMask;
import model.filters.masks.impl.NormalizedMask;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 * @created 11/04/2010
 */
public class Constants {

    private static final Map<MasksEnum, Mask> masks =
            new HashMap<MasksEnum, Mask>();

    static {
    try {
        masks.put(MasksEnum.BLUR, ClampingMask.create(new double[][] {
                {1.0/9, 1.0/9, 1.0/9},
                {1.0/9, 1.0/9, 1.0/9},
                {1.0/9, 1.0/9, 1.0/9}}
            ));
        masks.put(MasksEnum.SHARPEN, ClampingMask.create(new double[][] {
                {-1.0/8, -1.0/8, -1.0/8},
                {-1.0/8,      2, -1.0/8},
                {-1.0/8, -1.0/8, -1.0/8}}
            ));
        masks.put(MasksEnum.LOW_PASS, ClampingMask.create(new double[][] {
                {     0, 1.0/10,      0},
                {1.0/10, 6.0/10, 1.0/10},
                {     0, 1.0/10,      0}}
            ));
        masks.put(MasksEnum.SMOOTH, ClampingMask.create(new double[][] {
                {1.0/16, 2.0/16, 1.0/16},
                {2.0/16, 4.0/16, 2.0/16},
                {1.0/16, 2.0/16, 1.0/16}}
            ));
        masks.put(MasksEnum.MID_PASS, ClampingMask.create(new double[][] {
                { 1.0, -2.0,  1.0},
                {-2.0,  5.0, -2.0},
                { 1.0, -2.0,  1.0}}
            ));
        masks.put(MasksEnum.GAUSS_LOW_PASS, ClampingMask.create(new double[][] {
                {1.0/9, 2.0/9, 1.0/9},
                {2.0/9, 4.0/9, 2.0/9},
                {1.0/9, 2.0/9, 1.0/9}}
            ));
        masks.put(MasksEnum.LAPLACIAN, ClampingMask.create(new double[][] {
                { 1.0, -2.0,  1.0},
                {-2.0,  4.0, -2.0},
                { 1.0, -2.0,  1.0}}
            ));
        masks.put(MasksEnum.PREWITT_1, NormalizedMask.create(new double[][] {
                { 1.0,  1.0,  1.0},
                { 0.0,  0.0,  0.0},
                {-1.0, -1.0, -1.0}},
            127, 0.1));
        masks.put(MasksEnum.PREWITT_2, NormalizedMask.create(new double[][] {
                {-1.0,  0.0,  1.0},
                {-1.0,  0.0,  1.0},
                {-1.0,  0.0,  1.0}},
            127, 0.1));
    } catch (InvalidMaskException e) {}
    }

    public static MaskFilter getMaskFilter(MasksEnum mask) throws NoSuchElementException {

        if (!masks.containsKey(mask))
            throw new NoSuchElementException("Mask " + mask.name() + " not registered.");

        MaskFilter result = new MaskFilter();
        result.addMask(masks.get(mask));
        return result;
    }

    public static MaskFilter getMaskFilter(MasksEnum[] _masks) {

        MaskFilter result = new MaskFilter();
        for(MasksEnum mask: _masks) {
            if (!masks.containsKey(mask))
                throw new NoSuchElementException("Mask " + mask.name() + " not registered.");
            result.addMask(masks.get(mask));
        }
        return result;
    }
}
