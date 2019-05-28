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

	//Constructeur
	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
	}

	//Getters
	private int coordonneExtremeDroite() {
		return this.longueur - 1;
	}
	public int getLongueur() {
		return longueur;
	}
	public Envahisseur getEnvahisseur() {
		return this.envahisseur;
	}
	public Vaisseau getVaisseau() {
		return this.vaisseau;
	}
	public Missile getMissile() {
		return this.missile;
	}

	//Initialisation jeu
	public void initialiserJeu() {
		Position positionVaisseau = new Position(this.longueur / 2, this.hauteur - 1);
		Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
		positionnerUnNouveauSprite(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE,'V');

		Position positionEnvahisseur = new Position(Constante.coordoneeExtremeGauche,Constante.ordonneeDefautPlacementEnvahisseur);
		Dimension dimensionEnvahisseur = new Dimension(Constante.ENVA_LONGUEUR, Constante.ENVA_HAUTEUR);
		positionnerUnNouveauSprite(dimensionEnvahisseur, positionEnvahisseur, Constante.ENVA_VITESSE,'E');
	}

	//Méthodes pour mode Console
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
	
	//Méthodes relatives au mode graphique
	@Override
	public void evoluer(Commande commandeUser) {

		if (commandeUser.gauche) {
			deplacerSpriteVersLaGauche(vaisseau);
		}

		if (commandeUser.droite) {
			deplacerSpriteVersLaDroite(vaisseau);
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
	
	//Autres méthodes
	public void positionnerUnNouveauSprite(Dimension dimension, Position position, int vitesse, char typeSprite) { //Type= E pour envahisseur et V pour vaisseau ; Sinon, rien
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
	
	//Méthode relative aux missiles
	public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {

		if ((vaisseau.hauteur() + dimensionMissile.hauteur()) > this.hauteur)
			throw new MissileException(
					"Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");

		this.missile = this.vaisseau.tirerUnMissile(dimensionMissile, vitesseMissile);
	}

	//Methodes de déplacement des sprites
	public void deplacerSpriteVersLaDroite(Sprite sprite) {
		if (sprite.abscisseLaPlusADroite() < (longueur - 1)) {
			sprite.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(sprite.abscisseLaPlusADroite(), sprite.ordonneeLaPlusHaute())) sprite.positionner(longueur - sprite.longueur(), sprite.ordonneeLaPlusHaute());
		}
	}
	public void deplacerSpriteVersLaGauche(Sprite sprite) {
		if (0 < sprite.abscisseLaPlusAGauche()) sprite.deplacerHorizontalementVers(Direction.GAUCHE);
		if (!estDansEspaceJeu(sprite.abscisseLaPlusAGauche(), sprite.ordonneeLaPlusHaute())) {
			sprite.positionner(0, sprite.ordonneeLaPlusHaute());
		}
	}

	//Methode relative au déplacement automatique de certains sprite
	public void deplacerEnvahisseur() {
		if (this.aUnEnvahisseur()) {
			if (this.aUnEnvahisseurQuiOccupeLaPosition(Constante.coordoneeExtremeGauche, this.envahisseur.getOrigine().ordonnee())) {
				Envahisseur.SeDirigeParDefautEtActuellementVersLaDroite = true;
			} else if (this.aUnEnvahisseurQuiOccupeLaPosition(coordonneExtremeDroite(),
					this.envahisseur.getOrigine().ordonnee())) {
				Envahisseur.SeDirigeParDefautEtActuellementVersLaDroite = false;
			}

			if (Envahisseur.SeDirigeParDefautEtActuellementVersLaDroite) {
				this.deplacerSpriteVersLaDroite(envahisseur);
			} else {
				this.deplacerSpriteVersLaGauche(envahisseur);
			}
		}
	}
	public void deplacerMissile() {
		if (this.aUnMissile()) {
			missile.deplacerVerticalementVers(Direction.HAUT_ECRAN);
			if (this.missile.ordonneeLaPlusBasse() < 0) {
				this.missile = null;
			}
		}
	}
}