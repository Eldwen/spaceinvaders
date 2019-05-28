package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Collision;
import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;

public class CollisionTest {
    private SpaceInvaders spaceinvaders;

    @Before
    public void initialisation() {
	    spaceinvaders = new SpaceInvaders(15, 10);
    }
	
    @Test
    public void test_verifHautMissile_Superieur_BasEnvahisseur() {
      spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2),new Position(0,4), 3,'E');
      spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2), new Position(0,9), 3,'V');
      spaceinvaders.tirerUnMissile(new Dimension(3,2), 2);
      spaceinvaders.deplacerMissile();
      assertEquals(true, Collision.collisionParLeBas(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
     }
    
    @Test
    public void test_verifBasMissile_Inferieur_HautEnvahisseur() {
      spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2),new Position(0,4), 3,'E');
      spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2), new Position(0,9), 3,'V');
      spaceinvaders.tirerUnMissile(new Dimension(3,2), 4);
      spaceinvaders.deplacerMissile();
      assertEquals(true, Collision.collisionParLeHaut(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
    }
    
    @Test
    public void test_verifDroiteMissile_Apres_GaucheEnvahisseur() {
      spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2),new Position(2,4), 3,'E');
      spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2), new Position(0,9), 3,'V');
      spaceinvaders.tirerUnMissile(new Dimension(3,2), 3);
      spaceinvaders.deplacerMissile();
      assertEquals(true, Collision.collisionParlaGauche(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
    }
    
    @Test
    public void test_verifGaucheMissile_Avant_DroiteEnvahisseur() {
      spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2),new Position(2,4), 3,'E');
      spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2), new Position(4,9), 3,'V');
      spaceinvaders.tirerUnMissile(new Dimension(3,2), 3);
      spaceinvaders.deplacerMissile();
      assertEquals(true, Collision.collisionParLaDroite(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
    }
    
    @Test
    public void test_MissileContenuDansEnvahisseur_verifParLaGauche() {
      spaceinvaders.positionnerUnNouveauSprite(new Dimension(13,2),new Position(2,4), 3,'E');
      spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2), new Position(7,9), 3,'V');
      spaceinvaders.tirerUnMissile(new Dimension(3,2), 3);
      spaceinvaders.deplacerMissile();
      assertEquals(true, Collision.collisionParlaGauche(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
    }
    
    @Test
    public void test_MissileContenuDansEnvahisseur_verifParLaDroite() {
      spaceinvaders.positionnerUnNouveauSprite(new Dimension(13,2),new Position(2,4), 3,'E');
      spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2), new Position(7,9), 3,'V');
      spaceinvaders.tirerUnMissile(new Dimension(3,2), 3);
      spaceinvaders.deplacerMissile();
      assertEquals(true, Collision.collisionParLaDroite(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
    }
    
    @Test
    public void test_MissileDansEnvahisseur_DetecteUneCollision() {
       spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2),new Position(0,4), 3,'E');
       spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2), new Position(0,9), 3,'V');
       spaceinvaders.tirerUnMissile(new Dimension(3,2), 2);
       spaceinvaders.deplacerMissile();
       assertEquals(true,Collision.detecterCollisionParUnCoteEtParLeHautOuLeBas(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
    }
}
