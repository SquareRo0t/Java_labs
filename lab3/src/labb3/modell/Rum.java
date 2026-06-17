package labb3.modell;

import java.awt.Color;
import labb3.GlobalaKonstanter;
import labb3.verktyg.Punkt;

public class Rum {

	// TODO: Lägg till tillståndsvariabler.
	private Color golvfärg;
	private int bredd;
	private int höjd;
	private Punkt öv;
	private Gång[] gångar;

	// Konstruktor för att skapa ett nytt rum med specifierad villkor
	public Rum(Color golvfärg, int bredd, int höjd, int övX, int övY) {
		/*
		 * TODO: Kopiera parametrarna in i tillståndsvariablerna. (övX,övY) är
		 * koordinaten för rummets övre vänstra hörn och lagras lämpligen som en instans
		 * av klassen Punkt i paketet verktyg.
		 */
		this.golvfärg = golvfärg;
		this.bredd = bredd;
		this.höjd = höjd;
		this.öv = new Punkt(övX, övY);
		this.gångar = new Gång[GlobalaKonstanter.ANTAL_VÄDERSTRECK];
	}

	/*
	 * TODO: Skriv "getters", metoder som returnerar tillståndsvariablernas värden
	 */
	public Color getGolvfärg() {
		return golvfärg;
	}

	public int getBredd() {
		return bredd;
	}

	public int getHöjd() {
		return höjd;
	}

	public Punkt öv() {
		return öv;
	}

	/*
	 * TODO: Skriv instansmetoden finnsUtgångÅt som ska kontrollera om det från ett rum finns en
	 * utgång åt visst väderstreck.
	 */
	
	// Metod för att kontrollera om det finns en utgång från rummet åt ett visst väderstreck
	boolean finnsUtgångÅt(Väderstreck väderstreck) {
		return this.gångar[väderstreck.index()] != null;
	}

	/*
	 * TODO: Skriv instansmetoden Gång gångenÅt(Väderstreck väderstreck) som
	 * returnerar den gång som leder från ett rum i riktning väderstreck. Om sådan
	 * gång saknas ska ett undantag kastas med lämpligt felmeddelande.
	 */
	
	// Metod för att hämta gången som leder från rummet i en viss riktning
	public Gång gångenÅt(Väderstreck väderstreck) throws Exception {
		if (!finnsUtgångÅt(väderstreck)) {
			throw new Exception("Ingen gång åt det specifierade väderstrecket");
		}
		return this.gångar[väderstreck.index()];
	}

	// TODO: Skriv klar metoden nedan som kopplar ihop två rum med en gång.
	
	// Metod för att koplla ihop två rum med en gång i specificerade riktningar
	public static void kopplaIhop(Rum från, Väderstreck riktningUtUrFrån, Rum till, Väderstreck riktningInITill)
			
			throws Exception {
		// Kontrollerar om det är samma rum
		if (från.equals(till)) {
			throw new Exception("Ej till och från samma rum");
		}

		// kontrollerar om det redan finns en gång vid en av utgångarna från något av rummen
		if (från.finnsUtgångÅt(riktningUtUrFrån) || till.finnsUtgångÅt(riktningInITill)) {
			throw new Exception("Gång finns redan vid en av utgångarna");
		}

		// Skapar en gång från 'från' rummet till 'till' rummet och vice versa
		från.gångar[riktningUtUrFrån.index()] = new Gång(från, riktningUtUrFrån, till, riktningInITill);

		till.gångar[riktningInITill.index()] = new Gång(till, riktningInITill, från, riktningUtUrFrån);
	}
}
