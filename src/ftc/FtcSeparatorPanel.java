package ftc;

import java.awt.Color;
import java.awt.Graphics;
 
import javax.swing.JPanel;

public class FtcSeparatorPanel extends JPanel {
	
	private static final long serialVersionUID = 572595899955197434L;
	
	protected Color leftColor;
    protected Color rightColor;
 
    public FtcSeparatorPanel(Color leftColor, Color rightColor) {
        this.leftColor = leftColor;
        this.rightColor = rightColor;
        setOpaque(false);
    }
 
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(leftColor);
        g.drawLine(0, 0, 0, getHeight());
        g.setColor(rightColor);
        g.drawLine(1, 0, 1, getHeight());
    }
}
