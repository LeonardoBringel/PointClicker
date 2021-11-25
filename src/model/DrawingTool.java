package model;

import java.awt.Color;
import java.awt.image.BufferedImage;

public interface DrawingTool {
    
    public boolean setPoint(int x, int y);
    
    public void reset();
    
    public void drawLine(BufferedImage bi, Color color);
    
    public void close(BufferedImage bi, Color color);
    
    public void paint(BufferedImage bi, Color color);
}
