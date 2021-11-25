package model;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class PolygonTool extends LineTool {
    
    private Point p0 = null;
    
    @Override
    public boolean setPoint(int x, int y) {
        if(p0 == null) {
            p0 = new Point(x, y);
        }
        return super.setPoint(x, y);
    }

    @Override
    public void reset() {
        this.resetPolygon();
    }
    
    private void resetPolygon() {
        this.p0 = null;
        super.reset();
    }

    @Override
    public void drawLine(BufferedImage bi, Color color) {

        Point pAux = new Point(p2.getX(), p2.getY());
        super.drawLine(bi, color);
        p1 = pAux;
    }
    
    @Override
    public void close(BufferedImage bi, Color color) {

        p2 = p0;
        super.drawLine(bi, color);
        resetPolygon();
    }
}
