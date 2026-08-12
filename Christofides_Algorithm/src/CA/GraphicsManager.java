package CA;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GraphicsManager {

	public BufferedImage generateGraphImage(City[] cities, Graph graph, int mapSize, double tSPLowerBound) {
        BufferedImage image = new BufferedImage(
        		mapSize, mapSize+35, BufferedImage.TYPE_INT_RGB
            );
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, mapSize, mapSize+35);
        
        g.setColor(Color.black);
        for (Edge edge : graph.getSortedEdges()) {

            int x1 = cities[edge.getAdjacencyTableIndexX()].getPosX();
            int y1 = cities[edge.getAdjacencyTableIndexX()].getPosY();

            int x2 = cities[edge.getAdjacencyTableIndexY()].getPosX();
            int y2 = cities[edge.getAdjacencyTableIndexY()].getPosY();
            g.drawLine(x1, y1, x2, y2);
        }
        
        for(int i = 0; i < cities.length; i++) {
        	g.fillOval(cities[i].getPosX()-2, cities[i].getPosY()-2, 5, 5);
        }
        g.drawString("Total Graph Weight: "+(int)graph.getTotalGraphWeighting(), 10, mapSize+20);
        g.drawString("TSP LowerBound: "+(int)tSPLowerBound, mapSize-150, mapSize+20);
        
        g.dispose();
        
        return image;
	}

	
    public void showImage(BufferedImage image) {

        JFrame frame = new JFrame("My Image");

        JLabel label = new JLabel(new ImageIcon(image));

        frame.add(label);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
