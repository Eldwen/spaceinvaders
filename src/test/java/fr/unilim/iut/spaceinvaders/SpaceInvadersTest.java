package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Before;

import fr.unilim.iut.spaceinvaders.model.Collision;
import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class SpaceInvadersTest {

    private SpaceInvaders spaceinvaders;

    @Before
    public void initialisation() {
	    spaceinvaders = new SpaceInvaders(15, 10);
    }
    
   @Test
   public void test_AuDebut_JeuSpaceInvaderEstVide() {
	    assertEquals("" + 
	    "...............\n" + 
	    "...............\n" +
	    "...............\n" + 
	    "...............\n" + 
	    "...............\n" + 
	    "...............\n" + 
	    "...............\n" + 
	    "...............\n" + 
	    "...............\n" + 
	    "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
        }
        
	@Test
	public void test_UnNouveauVaisseauPositionneHorsEspaceJeu_DoitLeverUneException() {
		try {
			spaceinvaders.positionnerUnNouveauSprite(new Dimension(1,1),new Position(15,9), 1,'V');
			fail("Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}
		try {
			spaceinvaders.positionnerUnNouveauSprite(new Dimension(1,1),new Position(-1,9), 1,'V');
			fail("Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}
		try {
			spaceinvaders.positionnerUnNouveauSprite(new Dimension(1,1),new Position(14,10), 1,'V');
			fail("Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}
		try {
			spaceinvaders.positionnerUnNouveauSprite(new Dimension(1,1),new Position(14,-1), 1,'V');
			fail("Position trop à haut : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}
	}
    
	@Test
    public void test_VaisseauAvance_DeplacerVaisseauVersLaDroite() {
        spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2),new Position(7,9),3,'V');
        spaceinvaders.deplacerSpriteVersLaDroite(spaceinvaders.recupererVaisseau());
        assertEquals("" + 
        "...............\n" + 
        "...............\n" +
        "...............\n" + 
        "...............\n" + 
        "...............\n" + 
        "...............\n" + 
        "...............\n" + 
        "...............\n" + 
        "..........VVV..\n" + 
        "..........VVV..\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
   }
	
    @Test
	public void test_VaisseauImmobile_DeplacerVaisseauVersLaDroite() {
    	spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2),new Position(12,9), 3,'V');
		spaceinvaders.deplacerSpriteVersLaDroite(spaceinvaders.recupererVaisseau());
		assertEquals("" + 
		"...............\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"............VVV\n" + 
		"............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
    
    @Test
    public void test_VaisseauAvance_DeplacerVaisseauVersLaGauche() {
       spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2),new Position(7,9), 3,'V');
       spaceinvaders.deplacerSpriteVersLaGauche(spaceinvaders.recupererVaisseau());
       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "....VVV........\n" + 
       "....VVV........\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
   }
	
    @Test
    public void test_VaisseauImmobile_DeplacerVaisseauVersLaGauche() {
	   spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2),new Position(0,9), 3,'V');
       spaceinvaders.deplacerSpriteVersLaGauche(spaceinvaders.recupererVaisseau());
       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "VVV............\n" + 
       "VVV............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
     }
	
    @Test
    public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaDroite() {
       spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2),new Position(10,9),3,'V');
       spaceinvaders.deplacerSpriteVersLaDroite(spaceinvaders.recupererVaisseau());
       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "............VVV\n" + 
       "............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }
	
    @Test
    public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaGauche() {
       spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2),new Position(1,9), 3,'V');
       spaceinvaders.deplacerSpriteVersLaGauche(spaceinvaders.recupererVaisseau());
       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "VVV............\n" + 
       "VVV............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
     }
    
    @Test
	public void test_unNouveauVaisseauEstCorrectementPositionneDansEspaceJeu() {
		spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2),new Position(7,9), 1,'V');
		assertEquals("" + 
		"...............\n" + 
		"...............\n" +
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		".......VVV.....\n" + 
		".......VVV.....\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
    
	@Test
	public void test_UnNouveauVaisseauPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {
		try {
			spaceinvaders.positionnerUnNouveauSprite(new Dimension(9,2),new Position(7,9), 1,'V');
			fail("Dépassement du vaisseau à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}
		try {
			spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,4),new Position(7,1), 1,'V');
			fail("Dépassement du vaisseau vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}
	}
    
    @Test
    public void test_MissileBienTireDepuisVaisseau_VaisseauLongueurImpaireMissileLongueurImpaire() {
	  spaceinvaders.positionnerUnNouveauSprite(new Dimension(7,2),new Position(5,9), 2,'V');
	  spaceinvaders.tirerUnMissile(new Dimension(3,2),2);
      assertEquals("" + 
      "...............\n" + 
      "...............\n" +
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      ".......MMM.....\n" + 
      ".......MMM.....\n" + 
      ".....VVVVVVV...\n" + 
      ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
   }
    
    @Test(expected = MissileException.class)
	public void test_PasAssezDePlacePourTirerUnMissile_UneExceptionEstLevee() throws Exception { 
	   spaceinvaders.positionnerUnNouveauSprite(new Dimension(7,2),new Position(5,9), 1,'V');
	   spaceinvaders.tirerUnMissile(new Dimension(7,9),1);
	}
    
    @Test
    public void test_MissileAvanceAutomatiquement_ApresTirDepuisLeVaisseau() {
	   spaceinvaders.positionnerUnNouveauSprite(new Dimension(7,2),new Position(5,9), 2,'V');
	   spaceinvaders.tirerUnMissile(new Dimension(3,2),2);
	   spaceinvaders.deplacerMissile();
       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       "...............\n" + 
       ".......MMM.....\n" + 
       ".......MMM.....\n" + 
       "...............\n" + 
       "...............\n" + 
       ".....VVVVVVV...\n" + 
       ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
   }
    
    @Test
    public void test_MissileDisparait_QuandIlCommenceASortirDeEspaceJeu() {
 	   spaceinvaders.positionnerUnNouveauSprite(new Dimension(7,2),new Position(5,9), 1,'V');
 	   spaceinvaders.tirerUnMissile(new Dimension(3,2),1);
 	   for (int i = 1; i <=6 ; i++) {
 		   spaceinvaders.deplacerMissile();
 	   }
 	   spaceinvaders.deplacerMissile();
        assertEquals("" +
        "...............\n" + 
        "...............\n" +
        "...............\n" + 
        "...............\n" +
        "...............\n" +
        "...............\n" + 
        "...............\n" +
        "...............\n" + 
        ".....VVVVVVV...\n" + 
        ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }
    
	@Test
	public void test_unNouveauEnvahisseurEstCorrectementPositionneDansEspaceJeu() {
		spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2),new Position(0,4), 1,'E');
		assertEquals("" + 
		"...............\n" + 
		"...............\n" +
		"...............\n" + 
		"EEE............\n" + 
		"EEE............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_UnNouveauEnvahisseurPositionneHorsEspaceJeu_DoitLeverUneException() {;
		try {
			spaceinvaders.positionnerUnNouveauSprite(new Dimension(1,1),new Position(15,9), 1,'E');
			fail("Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}
		try {
			spaceinvaders.positionnerUnNouveauSprite(new Dimension(1,1),new Position(-1,9), 1,'E');
			fail("Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}
		try {
			spaceinvaders.positionnerUnNouveauSprite(new Dimension(1,1),new Position(14,10), 1,'E');
			fail("Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}
		try {
			spaceinvaders.positionnerUnNouveauSprite(new Dimension(1,1),new Position(14,-1), 1,'E');
			fail("Position trop à haut : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}	
	}
	
	@Test
    public void test_EnvahisseurImmobile_DeplacerVaisseauVersLaDroite() {
        spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2),new Position(0,4),3,'E');
        spaceinvaders.deplacerSpriteVersLaDroite(spaceinvaders.recupererEnvahisseur());
        assertEquals("" + 
        "...............\n" + 
        "...............\n" +
        "...............\n" + 
        "...EEE.........\n" + 
        "...EEE.........\n" + 
        "...............\n" + 
        "...............\n" + 
        "...............\n" + 
        "...............\n" + 
        "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
   }
	
    @Test
	public void test_EnvahisseurAvance_DeplacerVaisseauVersLaDroite() {
    	spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2),new Position(6,4), 3,'E');
		spaceinvaders.deplacerSpriteVersLaDroite(spaceinvaders.recupererEnvahisseur());
		assertEquals("" + 
		"...............\n" + 
		"...............\n" +
		"...............\n" + 
		".........EEE...\n" + 
		".........EEE...\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" + 
		"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
    
    @Test
    public void test_EnvahisseurImmobile_DeplacerVaisseauVersLaGauche() {
       spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2),new Position(12,4), 3,'E');
       spaceinvaders.deplacerSpriteVersLaGauche(spaceinvaders.recupererEnvahisseur());
       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       ".........EEE...\n" + 
       ".........EEE...\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
   }

    @Test
    public void test_EnvahisseurAvance_DeplacerVaisseauVersLaGauche() {
	   spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2),new Position(5,4), 3,'E');
       spaceinvaders.deplacerSpriteVersLaGauche(spaceinvaders.recupererEnvahisseur());
       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       "..EEE..........\n" + 
       "..EEE..........\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
     }
    
    @Test
    public void test_EnvahisseurAvancePartiellement_DeplacerVaisseauVersLaDroite() {
       spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2),new Position(11,4),3,'E');
       spaceinvaders.deplacerSpriteVersLaDroite(spaceinvaders.recupererEnvahisseur());
       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       "............EEE\n" + 
       "............EEE\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }
	
    @Test
    public void test_EnvahisseurAvancePartiellement_DeplacerVaisseauVersLaGauche() {
       spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2),new Position(2,4), 3,'E');
       spaceinvaders.deplacerSpriteVersLaGauche(spaceinvaders.recupererEnvahisseur());
       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       "EEE............\n" + 
       "EEE............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
     }
    
    @Test
    public void test_EnvahisseurChangeDeDirection_SiBordGauche() {
       spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2),new Position(12,4), 3,'E');
       spaceinvaders.deplacerEnvahisseur();
       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       ".........EEE...\n" + 
       ".........EEE...\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
     }
    
    @Test
    public void test_EnvahisseurChangeDeDirection_SiBordDroit() {
       spaceinvaders.positionnerUnNouveauSprite(new Dimension(3,2),new Position(0,4), 3,'E');
       spaceinvaders.deplacerEnvahisseur();
       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       "...EEE.........\n" + 
       "...EEE.........\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
     }
}