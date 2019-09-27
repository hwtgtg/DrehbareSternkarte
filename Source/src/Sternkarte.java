import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * 
 */

/**
 * @author Witt
 * 
 */
public class Sternkarte implements ITuWas {

	Behaelter anzeige = null ; // Zum Anzeigenaufbau am Anfang Dimension 0/0
	
	Schieberegler sMaske;
	Schieberegler sAlles;
	Schieberegler sDatum;
	Schieberegler sDeklination;
	Schieberegler sKarteUndDatum;

	AusgabeMitRichtung aMaske;
	AusgabeMitRichtung aAlles;
	AusgabeMitRichtung aDatum;
	AusgabeMitRichtung aKarteUndDatum;

	ChkTaste cDeklination;

	Linie lDatum;
	int len;

	int breiteHintergrund = 744;
	int breiteVordergrund = 634;
	int radiusH = breiteHintergrund / 2;
	int radiusV = breiteVordergrund / 2;

	int aussen;
	int innen;

	int zusatzBreite = 110 ;
	int zusatzHoehe = 60 ;
	
	
	double faktor = 0.9;

	int mitteX;
	int mitteY;

	double dMaske = 0;
	double dDatum = 0;
	double dKarte = 0;
	double dDeklination = 0;

	/**
	 * 
	 */
	public Sternkarte() {


		Zeichnung.setzeScrollbar(false);
		Bilddatei bild = new Bilddatei("icon.png");
		Zeichnung.setzeIcon(bild);
		Zeichnung.setzeTitel("Drehbare Sternkarte");

		anzeige = new Behaelter(0,0);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		double f1 = (dim.getHeight() - 170.0) / (breiteHintergrund);
		double f2 = (dim.getWidth() - 82.0) / (breiteHintergrund);

		if (f1 < f2) {
			faktor = f1;
		} else {
			faktor = f2;
		}

		aussen = (int) (breiteHintergrund * faktor);
		innen = (int) (breiteVordergrund * faktor);

		Zeichnung.setzeFenstergroesse(aussen + zusatzBreite , aussen + zusatzHoehe);


		radiusH = aussen / 2;
		radiusV = innen / 2;

		mitteX = aussen / 2 + 30;
		mitteY = aussen / 2 + 30;

		aAlles = new AusgabeMitRichtung(anzeige,"Alles", 35, 70, 35, 170);
		aAlles.setzeSchriftgroesse(20);
		aAlles.setzeSchreibrichtung('N');
		aAlles.setzeAusrichtung(2);

		cDeklination = new ChkTaste(anzeige,"Deklination", 60, 35,120, 40);
		cDeklination.setzeSchriftgroesse(20);
		
		STKBilder.comboboxInitialisieren(anzeige);

		aDatum = new AusgabeMitRichtung(anzeige,"Datum", 80, aussen, 100, 35);
		aDatum.setzeSchriftgroesse(20);
		aDatum.setzeSchreibrichtung('O');

		aMaske = new AusgabeMitRichtung(anzeige,"Maske", aussen - 5, 100, 35, 100);
		aMaske.setzeSchriftgroesse(20);
		aMaske.setzeSchreibrichtung('S');
		aMaske.setzeAusrichtung(0);

		aKarteUndDatum = new AusgabeMitRichtung(anzeige,"Karte und  Datum", aussen + 75 , 100, 35, 200);
		aKarteUndDatum.setzeSchriftgroesse(20);
		aKarteUndDatum.setzeSchreibrichtung('S');
		aKarteUndDatum.setzeAusrichtung(0);

		
		// Die Bilder sollen vor allem anderen liegen
		STKBilder.bildaufbau(anzeige , aussen , innen );
		
		lDatum = new Linie(anzeige,mitteX, mitteY + radiusV, mitteX, mitteY + radiusH);

		lDatum.setzeLinienDicke(3);
		lDatum.setzeFarbe("gruen");

		sMaske = new Schieberegler(anzeige,'H');
		sAlles = new Schieberegler(anzeige,'H');
		sDatum = new Schieberegler(anzeige,'H');
		sDeklination = new Schieberegler(anzeige,'H');
		sKarteUndDatum = new Schieberegler(anzeige,'H');

		sAlles.setzeDimensionen(0, 30, 30, aussen);
		sAlles.setzeOrientierungSenkrecht();

		sMaske.setzeDimensionen(aussen + 30, 30, 30, aussen);
		sMaske.setzeOrientierungSenkrecht();

		sDatum.setzeDimensionen(30, aussen + 30, aussen, 30);
		sDatum.setzeOrientierungHorizontal();

		sDeklination.setzeDimensionen(30, 0, aussen, 30);
		sDeklination.setzeOrientierungHorizontal();

		sKarteUndDatum.setzeDimensionen(aussen + 60, 30, 30, aussen);
		sKarteUndDatum.setzeOrientierungSenkrecht();

		sAlles.setzeBereich(-360, 360, 0);
		sMaske.setzeBereich(360, -360, 0);
		sDeklination.setzeBereich(-270, 450, 90);
		sDatum.setzeBereich(-360, 360, 0);
		sKarteUndDatum.setzeBereich(360, -360, 0);

		sAlles.setzeTeilung(720);
		sMaske.setzeTeilung(720);
		sDatum.setzeTeilung(720);
		sKarteUndDatum.setzeTeilung(720);

		sAlles.setzeLink(this);
		sAlles.setzeID(0);
		sMaske.setzeLink(this);
		sMaske.setzeID(10);
		sDatum.setzeLink(this);
		sDatum.setzeID(30);
		sKarteUndDatum.setzeLink(this);
		sKarteUndDatum.setzeID(40);
		sDeklination.setzeLink(this);
		sDeklination.setzeID(50);

		cDeklination.setzeLink(this);
		cDeklination.setzeID(60);
		
		tuWas(50); // Deklinationsmarke setzen
		tuWas(61); // Deklinationszeiger verbergen

		anzeigeAendern();
		
		Zeichnung.gibZeichenflaeche().setzeLinkBasis(this);
		Zeichnung.gibZeichenflaeche().setzeIDBasis(100);
		
	}

	public void anzeigeAendern() {

		anzeige.setzeGroesse(0,0);
		
		double f1 = (Zeichnung.gibZeichenflaeche().getBounds().getHeight() - zusatzHoehe)
				/ (breiteHintergrund);
		double f2 = (Zeichnung.gibZeichenflaeche().getBounds().getWidth() - zusatzBreite)
				/ (breiteHintergrund);

		
		if (f1 < f2) {
			faktor = f1;
		} else {
			faktor = f2;
		}

		aussen = (int) (breiteHintergrund * faktor);
		innen = (int) (breiteVordergrund * faktor);

		radiusH = aussen / 2;
		radiusV = innen / 2;

		mitteX = aussen / 2 + 30;
		mitteY = aussen / 2 + 30;

		STKBilder.bHG.setzeDimensionen(30, 30,aussen,aussen);
		STKBilder.bVG.setzeDimensionen((aussen - innen) / 2 + 30, (aussen - innen) / 2 + 30,innen,innen);
		STKBilder.bLot.setzeDimensionen((aussen - innen) / 2 + 30, (aussen - innen) / 2 + 30,innen,innen);

		aDatum.setzePosition(80, aussen);
		aMaske.setzePosition(aussen - 5, 70);
		aKarteUndDatum.setzePosition(aussen + 75 , 70);

		sAlles.setzeDimensionen(0, 30, 30, aussen);
//		sAlles.setzeOrientierungSenkrecht();

		sMaske.setzeDimensionen(aussen + 30, 30, 30, aussen);
//		sMaske.setzeOrientierungSenkrecht();

		sDatum.setzeDimensionen(30, aussen + 30, aussen, 30);
//		sDatum.setzeOrientierungHorizontal();

		sDeklination.setzeDimensionen(30, 0, aussen, 30);
//		sDeklination.setzeOrientierungHorizontal();

		sKarteUndDatum.setzeDimensionen(aussen + 60, 30, 30, aussen);
//		sKarteUndDatum.setzeOrientierungSenkrecht();
		
		setzeDatum();
		anzeige.setzeGroesse((int)Zeichnung.gibZeichenflaeche().getBounds().getWidth(), (int)Zeichnung.gibZeichenflaeche().getBounds().getHeight());

	}

	void setzeDatum() {
		double dx = Math.sin(dDatum);
		double dy = Math.cos(dDatum);

		lDatum.setzeEndpunkte((int) (mitteX + dx * radiusV), (int) (mitteY + dy
				* radiusV), (int) (mitteX + dx * radiusH), (int) (mitteY + dy
				* radiusH));
	}

	public void setzeWinkel() {
		dMaske = Math.toRadians(sAlles.leseDoubleWert()
				+ sMaske.leseDoubleWert());
		dDatum = Math.toRadians(-sAlles.leseDoubleWert()
				- sKarteUndDatum.leseDoubleWert() + sDatum.leseDoubleWert());
		dKarte = Math.toRadians(sAlles.leseDoubleWert()
				+ sKarteUndDatum.leseDoubleWert());
	}

	@Override
	public void tuWas(int ID) {

		if (ID == 0) { // alles
			setzeWinkel();
			STKBilder.bHG.setWinkel(dKarte);
			setzeDatum();
			STKBilder.bVG.setWinkel(dMaske);
		} else if (ID == 10) { // maske
			setzeWinkel();
			STKBilder.bVG.setWinkel(dMaske);
		} else if (ID == 30) { // Datum
			setzeWinkel();
			setzeDatum();
		} else if (ID == 40) { // Maske fix
			setzeWinkel();
			STKBilder.bHG.setWinkel(dKarte);
			setzeDatum();
		} else if (ID == 50) { // Deklination
			dDeklination = Math.toRadians(sDeklination.leseDoubleWert());
			STKBilder.bLot.setWinkel(dDeklination);
		} else if (ID == 60) { // Deklination
			STKBilder.bLot.sichtbarMachen();
		} else if (ID == 61) {
			STKBilder.bLot.unsichtbarMachen();
		} else if (ID == 100+Zeichenflaeche.GROESSEANDERN) {
			anzeigeAendern();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Sternkarte();

	}

}
