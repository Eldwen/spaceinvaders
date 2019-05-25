package fr.unilim.iut.spaceinvaders.model;

public class Envahisseur extends Sprite {
	public static boolean DoitSeDirigerVersLaDroite;
			
	public Envahisseur(Dimension dimension, Position positionOrigine, int vitesse) {
		super(dimension, positionOrigine, vitesse);
		DoitSeDirigerVersLaDroite=true;
	}	
}
