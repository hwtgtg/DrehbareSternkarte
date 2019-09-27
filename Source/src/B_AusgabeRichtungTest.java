
public class B_AusgabeRichtungTest {

	public B_AusgabeRichtungTest() {
		Zeichnung.setzeRasterEin();
		Rechteck re = new Rechteck(100, 100, 400, 200);
		re.setzeFarbe("gruen");
		
		AusgabeMitRichtung ar = new AusgabeMitRichtung("Halloyy", 100, 100, 400, 200);
		ar.setzeSchreibrichtung('N');
		ar.setzeSchriftgroesse(60);
		ar.setzeSchriftStilFett();
		ar.setzeSchriftStilKursiv();
		ar.setzeFarbe("rot");
		ar.setzeAusrichtung(2);
		ar.setzeSchriftStilFett();
		ar.setzeSchriftStilKursiv();
		
		
		AusgabeMitRichtung aAlles = new AusgabeMitRichtung("alles.", 35 , 100 , 35 , 170 ) ;
		aAlles.setzeSchriftgroesse(20);
		aAlles.setzeSchreibrichtung('N');
		aAlles.setzeAusrichtung(2);

		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new B_AusgabeRichtungTest();

	}

}
