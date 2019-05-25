package fr.unilim.iut.spaceinvaders.model;

import fr.unilim.iut.spaceinvaders.moteurjeu.Commande;
import fr.unilim.iut.spaceinvaders.moteurjeu.Jeu;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class SpaceInvaders implements Jeu {

	int longueur;
	int hauteur;
	Vaisseau vaisseau;
	Missile missile;
	Envahisseur envahisseur;
	Collision collision;

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
	}

	public int getLongueur() {
		return longueur;
	}

	public void initialiserJeu() {
		Position positionVaisseau = new Position(this.longueur / 2, this.hauteur - 1);
		Dimension dimensionVaisseau = new Dimension(Constante.SPRITE_LONGUEUR, Constante.SPRITE_HAUTEUR);
		positionnerUnNouveauSprite(dimensionVaisseau, positionVaisseau, Constante.SPRITE_VITESSE,'V');

		Position positionEnvahisseur = new Position(Constante.coordoneeExtremeGauche,Constante.ordonneeDefautPlacementEnvahisseur);
		Dimension dimensionEnvahisseur = new Dimension(Constante.SPRITE_LONGUEUR, Constante.SPRITE_HAUTEUR);
		positionnerUnNouveauSprite(dimensionEnvahisseur, positionEnvahisseur, Constante.SPRITE_VITESSE,'E');
	}

	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append(Constante.MARQUE_FIN_LIGNE);
		}
		return espaceDeJeu.toString();
	}

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (this.aUnVaisseauQuiOccupeLaPosition(x, y)) marque = Constante.MARQUE_VAISSEAU;
		else if (this.aUnMissileQuiOccupeLaPosition(x, y)) marque = Constante.MARQUE_MISSILE;
		else if (this.aUnEnvahisseurQuiOccupeLaPosition(x, y)) marque = Constante.MARQUE_ENVAHISSEUR;
		else marque = Constante.MARQUE_VIDE;
		return marque;
	}
	
	//Méthodes pour verifier la position d'un Sprite
	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		return this.aUnMissile() && missile.occupeLaPosition(x, y);
	}
	private boolean aUnEnvahisseurQuiOccupeLaPosition(int x, int y) {
		return this.aUnEnvahisseur() && envahisseur.occupeLaPosition(x, y);
	}
	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
	}
	
	//Méthodes pour vérifier la présence d'un sprite
	public boolean aUnEnvahisseur() {
		return envahisseur != null;
	}
	public boolean aUnMissile() {
		return missile != null;
	}
	public boolean aUnVaisseau() {
		return vaisseau != null;
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
	}
	
	public void positionnerUnNouveauSprite(Dimension dimension, Position position, int vitesse, char typeSprite) {
		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du sprite est en dehors de l'espace jeu");

		int longueurSprite = dimension.longueur();
		int hauteurSprite = dimension.hauteur();

		if (!estDansEspaceJeu(x + longueurSprite - 1, y))
			throw new DebordementEspaceJeuException(
					"Le sprite déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurSprite + 1))
			throw new DebordementEspaceJeuException(
					"Le sprite déborde de l'espace jeu vers le bas à cause de sa hauteur");

		//Mettre le type à E pour envahisseur et à V pour vaisseau
		if('E'==Character.toUpperCase(typeSprite)) envahisseur = new Envahisseur(dimension, position, vitesse);
		else if ('V'==Character.toUpperCase(typeSprite)) vaisseau = new Vaisseau(dimension, position, vitesse);
		else System.err.println("Erreur : Muvais type de Sprite inséré");

	}

	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1)) {
			vaisseau.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
				vaisseau.positionner(longueur - vaisseau.longueur(), vaisseau.ordonneeLaPlusHaute());
			}
		}
	}

	public void deplacerVaisseauVersLaGauche() {
		if (0 < vaisseau.abscisseLaPlusAGauche())
			vaisseau.deplacerHorizontalementVers(Direction.GAUCHE);
		if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusHaute())) {
			vaisseau.positionner(0, vaisseau.ordonneeLaPlusHaute());
		}
	}

	public Vaisseau recupererVaisseau() {
		return this.vaisseau;
	}

	public Missile recupererMissile() {
		return this.missile;
	}

	@Override
	public void evoluer(Commande commandeUser) {

		if (commandeUser.gauche) {
			deplacerVaisseauVersLaGauche();
		}

		if (commandeUser.droite) {
			deplacerVaisseauVersLaDroite();
		}

		if (commandeUser.tir && !this.aUnMissile())
			tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR),
					Constante.MISSILE_VITESSE);

		if (this.aUnMissile()) {
			this.deplacerMissile();
		}

		if (this.aUnEnvahisseur()) {
			this.deplacerEnvahisseur();
		}

	}

	@Override
	public boolean etreFini() {
		boolean fin = false;
		if (this.aUnEnvahisseur() && this.aUnMissile()) {
			if (Collision.detecterCollisionParUnCoteEtParLeHautOuLeBas(this.missile, this.envahisseur)) {
				fin = true;
			}
		}
		return fin;
	}

	public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {

		if ((vaisseau.hauteur() + dimensionMissile.hauteur()) > this.hauteur)
			throw new MissileException(
					"Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");

		this.missile = this.vaisseau.tirerUnMissile(dimensionMissile, vitesseMissile);
	}

	public void deplacerMissile() {
		if (this.aUnMissile()) {
			missile.deplacerVerticalementVers(Direction.HAUT_ECRAN);
			if (this.missile.ordonneeLaPlusBasse() < 0) {
				this.missile = null;
			}
		}
	}



	public void deplacerEnvahisseurVersLaDroite() {
		if (envahisseur.abscisseLaPlusADroite() < (longueur - 1)) {
			envahisseur.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(envahisseur.abscisseLaPlusADroite(), envahisseur.ordonneeLaPlusHaute())) {
				envahisseur.positionner(longueur - envahisseur.longueur(), envahisseur.ordonneeLaPlusHaute());
			}
		}
	}

	public void deplacerEnvahisseurVersLaGauche() {
		if (0 < envahisseur.abscisseLaPlusAGauche())
			envahisseur.deplacerHorizontalementVers(Direction.GAUCHE);
		if (!estDansEspaceJeu(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordonneeLaPlusHaute())) {
			envahisseur.positionner(0, envahisseur.ordonneeLaPlusHaute());
		}
	}

	private int coordonneExtremeDroite() {
		return this.longueur - 1;
	}

	private int coordonneeExtremeGauche() {
		return Constante.coordoneeExtremeGauche;
	}

	public void deplacerEnvahisseur() {

		if (this.aUnEnvahisseur()) {
			if (this.aUnEnvahisseurQuiOccupeLaPosition(coordonneeExtremeGauche(), recupererOrdonneeEnvahisseur())) {
				this.envahisseur.SeDirigeParDefautEtActuellementVersLaDroite = true;
			} else if (this.aUnEnvahisseurQuiOccupeLaPosition(coordonneExtremeDroite(),
					recupererOrdonneeEnvahisseur())) {
				this.envahisseur.SeDirigeParDefautEtActuellementVersLaDroite = false;
			}

			if (this.envahisseur.SeDirigeParDefautEtActuellementVersLaDroite) {
				this.deplacerEnvahisseurVersLaDroite();
			} else {
				this.deplacerEnvahisseurVersLaGauche();
			}
		}
	}

	private int recupererOrdonneeEnvahisseur() {
		return this.envahisseur.getOrigine().ordonnee();
	}

	public Envahisseur recupererEnvahisseur() {
		return this.envahisseur;
	}
}