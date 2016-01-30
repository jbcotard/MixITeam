package opendata.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import opendata.ReconnaissanceVocale;
import opendata.modele.Clientws;
import opendata.modele.Evenement;

public class Panneau extends JPanel {
	private static String TEXTE_CATEGORIES = "Choisissez une categorie.";
	private JLabel lb_categories;
	private ReconnaissanceVocale reconnaissance;
	private String categorie;
	private Clientws client;
	JPanel listePanneaux;
	private JLabel lb_image;
	
	
	public Panneau ()
	{
		createView();
		reconnaissance = new ReconnaissanceVocale();
		client = new Clientws();
		addListener();
		
	}
	
	public void startCategorie()
	{
		try {
			categorie = reconnaissance.getCategorie();
			lb_categories.setVisible(false);
			List<Evenement> evenements = client.getEvenements(categorie);
			addCarousel(evenements);
			this.revalidate();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private void addCarousel(List<Evenement> liste)
	{
		
		for (int i=0;i<liste.size();i++)
		{
			Evenement e = liste.get(i);
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
			lb_titre.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					afficherEvenement(id, nom);
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			pn.setPreferredSize(new Dimension(750, 70));
			listePanneaux.add(pn, c);
		}
		try{
			lb_image.setIcon(new ImageIcon("./images/"+categorie+".jpg"));
		}catch (Exception e)
		{
			
		}
		listePanneaux.revalidate();
		this.revalidate();
		listePanneaux.repaint();
	}
	
	private void afficherEvenement(String id, String nom)
	{
		FenetreDetails fenetreDetails = new FenetreDetails(id, categorie, client, nom);
		fenetreDetails.setVisible(true);
		FenetreEvenement fenetreEvenement = new FenetreEvenement(id, categorie, client, nom);
		fenetreEvenement.setVisible(true);
	}
	
	private void createView(){
		lb_categories = new JLabel(TEXTE_CATEGORIES);
		listePanneaux = new JPanel();
		lb_image = new JLabel();
		lb_image.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listePanneaux.setLayout(new GridBagLayout());
		JScrollPane scrollPane = new JScrollPane(listePanneaux);
		setLayout(new BorderLayout());
		scrollPane.setPreferredSize(new Dimension(750, 550));
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		JPanel pn2 = new JPanel();
		pn2.setLayout(new GridBagLayout());
		pn2.add(lb_image,c);
		pn2.add(lb_categories,c);
		pn2.setSize(800, 50);
		add(pn2,BorderLayout.NORTH);
		add(scrollPane,BorderLayout.CENTER);
		setSize(800,600);
			
	}
	
	private void addListener(){
		lb_image.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				listePanneaux.removeAll();
				refresh();
				startCategorie();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void refresh()
	{
		listePanneaux.revalidate();
		this.revalidate();		
	}
	
	
	public void stopInterphone()
	{
		client.stop();
		reconnaissance.stop();
	}
	
	

}
