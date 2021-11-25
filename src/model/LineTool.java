package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import static java.lang.Math.abs;

public class LineTool implements DrawingTool {

    protected Point p1 = null;
    protected Point p2 = null;

    private int d;
    private int incE;
    private int incNE;
    
    @Override
    public boolean setPoint(int x, int y) {
        
        if(p1 == null) {
            p1 = new Point(x, y);
            return false;
        } else {
            p2 = new Point(x, y);
            return true;
        }
    }
    
    @Override
    public void reset() {
        this.resetLine();
    }
    
    private void resetLine() {
        this.p1 = null;
        this.p2 = null;
    }
    
    @Override
    public void drawLine(BufferedImage bi, Color color) {
        
        if(p1 == null || p2 == null) {
            throw new NullPointerException("Ops! Sounds like a line needs two points. >.<");
        }
        
        //Ensuring that p2 is greater than p1
        crescentLine();
        
        //Determining delta
        int dx = abs(p2.getX() - p1.getX());
        int dy = abs(p1.getY() - p2.getY());
        
        if(dx > dy) { //Low Bresenham
            bresenhamInclination(dx, dy);
            
            boolean positive = p1.getY() > p2.getY();
            lowBresenham(bi, color, positive);
            
        } else { //High Bresenham
            bresenhamInclination(dy, dx);

            boolean positive = p1.getY() > p2.getY();
            highBresenham(bi, color, positive);
        }
        this.resetLine();
    }
    
    private void crescentLine() {
        if(p1.getX() > p2.getX()) {
            
            Point pAux = p1;
            p1 = p2;
            p2 = pAux;
        }
    }
    
    private void bresenhamInclination(int dHigher, int dLower) {
        d = 2 * dLower - dHigher;
        incE = 2 * dLower;
        incNE = 2 * (dLower - dHigher);
    }
    
    private void lowBresenham(BufferedImage bi, Color color, boolean positive) {
        
        Point.writePoint(p1, bi, color);
        while(p1.getX() < p2.getX()) {
            
            p1.increaseX();
            if(d <= 0) {
                d += incE;
            } else {
                d += incNE;
                
                if(positive) {
                    p1.decreaseY();
                } else {
                    p1.increaseY();
                }
            }
            Point.writePoint(p1, bi, color);
        }
    }
    
    private void highBresenham(BufferedImage bi, Color color, boolean positive) {
        
        Point.writePoint(p1, bi, color);
        while((positive) ? (p2.getY() < p1.getY()) : (p2.getY() > p1.getY())) {
            
            if(positive) {
                p1.decreaseY();
            } else {
                p1.increaseY();
            }
            
            if(d <= 0) {
                d += incE;
            } else {
                d += incNE;
                p1.increaseX();
            }
            Point.writePoint(p1, bi, color);
        }
    }
    
    @Override
    public void close(BufferedImage bi, Color color) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void paint(BufferedImage bi, Color color) {
        throw new UnsupportedOperationException();
    }
}
