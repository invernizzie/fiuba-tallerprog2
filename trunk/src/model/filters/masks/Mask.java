package model.filters.masks;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Esteban
 * Date: 07/04/2010
 * Time: 14:03:45
 * To change this template use File | Settings | File Templates.
 */
public interface Mask {

    Image apply(Image image);
}
