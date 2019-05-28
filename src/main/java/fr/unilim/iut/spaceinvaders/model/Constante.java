package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;

public class Constante {
	//Cosntantes d'espace de jeu
    public static final int ESPACEJEU_LONGUEUR = 300;
    public static final int ESPACEJEU_HAUTEUR = 200;
	public static final int coordoneeExtremeGauche = 0;
	
    //Constantes de vaisseau 
    public static final int VAISSEAU_LONGUEUR = 30;
    public static final int VAISSEAU_HAUTEUR = 20;
    public static final int VAISSEAU_VITESSE = 18;
    
    //Constantes d'envahisseur
    public static final int ENVA_LONGUEUR = 30;
    public static final int ENVA_HAUTEUR = 20;
    public static final int ENVA_VITESSE = 18;
    
    //Constantes de marques
	static final char MARQUE_FIN_LIGNE = '\n';
	static final char MARQUE_VIDE = '.';
	static final char MARQUE_VAISSEAU = 'V';
	static final char MARQUE_MISSILE = 'M';
	static final char MARQUE_ENVAHISSEUR = 'E';
	
	//Constantes de missile
	public static final int MISSILE_LONGUEUR = 8;
	public static final int MISSILE_HAUTEUR = 25;
	public static final int MISSILE_VITESSE = 22;
	
	//Constantes d'envahisseur
	public static final int ordonneeDefautPlacementEnvahisseur = 20;
	
	//Constantes de couleur de sprite
	public static final Color CouleurVaisseau = Color.gray;
	public static final Color CouleurMssile = Color.blue;
	public static final Color CouleurEnvahisseur = Color.red;
}
