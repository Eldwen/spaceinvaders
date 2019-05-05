package fr.unilim.iut.spaceinvaders;

public class Vaisseau {
	int x;
	int y;
	int longueur;
	int hauteur;
	
	public Vaisseau(int longueur, int hauteur) {
		this(longueur,hauteur,0,0);
	}
	
    public Vaisseau(int longueur, int hauteur, int x, int y) {
    	this.longueur=longueur;
    	this.hauteur=hauteur;
		this.x = x;
		this.y = y;
	}

    public void positionner(int x, int y) {
	    this.x = x;
	    this.y = y;
    }
    
    public boolean occupeLaPosition(int x, int y) {
	     return (estAbcisseCouverte(x) && estOrdonneeCouverte(y));
    }

	private boolean estOrdonneeCouverte(int y) {
		return (ordonneeLaPlusBasse()<=y) && (y<=ordonneeLaPlusHaute());
	}

	private int ordonneeLaPlusHaute() {
		return this.y;
	}

	private int ordonneeLaPlusBasse() {
		return ordonneeLaPlusHaute()-this.hauteur+1;
	}

	private boolean estAbcisseCouverte(int x) {
		return (abcisseLaPlusAGauche()<=x) && (x<=abcisseLaPlusADroite());
	}

	public int abcisseLaPlusADroite() {
		return this.x+this.longueur-1;
	}

    public void seDeplacerVersLaDroite() {
	      this.x = this.x + 1 ;
    }
    
    public void seDeplacerVersLaGauche() {
	      this.x = this.x - 1 ;
  }
    
   	public int abcisseLaPlusAGauche() {
        return this.x;
	}
}
