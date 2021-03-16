package view;
import java.awt.*;
import java.util.List;

import javax.swing.*;

public class TacheGraph extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private List<List<Object>> listInfosGraph;
	
	public TacheGraph (List<List<Object>> listInfosGraph){
		this.listInfosGraph = listInfosGraph;
        setPreferredSize(new Dimension(10000,10000));

	}
	
	@SuppressWarnings("unchecked")
	public void paint(Graphics g) {
		super.paint(g);
		for(List<Object> infoGraphs: listInfosGraph){
			int x = (int) infoGraphs.get(0);
			int y = (int) infoGraphs.get(1);
			int r = (int) infoGraphs.get(2);
			String c1;
			try{
				c1 = Integer.toString((int) infoGraphs.get(3));
			}catch(Exception e){
				c1 = (String) infoGraphs.get(3);
			}
			String c2 = Integer.toString((int) infoGraphs.get(4));
			String c3 = Integer.toString((int) infoGraphs.get(5));

			Color c = g.getColor();
			g.setColor(Color.BLACK);
			g.fillOval(x,y,r,r);
			g.setColor(c);
			g.setColor(Color.WHITE);
			g.fillOval(x+3,y+3,r-6,r-6);
			g.setColor(c);
			g.drawLine(x, y+r/2, x+r, y+r/2);
			g.drawLine(x+r/2, y+r/2, x+r/2, y+r);
			
			int hC1 = 0;
			for(int j = 0; j <= c1.length(); j++){
				 hC1 += 2 ;
			}
			int hC2 = 0;
			for(int j = 0; j <= c2.length(); j++){
				hC2 += 2 ;
			}
			
			int hC3 = 0;
			for(int j = 0; j <= c3.length(); j++){
				hC3 += 2 ;
			}
			g.drawString(c1, x+r/2-hC1, y+r/3);
			g.drawString(c2, x+r*2/3-hC2, y+r*3/4);
			g.drawString(c3, x+r/3-hC3, y+r*3/4);
			
			List<List<Integer>> allTrait =  (List<List<Integer>>) infoGraphs.get(6);
			for(List<Integer> trait: allTrait){
				if(trait.get(4) == 1){
					g.setColor(Color.RED);
				}else{
					g.setColor(Color.BLACK);
				}
				g.drawLine(trait.get(0), trait.get(1), trait.get(2), trait.get(3));
			}
			g.setColor(Color.BLACK);

		}
		
	}
	
 

}
   