package labb3;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import labb3.kontroll.Tangentbordslyssnare;
import labb3.modell.Nivå;
import labb3.modell.Rum;
import labb3.vy.Målarduk;

// TODO: Klassen ska ärva klassen JFrame i standardbibliotekets paket 
// javax.swing och implementera gränssnittet Observer i paketet java.util. 
public class GUI extends JFrame implements Observer {

	private Målarduk målarduk;

	// Konstruktor för GUI klassen
	public GUI(Nivå enNivå) {

		// Anropar metoden setDefaultCloseOperation med konstanten
		// JFrame.EXIT_ON_CLOSE.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Gör så att enNivå observerar this (instansen av GUI som denna
		// kod håller på att skapa.
		enNivå.addObserver(this);

		// Tilldelar tillståndsvariabeln målarduk en instans av klassen
		// Målarduk.
		målarduk = new Målarduk(enNivå);

		// TODO: Använd setPreferredSize på målarduk och sätt dess dimensioner
		// så alla rum faktiskt syns. Ett tips är att loopa igenom nivåns alla
		// rum och räkna ut vilken bredd och höjd målarduken måste ha(!)
		// Annars går det också att dra till med en storlek och sen bara
		// använda rum som säkert kan visas på målarduken.

		// Beräknar storleken på Målarduken baserat på nivåns rum
		int max_X = 0;
		int max_Y = 0;

		// List över alla rum på nivån
		ArrayList<Rum> allaRum = enNivå.rum();

		// Loopar igenom alla rum för att beräkna den maximala bredden och höjden
		for (int i = 0; i < allaRum.size(); i++) {

			// Hämtar ett rum från listan
			Rum ettRum = allaRum.get(i);

			// Beräknar den nya maximala bredden om detta rum sträcker sig längre till höger
			// än de tidigare rummen
			if (ettRum.öv().x() + ettRum.getBredd() > max_X) {
				max_X = ettRum.öv().x() + ettRum.getBredd();
			}
			// Beräknar den nya maximala höjden om detta rum sträcker sig längre nedåt
			// än de tidigare rummen
			if (ettRum.öv().y() + ettRum.getHöjd() > max_Y) {
				max_Y = ettRum.öv().y() + ettRum.getHöjd();
			}
		}

		// Ställer in storleken på Målarduken och lägger till lite extra utrymme för att
		// undvika beskärning
		målarduk.setPreferredSize(new Dimension(max_X + GlobalaKonstanter.DUBBEL_VÄGGTJOCKLEK,
				max_Y + GlobalaKonstanter.DUBBEL_VÄGGTJOCKLEK));

		// TODO: Lägg till en KeyListener på målarduken, dvs en instans av
		// typen Tangentbordslyssnare. Notera att lyssnaren vill ha enNivå
		// som argument till konstruktorn för att kunna påverka just den
		// nivån.
		
		// Lägger till en tangetbordslyssnare för att hantera tangetsbordshändelser
		målarduk.addKeyListener(new Tangentbordslyssnare(enNivå));

		// TODO: Anropa setContentPane med målarduk, så att målarduk är
		// den yta som vår JFrame kommer att ha. Sen återstår bara att
		// göra setVisible(true) och pack().
		
		// Ställer in Målarduken som innehåller i JFrame
		setContentPane(målarduk);
		
		// Gör JFrame synlig och anpassar storleken till innehållet
		setVisible(true);
		pack();
	}

	// TODO: Metoden update ska vara den i gränssnittet Observer. Lägg därför
	// till @Override på raden innan metodhuvudet. (Klassen måste sen importera
	// java.util.Observer.)
	@Override
	public void update(Observable o, Object arg) {
		// Detta anrop triggar ett anrop till paintComponent i Målarduk.
		// Avkommentera raden (när Målarduk "är-en" JPanel).
		this.målarduk.repaint();
	}
}
