package fr.unilim.iut.spaceinvaders.model;

public class Collision {
	// Tests de Sprite se rentrant dedans par un coté
	public static boolean collisionParLeBas(Sprite sprite1, Sprite sprite2) {
		if (null != sprite1 && null != sprite2) {
			return (sprite1.ordonneeLaPlusBasse() <= sprite2.ordonneeLaPlusHaute()
					&& sprite1.ordonneeLaPlusHaute() >= sprite2.ordonneeLaPlusHaute())
					|| (sprite1.ordonneeLaPlusHaute() >= sprite2.ordonneeLaPlusHaute()
							&& sprite1.ordonneeLaPlusBasse() <= sprite2.ordonneeLaPlusBasse());
		} 
		else return false;
	}

	public static boolean collisionParLeHaut(Sprite sprite1, Sprite sprite2) {
		if (null != sprite1 && null != sprite2) {
			return (sprite1.ordonneeLaPlusHaute() >= sprite2.ordonneeLaPlusBasse()
					&& sprite1.ordonneeLaPlusBasse() <= sprite2.ordonneeLaPlusBasse())
					|| (sprite1.ordonneeLaPlusHaute() >= sprite2.ordonneeLaPlusHaute()
							&& sprite1.ordonneeLaPlusBasse() <= sprite2.ordonneeLaPlusBasse());
		} 
		else return false;
	}

	public static boolean collisionParlaGauche(Sprite sprite1, Sprite sprite2) {
		if (null != sprite1 && null != sprite2) {
			return (sprite1.abscisseLaPlusADroite() >= sprite2.abscisseLaPlusAGauche()
					&& sprite1.abscisseLaPlusAGauche() <= sprite2.abscisseLaPlusAGauche())
					|| (sprite1.abscisseLaPlusAGauche() >= sprite2.abscisseLaPlusAGauche()
							&& sprite1.abscisseLaPlusADroite() <= sprite2.abscisseLaPlusADroite());
		}
		else return false;
	}

	public static boolean collisionParLaDroite(Sprite sprite1, Sprite sprite2) {
		if (null != sprite1 && null != sprite2) {
			return (sprite1.abscisseLaPlusAGauche() <= sprite2.abscisseLaPlusADroite()
					&& sprite1.abscisseLaPlusADroite() >= sprite2.abscisseLaPlusADroite())
					|| (sprite1.abscisseLaPlusAGauche() >= sprite2.abscisseLaPlusAGauche()
							&& sprite1.abscisseLaPlusADroite() <= sprite2.abscisseLaPlusADroite());
		} 
		else return false;
	}

	public static boolean detecterCollisionParUnCoteEtParLeHautOuLeBas(Sprite sprite1, Sprite sprite2) {
		if (null != sprite1 && null != sprite2) {
			return (collisionParLeBas(sprite1, sprite2) || collisionParLeHaut(sprite1, sprite2))
					&& (collisionParlaGauche(sprite1, sprite2) || collisionParLaDroite(sprite1, sprite2));
		} 
		else return false;
	}
}
