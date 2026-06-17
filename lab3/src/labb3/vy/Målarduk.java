package labb3.vy;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import labb3.GlobalaKonstanter;
import labb3.modell.Gång;
import labb3.modell.Nivå;
import labb3.modell.Rum;
import labb3.modell.Väderstreck;
import labb3.verktyg.Grafik;
import labb3.verktyg.Punkt;

public class Målarduk extends JPanel {

	private final Nivå enNivå;

	public Målarduk(Nivå enNivå) {


		this.enNivå = enNivå;
		this.setBackground(GlobalaKonstanter.MARKFÄRG);
		this.setFocusable(true);
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		
		// Loppar genom alla rum i nivån och ritar dem
		ArrayList<Rum> allaRum = enNivå.rum();
		for (int i = 0; i < allaRum.size(); i++) {
			ritaRum(g, allaRum.get(i)); // Ritar rummet
			ritaGångarFrånRum(g, allaRum.get(i)); // Ritar gångar från rummet
			ritaMarkörFörVarAnvändarenÄr(g); // Ritar användarens markör
		}

	}

	// Metod för att rita ut ett rum på grafiken med väggar och golv
	private void ritaRum(Graphics g, Rum ettRum) {
		
		// Sätter färg för vägggarna
		g.setColor(GlobalaKonstanter.VÄGGFÄRG);

		/* Ritar en rektangel för väggarna med den övre vänstra hörnet 
		 * som startpunkt och bredd och höjd från rummet */
		g.fillRect(ettRum.öv().x(), ettRum.öv().y(), ettRum.getBredd(), ettRum.getHöjd());

		// Sätter färg för golvet
		g.setColor(ettRum.getGolvfärg());

		// Rita en rektangel för golvet med en förskutning för att ta hänsyn till väggarnas tjocklek
		g.fillRect(ettRum.öv().x() + GlobalaKonstanter.VÄGGTJOCKLEK, ettRum.öv().y() + GlobalaKonstanter.VÄGGTJOCKLEK,
				ettRum.getBredd() - 2 * GlobalaKonstanter.VÄGGTJOCKLEK,
				ettRum.getHöjd() - 2 * GlobalaKonstanter.VÄGGTJOCKLEK);
	}

	// Metod för att rita gångar från ett rum
	private void ritaGångarFrånRum(Graphics g, Rum ettRum) {
		
		// Loopar igenom alla väderstreck
		for (Väderstreck riktning : Väderstreck.values()) {
			try {
				// Försöker rita gånen åt ett visst väderstreck från rummet
				ritaGång(g, ettRum.gångenÅt(riktning));
			} catch (Exception e) {

			}
		}
	}

	// Metod för att beräkna baspunkten för en gång
	private Punkt baspunkt(Rum ettRum, Väderstreck enRiktning) {

		int x;
		int y;

		switch (enRiktning) {

		case NORR:
			x = ettRum.öv().x() + ettRum.getBredd() / 2;
			y = ettRum.öv().y() + GlobalaKonstanter.VÄGGTJOCKLEK;
			return new Punkt(x, y);

		case ÖSTER:
			x = ettRum.öv().x() + ettRum.getBredd() - GlobalaKonstanter.VÄGGTJOCKLEK;
			y = ettRum.öv().y() + ettRum.getHöjd() / 2;
			return new Punkt(x, y);

		case SÖDER:
			x = ettRum.öv().x() + ettRum.getBredd() / 2;
			y = ettRum.öv().y() + ettRum.getHöjd() - GlobalaKonstanter.VÄGGTJOCKLEK;
			return new Punkt(x, y);

		case VÄSTER:
			x = ettRum.öv().x() + GlobalaKonstanter.VÄGGTJOCKLEK;
			y = ettRum.öv().y() + ettRum.getHöjd() / 2;
			return new Punkt(x, y);

		default:
			return null;
		}
	}

	// Metod för att beräkna pivotpunkten för en gång
	private Punkt pivotpunkt(Rum ettRum, Väderstreck enRiktning) {

		// Beräkna x och y koodinater beroende på gångens riktning
		int x;
		int y;

		switch (enRiktning) {

		case NORR:
			x = ettRum.öv().x() + ettRum.getBredd() / 2;
			y = ettRum.öv().y() - GlobalaKonstanter.HALV_VÄGGTJOCKLEK;
			return new Punkt(x, y);

		case ÖSTER:
			x = ettRum.öv().x() + ettRum.getBredd() + GlobalaKonstanter.HALV_VÄGGTJOCKLEK;
			y = ettRum.öv().y() + ettRum.getHöjd() / 2;
			return new Punkt(x, y);

		case SÖDER:
			x = ettRum.öv().x() + ettRum.getBredd() / 2;
			y = ettRum.öv().y() + ettRum.getHöjd() + GlobalaKonstanter.HALV_VÄGGTJOCKLEK;
			return new Punkt(x, y);

		case VÄSTER:
			x = ettRum.öv().x() - GlobalaKonstanter.HALV_VÄGGTJOCKLEK;
			y = ettRum.öv().y() + ettRum.getHöjd() / 2;
			return new Punkt(x, y);

		default:
			return null;
		}
	}

	// Metod för att rita en gång mellan två rum
	private void ritaGång(Graphics g, Gång enGång) {

		// Beräkna baspunkter och pivotpuntker för gången
		Punkt b1 = baspunkt(enGång.från(), enGång.riktningUtUrFrån());
		Punkt p1 = pivotpunkt(enGång.från(), enGång.riktningUtUrFrån());
		Punkt b3 = baspunkt(enGång.till(), enGång.riktningInITill());
		Punkt p3 = pivotpunkt(enGång.till(), enGång.riktningInITill());
		
		// Rita linjer för gången och dess pivotpunkter
		Grafik.drawThickLine(g, b3, p3, GlobalaKonstanter.VÄGGTJOCKLEK, GlobalaKonstanter.GÅNGFÄRG);
		Grafik.drawThickLine(g, b1, p1, GlobalaKonstanter.VÄGGTJOCKLEK, GlobalaKonstanter.GÅNGFÄRG);
		Grafik.drawThickLine(g, p1, p3, GlobalaKonstanter.VÄGGTJOCKLEK, GlobalaKonstanter.GÅNGFÄRG);
		
		// Rita cirkelmarkörer vid pivotpunkterna för gången
		Grafik.fillCircle(g, p1, GlobalaKonstanter.HALV_VÄGGTJOCKLEK, GlobalaKonstanter.GÅNGFÄRG);
		Grafik.fillCircle(g, p3, GlobalaKonstanter.HALV_VÄGGTJOCKLEK, GlobalaKonstanter.GÅNGFÄRG);
	}

	// Metod för att rita en markör för användares position i rummet
	private void ritaMarkörFörVarAnvändarenÄr(Graphics g) {
		
		// Beräkna användarens position i mitten av nuvarande rum
		Punkt userPosition = new Punkt(enNivå.nuvarandeRum().öv().x() + enNivå.nuvarandeRum().getBredd() / 2,
				enNivå.nuvarandeRum().öv().y() + enNivå.nuvarandeRum().getHöjd() / 2);
		
		Grafik.fillCircle(g, userPosition, GlobalaKonstanter.ANVÄNDARRADIE, GlobalaKonstanter.ANVÄNDARFÄRG);
	}
}
