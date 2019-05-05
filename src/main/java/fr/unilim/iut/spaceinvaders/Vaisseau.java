package fr.unilim.iut.spaceinvaders;

public class Vaisseau {
	Position origine;
	Dimension dimension;
	
	public Vaisseau(int longueur, int hauteur) {
		this(longueur,hauteur,0,0);
	}
	
    public Vaisseau(int longueur, int hauteur, int x, int y) {
    	this.dimension=new Dimension(longueur,hauteur);
    	this.origine=new Position (x,y);
	}

    public void positionner(int x, int y) {
		  this.origine.changerAbscisse(x);
		  this.origine.changerOrdonnee(y);
    }
    
    public boolean occupeLaPosition(int x, int y) {
	     return (estAbcisseCouverte(x) && estOrdonneeCouverte(y));
    }

	private boolean estOrdonneeCouverte(int y) {
		return (ordonneeLaPlusBasse()<=y) && (y<=ordonneeLaPlusHaute());
	}

	private int ordonneeLaPlusHaute() {
		return this.origine.ordonnee();
	}

	private int ordonneeLaPlusBasse() {
		return ordonneeLaPlusHaute()-this.dimension.hauteur()+1;
	}

	private boolean estAbcisseCouverte(int x) {
		return (abcisseLaPlusAGauche()<=x) && (x<=abcisseLaPlusADroite());
	}

	public int abcisseLaPlusADroite() {
		return this.origine.abscisse()+this.dimension.longueur()-1;
	}

	   public void seDeplacerVersLaDroite() {
		    this.origine.changerAbscisse(this.origine.abscisse()+1);
	   }

	
      public void seDeplacerVersLaGauche() {
		    this.origine.changerAbscisse(this.origine.abscisse()-1);
	  }
    
   	public int abcisseLaPlusAGauche() {
        return this.origine.abscisse();
	}
}
