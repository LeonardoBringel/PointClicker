package model;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class FillpolyTool extends PolygonTool {

    private int lowX = 1000;
    private int highX = -1;
    private int lowY = 1000;
    private int highY = -1;
    
    @Override
    public boolean setPoint(int x, int y) {
        
        if(x < lowX) {
            lowX = x;
        }
        
        if(x > highX) {
            highX = x;
        }
        
        if(y < lowY) {
            lowY = y;
        }
        
        if(y > highY) {
            highY = y;
        }
        return super.setPoint(x, y);
    }
    
    @Override
    public void reset() {
        this.resetFillpoly();
    }
    
    public void resetFillpoly() {
        this.lowX = 1000;
        this.highX = -1;
        this.lowY = 1000;
        this.highY = -1;
        
        super.reset();
    }
    
    @Override
    public void paint(BufferedImage bi, Color color) {
        
        boolean in;
        for(int yScan = lowY; yScan <= highY; yScan++) {
            
            in = false;
            for(int xScan = lowX; xScan < highX; xScan++) {
                
                if(bi.getRGB(xScan, yScan) == color.getRGB()) {
                    if(bi.getRGB(xScan+1, yScan) != color.getRGB()) {
                        in = !in;
                    }
                }
                
                if(in) {
                    if(bi.getRGB(xScan, yScan-1) == color.getRGB()) {
                        if(bi.getRGB(xScan-1, yScan) == color.getRGB()) {
                            Point.writePoint(xScan, yScan, bi, color);
                        }
                    }
                }
            }
        }
        resetFillpoly();
    }
}
