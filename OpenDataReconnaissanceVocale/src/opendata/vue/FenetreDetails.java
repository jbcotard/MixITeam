package opendata.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.http.client.ClientProtocolException;

import opendata.modele.Clientws;
import opendata.modele.Evenement;
import opendata.modele.EvenementDetail;

public class FenetreDetails extends JFrame 
{
	private Clientws client;
	private EvenementDetail evenement;
	private String categorie;
	private String id;
	private String nom;
	
	public FenetreDetails(String id, String categorie, Clientws client, String nom)
	{
		this.client = client;
		this.categorie = categorie;
		this.id = id;
		this.nom = nom;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(350, 350);
		setLocationRelativeTo(null);
		float x = this.getX();
		this.setLocation((int) (x - 500), this.getY());
		setResizable(false);
		setTitle("Evenements semblables");
		createView();
	}
	
	public static int distance(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        // i == 0
        int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }

	private void createView()
	{
		JPanel panel = new JPanel();
		try {
			List<Evenement> evenements = client.getEvenements(categorie);
			
			for (int i=0;i<evenements.size();i++)
			{
				Evenement ev = evenements.get(i);
				evenements.get(i).setDistance(distance(nom, ev.getNameEvenement()));
			}
			Collections.sort(evenements, new Comparator<Evenement>() {
			    public int compare(Evenement tc1, Evenement tc2) {
			    	if (tc1.getDistance()<tc2.getDistance()){
			    		return -1;
			    	}
			    	if (tc1.getDistance()>tc2.getDistance()){
			    		return 1;
			    	}
			        return 0;
			    }
			});
			panel.setPreferredSize(new Dimension(350,350));
			JScrollPane scrollPane = new JScrollPane(panel);
			
			int max = 6;
			if (evenements.size()<max) max = evenements.size()-1;
			
			for (int i=1;i<max;i++)
			{
				Evenement e = evenements.get(i);
				JPanel pn = new JPanel();
				pn.setLayout(new GridBagLayout());
				GridBagConstraints c = new GridBagConstraints();
				c.gridwidth = GridBagConstraints.REMAINDER;
				JLabel lb_titre = new JLabel("<html><b>"+e.getNameEvenement()+"</b></html>");
				lb_titre.setFont(lb_titre.getFont().deriveFont(25));
				JLabel lb_commune = new JLabel(e.getCommune());
				lb_commune.setFont(lb_commune.getFont().deriveFont(18));
				pn.add(lb_titre,c);
				pn.add(lb_commune,c);
				if (i%2 == 0)
				{
					lb_commune.setBackground(Color.LIGHT_GRAY);
					lb_titre.setBackground(Color.LIGHT_GRAY);
					pn.setBackground(Color.LIGHT_GRAY);
				}
				else
				{
					lb_commune.setBackground(Color.GRAY);
					lb_titre.setBackground(Color.GRAY);
					pn.setBackground(Color.GRAY);
				}
				final String id = e.getId();
				final String nom = e.getNameEvenement();
				
				pn.setPreferredSize(new Dimension(350, 70));
				panel.add(pn, c);
				add(scrollPane);
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
