package fr.unilim.iut.spaceinvaders.model;

public class Collision {
	
	public static boolean verifHautMissileSuperieurBasEnvahisseur(Sprite entity1,Sprite entity2) {
		boolean result = false;
		if (entity1.ordonneeLaPlusHaute()>=entity2.ordonneeLaPlusBasse()) {
			result=true;
		}
		return result;
	}
	
	public static boolean verifBasMissileInférieurHautEnvahisseur(Sprite entity1,Sprite entity2) {
		boolean result = false;
		if (entity1.ordonneeLaPlusBasse()<=entity2.ordonneeLaPlusHaute()) {
			result=true;
		}
		return result;
	}
	
	public static boolean verifDroiteMissileApresGaucheEnvahisseur(Sprite entity1,Sprite entity2) {
		boolean result = false;
		if (entity1.abscisseLaPlusADroite()>=entity2.abscisseLaPlusAGauche()) {
			result=true;
		}
		return result;
	}
	
	public static boolean verifGaucheMissileAvantDroiteEnvahisseur(Sprite entity1,Sprite entity2) {
		boolean result = false;
		if (entity1.abscisseLaPlusAGauche()<=entity2.abscisseLaPlusADroite()) {
			result=true;
		}
		return result;
	}
	
	public static boolean detecterCollision(Sprite entity1,Sprite entity2) {
		boolean collision =false;
		if ((verifHautMissileSuperieurBasEnvahisseur(entity1,entity2)||verifBasMissileInférieurHautEnvahisseur(entity1,entity2))&&(verifDroiteMissileApresGaucheEnvahisseur(entity1,entity2)||verifGaucheMissileAvantDroiteEnvahisseur(entity1,entity2))) {
			collision=true;
		}
		return collision;
	}
	
	
	
}
