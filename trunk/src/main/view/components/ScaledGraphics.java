package main.view.components;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

/**
 * @author Esteban I. Invernizzi (invernizzie@gmail.com)
 *         Date: 31/07/2010
 */
public class ScaledGraphics extends Graphics {

    private Graphics adaptedGraphics;
    private double xScale;
    private double yScale;

    public ScaledGraphics(Graphics adapted, double xScale, double yScale) {
        setAdaptedGraphics(adapted);
        setXScale(xScale);
        setYScale(yScale);
    }

    public ScaledGraphics(Graphics adapted, double scale) {
        this(adapted, scale, scale);
    }

    public Graphics create() {
        return adaptedGraphics.create();
    }

    @Override
    public void translate(int x, int y) {
        getAdaptedGraphics().translate(scaleX(x), scaleY(y));
    }

    public Color getColor() {
        return adaptedGraphics.getColor();
    }

    public void setColor(Color c) {
        adaptedGraphics.setColor(c);
    }

    public void setPaintMode() {
        adaptedGraphics.setPaintMode();
    }

    public void setXORMode(Color c1) {
        adaptedGraphics.setXORMode(c1);
    }

    public Font getFont() {
        return adaptedGraphics.getFont();
    }

    public void setFont(Font font) {
        adaptedGraphics.setFont(font);
    }

    public FontMetrics getFontMetrics() {
        return adaptedGraphics.getFontMetrics();
    }

    public FontMetrics getFontMetrics(Font f) {
        return adaptedGraphics.getFontMetrics(f);
    }

    public Rectangle getClipBounds() {
        return adaptedGraphics.getClipBounds();
    }

    @Override
    public void clipRect(int x, int y, int width, int height) {
        getAdaptedGraphics().clipRect(scaleX(x), scaleY(y), scaleX(width), scaleY(height));
    }

    @Override
    public void setClip(int x, int y, int width, int height) {
        getAdaptedGraphics().setClip(scaleX(x), scaleY(y), scaleX(width), scaleY(height));
    }

    public Shape getClip() {
        throw new UnsupportedOperationException();
        //return getAdaptedGraphics().getClip();
    }

    public void setClip(Shape clip) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void copyArea(int x, int y, int width, int height, int dx, int dy) {
        getAdaptedGraphics().copyArea(scaleX(x), scaleY(y), scaleX(width), scaleY(height), scaleX(dx), scaleY(dy));
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        getAdaptedGraphics().drawLine(scaleX(x1), scaleY(y1), scaleX(x2), scaleY(y2));
    }

    @Override
    public void fillRect(int x, int y, int width, int height) {
        getAdaptedGraphics().fillRect(scaleX(x), scaleY(y), scaleX(width), scaleY(height));
    }

    @Override
    public void clearRect(int x, int y, int width, int height) {
        getAdaptedGraphics().clearRect(scaleX(x), scaleY(y), scaleX(width), scaleY(height));
    }

    @Override
    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        getAdaptedGraphics().drawRoundRect(scaleX(x), scaleY(y), scaleX(width), scaleY(height), scaleX(arcWidth), scaleY(arcHeight));
    }

    @Override
    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        getAdaptedGraphics().fillRoundRect(scaleX(x), scaleY(y), scaleX(width), scaleY(height), scaleX(arcWidth), scaleY(arcHeight));
    }

    @Override
    public void drawOval(int x, int y, int width, int height) {
        getAdaptedGraphics().drawOval(scaleX(x), scaleY(y), scaleX(width), scaleY(height));
    }

    @Override
    public void fillOval(int x, int y, int width, int height) {
        getAdaptedGraphics().fillOval(scaleX(x), scaleY(y), scaleX(width), scaleY(height));
    }

    @Override
    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        getAdaptedGraphics().drawArc(scaleX(x), scaleY(y), scaleX(width), scaleY(height), startAngle, arcAngle);
    }

    @Override
    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        getAdaptedGraphics().fillArc(scaleX(x), scaleY(y), scaleX(width), scaleY(height), startAngle, arcAngle);
    }

    @Override
    public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawString(String str, int x, int y) {
        getAdaptedGraphics().drawString(str, scaleX(x), scaleY(y));
    }

    @Override
    public void drawString(AttributedCharacterIterator iterator, int x, int y) {
        getAdaptedGraphics().drawString(iterator, scaleX(x), scaleY(y));
    }

    @Override
    public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
        return getAdaptedGraphics().drawImage(img, scaleX(x), scaleY(y), observer);
    }

    @Override
    public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
        return getAdaptedGraphics()
                .drawImage(img, scaleX(x), scaleY(y), scaleX(width), scaleY(height), observer);
    }

    @Override
    public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
        return getAdaptedGraphics().drawImage(img, scaleX(x), scaleY(y), bgcolor, observer);
    }

    @Override
    public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
        return getAdaptedGraphics()
                .drawImage(img, scaleX(x), scaleY(y), scaleX(width), scaleY(height), bgcolor, observer);
    }

    @Override
    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
        return getAdaptedGraphics()
                .drawImage(img, scaleX(dx1), scaleY(dy1), scaleX(dx2), scaleY(dy2),
                        scaleX(sx1), scaleY(sy1), scaleX(sx2), scaleY(sy2), observer);
    }

    @Override
    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
        return getAdaptedGraphics()
                .drawImage(img, scaleX(dx1), scaleY(dy1), scaleX(dx2), scaleY(dy2),
                        scaleX(sx1), scaleY(sy1), scaleX(sx2), scaleY(sy2), bgcolor, observer);
    }

    public void dispose() {
        adaptedGraphics.dispose();
    }

    public void finalize() {
        super.finalize();
        adaptedGraphics.finalize();
    }

    public String toString() {
        return adaptedGraphics.toString();
    }

    @Deprecated
    public Rectangle getClipRect() {
        return adaptedGraphics.getClipRect();
    }

    public Graphics getAdaptedGraphics() {
        return adaptedGraphics;
    }

    public void setAdaptedGraphics(Graphics adaptedGraphics) {
        this.adaptedGraphics = adaptedGraphics;
    }

    public double getXScale() {
        return xScale;
    }

    public double getYScale() {
        return yScale;
    }

    private void setXScale(double xScale) {
        this.xScale = xScale;
    }

    private void setYScale(double yScale) {
        this.yScale = yScale;
    }

    private int scaleX(int x) {
        return (int)(x * xScale + 0.5);
    }

    private int scaleY(int y) {
        return (int)(y * yScale + 0.5);
    }
}
