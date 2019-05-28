package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import fr.unilim.iut.spaceinvaders.moteurjeu.DessinJeu;

public class DessinSpaceInvaders implements DessinJeu {

	private SpaceInvaders jeu;

	public DessinSpaceInvaders(SpaceInvaders spaceInvaders) {
		this.jeu = spaceInvaders;
	}

	@Override
	public void dessiner(BufferedImage im) {
		if (this.jeu.aUnVaisseau()) {
			Vaisseau vaisseau = this.jeu.recupererVaisseau();
			this.dessinerUnSprite(vaisseau, im);
		}
		if (this.jeu.aUnMissile()) {
			Missile missile = this.jeu.recupererMissile();
			this.dessinerUnSprite(missile, im);
		}
		if (this.jeu.aUnEnvahisseur()) {
			Envahisseur envahisseur = this.jeu.recupererEnvahisseur();
			this.dessinerUnSprite(envahisseur, im);
		}
	}

	private void dessinerUnSprite(Sprite sprite, BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		crayon.setColor(trouverCouleurSprite(sprite));
		crayon.fillRect(sprite.abscisseLaPlusAGauche(), sprite.ordonneeLaPlusBasse(), sprite.longueur(),sprite.hauteur());
	}
	
	private Color trouverCouleurSprite(Sprite sprite) {
		if (sprite instanceof Vaisseau) return Constante.CouleurVaisseau;
		else {
			if (sprite instanceof Envahisseur) return Constante.CouleurEnvahisseur;
			else return Constante.CouleurMssile;
		}
	}
}
