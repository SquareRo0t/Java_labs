package labb3.modell;

import java.util.ArrayList;
import java.util.Observable;
import labb3.verktyg.Punkt;

// Nivå-klassen representerar en nivå i spelet med flera rum och en användare som 
// kan röra sig mellan dem. 
public class Nivå extends Observable {

	/*
	 * TODO: Lägg till tillståndsvariabler för att hålla reda på nivåns rum och i
	 * vilket rum som användaren "är" i.
	 */
	private ArrayList<Rum> rum; // Lista över alla rum i nivån
	private Rum användarRum; // Det rum där användaren för närvarande befinner sig i

	// Konstruktor för Nivå-klassen
	public Nivå(Rum startrum, ArrayList<Rum> rum) {

		// TODO: Kopiera in startrum och rum in i tillståndsvariablerna.
		this.användarRum = startrum;
		this.rum = rum;

		// TODO: Kontrollera att startrum finns med i rum. Om inte, kasta undantag
		// med lämpligt felmeddelande.
		if (!rum.contains(startrum)) {
			throw new RuntimeException("Startrum finns ej med i listan");
		}

		/*
		 * TODO: Kontrollera att inga rum överlappar varandra. Om det ändå är fallet,
		 * kasta undantag med lämpligt felmeddelande.
		 */
		
		// Yttre loop som itererar över varje rum föurtom sista rummet
		for (int i = 0; i < this.rum.size() - 1; i++) {
			
			// Inre loop som itererar över varje rum inklusive sig själv
			for (int j = 0; j < this.rum.size(); j++) {

				// Hoppar över jämförelse om i och j är samma pga av de pekar på samma rum
				// Förhindrar att jämföra med sig själv
				if (i == j) {
					continue;
				}

				// Kontrollera överlappning mellan rum i och j
				if (checkOverLap(this.rum.get(i), this.rum.get(j))) {
					throw new RuntimeException("Rummen överlappar ");
				}
			}
		}
	}

	// Metod för att kontrollera om två rum överlappar varandra
	private boolean checkOverLap(Rum rum1, Rum rum2) {
		Punkt öv1 = rum1.öv(); // Övre vänstra hörnet för rum 1
		Punkt öv2 = rum2.öv(); // Övre vänstra hörnet för rum 2

		// Kontrollerar om det finns någon horisontell överlappning
		if (öv1.x() + rum1.getBredd() < öv2.x() || öv2.x() + rum2.getBredd() < öv1.x()) {
			return false;
		}
		// Kontrollerar om det finns någon vertikal överlappning
		if (öv1.y() + rum1.getHöjd() < öv2.y() || öv2.y() + rum2.getHöjd() < öv1.y()) {
			return false;
		}
		return true;
	}

	/*
	 * TODO: Skriv en instansmetod som returnerar alla rummen. Denna behöver
	 * Målarduk för att veta vilka rum som finns på nivån och som ska ritas ut.
	 */
	public ArrayList<Rum> rum() {
		return this.rum;
	}

	/*
	 * TODO Skriv en instansmetod som returnerar en referens till det rum som //
	 * användaren "är i".
	 */
	public Rum nuvarandeRum() {
		return användarRum;
	}

	/*
	 * TODO: Skriv klar instansmetoden hoppaÅt nedan så att den ändrar det rum som
	 * användaren "är i" om det är möjligt genom att följa en gång från rummet och i
	 * riktning väderstreck.
	 * 
	 * Om väderstreck inte är en riktning i vilken det finns en gång, så ändras inte
	 * rummet användaren "är i" (och inte heller kastas undantag). (Denna metod
	 * använder kontrolldelen av programmet för att begära ett hopp till angränsande
	 * rum efter att användaren tryckt på en tangent.)
	 */

	// Metod för att ändra användarens nuvarande rum baserat på angiven riktning
	public void hoppaÅt(Väderstreck väderstreck) {
		try {
			// Försöker att gå till ett angränsande rum i den angivna riktningen
			användarRum = användarRum.gångenÅt(väderstreck).till();
			setChanged();
			notifyObservers();
		} catch (Exception e) {

		}
	}
}
// ---------------------------------------------------------------
// ---------------------------------------------------------------
