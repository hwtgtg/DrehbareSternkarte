
public class STKBilder implements ITuWas {

	public static Bilddatei karte;
	public static Bilddatei maske;
	public static Bilddatei lot;

	public static BildDrehbar bVG;
	public static BildDrehbar bHG;
	public static BildDrehbar bLot;

	public static Combobox cbOrt;

	public static STKBilder single;

	private STKBilder() {

	}

	public static void bildaufbau(Behaelter anzeige, int aussen, int innen) {
		STKBilder.karte = new Bilddatei("bilder/Gauting_K.gif");
		STKBilder.maske = new Bilddatei("bilder/Gauting_M.gif");
		STKBilder.lot = new Bilddatei("bilder/Gauting_L.gif");

		STKBilder.bHG = new BildDrehbar(anzeige, 0, 0, aussen, aussen,
				STKBilder.karte);
		STKBilder.bHG.einpassen();
		STKBilder.bVG = new BildDrehbar(anzeige, 30, 30, innen, innen,
				STKBilder.maske);
		STKBilder.bVG.einpassen();

		STKBilder.bLot = new BildDrehbar(anzeige, 30, 30, innen, innen,
				STKBilder.lot);
		STKBilder.bLot.einpassen();

	}

	public static void comboboxInitialisieren(Behaelter anzeige) {
		if (single == null) {
			single = new STKBilder();
		}
		cbOrt = new Combobox(anzeige, 60, 70, 140, 30);
		cbOrt.setzeNichtEditierbar();
		cbOrt.setzeSchriftgroesse(15);

		cbOrt.setzeLink(single);

		cbOrt.textHinzufuegen("71N-Nordkap");
		cbOrt.textHinzufuegen("59N-Oslo");
		cbOrt.textHinzufuegen("52N-Berlin");
		cbOrt.textHinzufuegen("48N-Gauting");
		cbOrt.textHinzufuegen("41N-Rom");
		cbOrt.textHinzufuegen("36N-Tunis");
		cbOrt.textHinzufuegen("12N-MtTahat");
		cbOrt.textHinzufuegen("23'N-Libreville");
		cbOrt.textHinzufuegen("34S-Kapstadt");
		cbOrt.textHinzufuegen("Spezial");
		cbOrt.setzeAuswahlIndex(3);
	}

	@Override
	public void tuWas(int ID) {
		int auswahl = cbOrt.leseAuswahlIndex();

		switch (auswahl) {
		case 0:
			STKBilder.karte = new Bilddatei("bilder/Nordkap_K.gif");
			STKBilder.maske = new Bilddatei("bilder/Nordkap_M.gif");
			STKBilder.lot = new Bilddatei("bilder/Nordkap_L.gif");
			break;
		case 1:
			STKBilder.karte = new Bilddatei("bilder/Oslo_K.gif");
			STKBilder.maske = new Bilddatei("bilder/Oslo_M.gif");
			STKBilder.lot = new Bilddatei("bilder/Oslo_L.gif");
			break;
		case 2:
			STKBilder.karte = new Bilddatei("bilder/Berlin_K.gif");
			STKBilder.maske = new Bilddatei("bilder/Berlin_M.gif");
			STKBilder.lot = new Bilddatei("bilder/Berlin_L.gif");
			break;
		case 3:
			STKBilder.karte = new Bilddatei("bilder/Gauting_K.gif");
			STKBilder.maske = new Bilddatei("bilder/Gauting_M.gif");
			STKBilder.lot = new Bilddatei("bilder/Gauting_L.gif");
			break;
		case 4:
			STKBilder.karte = new Bilddatei("bilder/Rom_K.gif");
			STKBilder.maske = new Bilddatei("bilder/Rom_M.gif");
			STKBilder.lot = new Bilddatei("bilder/Rom_L.gif");
			break;
		case 5:
			STKBilder.karte = new Bilddatei("bilder/Tunis_K.gif");
			STKBilder.maske = new Bilddatei("bilder/Tunis_M.gif");
			STKBilder.lot = new Bilddatei("bilder/Tunis_L.gif");
			break;
		case 6:
			STKBilder.karte = new Bilddatei("bilder/MtTahat_K.gif");
			STKBilder.maske = new Bilddatei("bilder/MtTahat_M.gif");
			STKBilder.lot = new Bilddatei("bilder/MtTahat_L.gif");
			break;
		case 7:
			STKBilder.karte = new Bilddatei("bilder/Libreville_K.gif");
			STKBilder.maske = new Bilddatei("bilder/Libreville_M.gif");
			STKBilder.lot = new Bilddatei("bilder/Libreville_L.gif");
			break;
		case 8:
			STKBilder.karte = new Bilddatei("bilder/Kapstadt_K.gif");
			STKBilder.maske = new Bilddatei("bilder/Kapstadt_M.gif");
			STKBilder.lot = new Bilddatei("bilder/Kapstadt_L.gif");
			break;
		case 9:
			STKBilder.karte = new Bilddatei("bilder/spezial_K.gif");
			STKBilder.maske = new Bilddatei("bilder/spezial_M.gif");
			STKBilder.lot = new Bilddatei("bilder/spezial_L.gif");
			break;
		default:
			STKBilder.karte = new Bilddatei("bilder/Gauting_K.gif");
			STKBilder.maske = new Bilddatei("bilder/Gauting_M.gif");
			STKBilder.lot = new Bilddatei("bilder/Gauting_L.gif");
			break;
		}
		
		if(STKBilder.bHG!=null){

		STKBilder.bHG.wechsleBild(karte);
		// STKBilder.bHG.einpassen();
		STKBilder.bVG.wechsleBild(maske);
		// STKBilder.bVG.einpassen();
		STKBilder.bLot.wechsleBild(lot);
		// STKBilder.bLot.einpassen();
		}

	}
}
