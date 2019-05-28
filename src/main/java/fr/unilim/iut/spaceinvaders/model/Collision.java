package fr.unilim.iut.spaceinvaders.model;

public class Collision {
	//Tests de Sprite se rentrant dedans par un coté
	public static boolean collisionParLeBas(Sprite entity1,Sprite entity2) {
		return (entity1.ordonneeLaPlusBasse()<=entity2.ordonneeLaPlusHaute() && entity1.ordonneeLaPlusHaute() >=entity2.ordonneeLaPlusHaute())||(entity1.ordonneeLaPlusHaute()>=entity2.ordonneeLaPlusHaute() && entity1.ordonneeLaPlusBasse()<=entity2.ordonneeLaPlusBasse());
	}
	public static boolean collisionParLeHaut(Sprite entity1,Sprite entity2) {
		return (entity1.ordonneeLaPlusHaute()>=entity2.ordonneeLaPlusBasse() && entity1.ordonneeLaPlusBasse()<=entity2.ordonneeLaPlusBasse())||(entity1.ordonneeLaPlusHaute()>=entity2.ordonneeLaPlusHaute() && entity1.ordonneeLaPlusBasse()<=entity2.ordonneeLaPlusBasse());
	}
	public static boolean collisionParlaGauche(Sprite entity1,Sprite entity2) {
		return (entity1.abscisseLaPlusADroite()>=entity2.abscisseLaPlusAGauche() && entity1.abscisseLaPlusAGauche()<=entity2.abscisseLaPlusAGauche())||(entity1.abscisseLaPlusAGauche()>=entity2.abscisseLaPlusAGauche() && entity1.abscisseLaPlusADroite()<=entity2.abscisseLaPlusADroite());
	}
	public static boolean collisionParLaDroite(Sprite entity1,Sprite entity2) {
		return (entity1.abscisseLaPlusAGauche()<=entity2.abscisseLaPlusADroite() && entity1.abscisseLaPlusADroite()>=entity2.abscisseLaPlusADroite())||(entity1.abscisseLaPlusAGauche()>=entity2.abscisseLaPlusAGauche() && entity1.abscisseLaPlusADroite()<=entity2.abscisseLaPlusADroite());
	}

	public static boolean detecterCollisionParUnCoteEtParLeHautOuLeBas(Sprite entity1,Sprite entity2) {
		return (collisionParLeBas(entity1,entity2)||collisionParLeHaut(entity1,entity2))&&(collisionParlaGauche(entity1,entity2)||collisionParLaDroite(entity1,entity2));
	}

}
