package opendata.vue;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.http.client.ClientProtocolException;

import opendata.modele.Clientws;
import opendata.modele.Evenement;
import opendata.modele.EvenementDetail;

public class FenetreEvenement extends JFrame 
{
	private Clientws client;
	private EvenementDetail evenement;
	private String categorie;
	private String id;
	private String nom;
	
	public FenetreEvenement(String id, String categorie, Clientws client, String nom)
	{
		this.client = client;
		this.categorie = categorie;
		this.id = id;
		this.nom = nom;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(350, 350);
		setLocationRelativeTo(null);
		float x = this.getX();
		this.setLocation((int) (x + 500), this.getY());
		setResizable(false);
		setTitle("Evenements semblables");
		createView();
	}
	
	private void createView()
	{
		JPanel panel = new JPanel();
		try {
			EvenementDetail evenement = client.getEvenement(id);
			panel.setPreferredSize(new Dimension(350,350));
			panel.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.gridwidth = GridBagConstraints.REMAINDER;
			panel.add(new JLabel("<html><span><b>"+evenement.getNameEvenement() +"</b></span></html>"),c);
			panel.add(new JLabel("<html><span><i>Adresse : "+evenement.getAdresse()+" - "+ evenement.getCommune()+"</i></span></html>"),c);
			if (evenement.getTarifGratuit() != null && !evenement.getTarifGratuit().equals("null"))
			{
				String res = "Oui";
				if (evenement.getTarifGratuit().equals("false"))
				{
					res = "Non";
				}
				panel.add(new JLabel("Gratuit : "+res),c);
			}
			if (evenement.getSite() != null)
				panel.add(new JLabel("<html>Site Web : "+evenement.getSite()+"</html>"),c);
			if (evenement.getMail() != null)
				panel.add(new JLabel("<html>Courriel : "+evenement.getMail()+"</html>"),c);
			
			add(panel);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
