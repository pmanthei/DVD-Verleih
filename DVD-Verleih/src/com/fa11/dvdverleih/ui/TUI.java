package com.fa11.dvdverleih.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.fa11.dvdverleih.datenhaltung.XMLDatenhaltung;
import com.fa11.dvdverleih.datenhaltung.tables.DVD;
import com.fa11.dvdverleih.datenhaltung.tables.Kunde;
import com.fa11.dvdverleih.datenhaltung.tables.Verleih;
import com.fa11.dvdverleih.fachkonzept.Fachkonzept;
import com.fa11.dvdverleih.fachkonzept.IFachkonzept;

/**
 * Command Line Interface f�r den DVD-Verleih.
 * 
 * @author Timo Raschke
 * 
 */
public class TUI {
	private IFachkonzept fachkonzept;

	public static void main(String[] args) {
		TUI tui = new TUI(new Fachkonzept(new XMLDatenhaltung()));
	}

	public TUI(IFachkonzept fachkonzept) {
		this.fachkonzept = fachkonzept;
		this.showMainMenu();
	}

	private void showMainMenu() {
		char auswahl;
		boolean korrekt;
		do {
			do {
				korrekt = true;
				
				System.out.println("DVD-Verleih");
				System.out.println("-----------\n");
				System.out.println("(a) alle Kunden anzeigen");
				System.out.println("(b) Kunde anzeigen");
				System.out.println("(c) Neuer Kunde");
				System.out.println("(d) Kunde bearbeiten");
				System.out.println("(e) Kunde l�schen\n");
				// #########################################
				System.out.println("(f) alle DVDs anzeigen");
				System.out.println("(g) DVD anzeigen");
				System.out.println("(h) Neue DVD");
				System.out.println("(i) DVD bearbeiten");
				System.out.println("(j) DVD l�schen\n");
				// #########################################
				System.out.println("(k) alle Leihvorg�nge anzeigen");
				System.out.println("(l) Leihvorg�nge eines Kunden anzeigen");
				System.out.println("(m) Neuer Leihvorgang\n");
				// #########################################
				System.out.println("(x) Ende\n\n");
				System.out.print("Bitte w�hlen Sie einen Men�punkt: ");
				auswahl = Helper.readToLowerChar();

				switch (auswahl) {
				case 'a':
					alleKundenAnzeigen();
				case 'b':
					kundeAnzeigen();
					break;
				case 'c':
					neuerKunde();
					break;
				case 'd':
					kundeBearbeiten();
					break;
				case 'e':
					kundeLoeschen();
					break;
				case 'f':
					alleDvdsAnzeigen();
					break;
				case 'g':
					dvdAnzeigen();
					break;
				case 'h':
					neueDvd();
					break;
				case 'i':
					dvdBearbeiten();
					break;
				case 'j':
					dvdLoeschen();
					break;
				case 'k':
					alleLeihvorgaengeAnzeigen();
					break;
				case 'l':
					leihvorg�ngeEinesKundenAnzeigen();
					break;
				case 'm':
					neuerLeihvorgang();
					break;
				case 'x':
					break;
				default:
					korrekt = false;
					System.out
							.println("Sie haben eine ung�ltige Auswahl getroffen!");
					Helper.warteAufTaste();
					break;
				}
			} while (!korrekt);
		} while (auswahl != 'x');
		System.out.println("Auf wiedersehen!");
		Helper.warteAufTaste();
	}

	private void alleDvdsAnzeigen() {
		System.out.println("DVD-�bersicht\n");
		for (DVD dvd : fachkonzept.getAllDVDs()) {
			System.out.println(dvd.getDvd_nr() + " " + dvd.getTitel() + " "
					+ dvd.getGenre() + " " + dvd.getErscheinungsjahr());

		}
		Helper.warteAufTaste();
	}

	private void alleKundenAnzeigen() {
		System.out.println("Kunden�bersicht\n");
		if (fachkonzept.getAllKunden() != null) {
			for (Kunde kunde : fachkonzept.getAllKunden()) {
				System.out.println(kunde.getKunden_nr() + " "
						+ kunde.getAnrede() + " " + kunde.getVorname() + " "
						+ kunde.getNachname() + " " + kunde.getStrasse() + " "
						+ kunde.getHausnummer() + " " + kunde.getPlz() + " "
						+ kunde.getOrt());
			}
		} else {
			System.out.println("Es wurden keine Kunden gefunden!");
		}
		Helper.warteAufTaste();
	}

	private void kundeAnzeigen() {
		int kundennummer;
		Kunde kunde;
		System.out.println("Kunden anzeigen\n");
		System.out.print("Kundennummer: ");
		kundennummer = Helper.readInt();
		kunde = fachkonzept.getKundeByID(kundennummer);
		if (kunde != null) {
			System.out.println("Kundennummer: " + kunde.getKunden_nr());
			System.out.println("Anrede: " + kunde.getAnrede());
			System.out.println("Vorname: " + kunde.getVorname());
			System.out.println("Nachname: " + kunde.getNachname());
			System.out.println("Stra�e: " + kunde.getStrasse() + " "
					+ kunde.getHausnummer());
			System.out.println("PLZ: " + kunde.getPlz() + " " + "Ort: "
					+ kunde.getOrt());
			System.out.println("Telefon: " + kunde.getTelefon_nummer());
			System.out.println("Geburtstag: " + kunde.getGeburtstag());
		} else {
			System.out.println("Kunde nicht vorhanden!");
		}
		Helper.warteAufTaste();
	}

	private void neuerKunde() {
		String anrede;
		String vorname;
		String nachname;
		Date geburtstag;
		String plz;
		String ort;
		String strasse;
		int hausnummer;
		String telefon_nummer;
		SimpleDateFormat sdfGeburtstag = new SimpleDateFormat("dd.MM.yyyy");
		boolean korrekt = true;

		System.out.print("Anrede: ");
		anrede = Helper.readString();
		System.out.print("Vorname: ");
		vorname = Helper.readString();
		System.out.print("Nachname: ");
		nachname = Helper.readString();
		System.out.print("Stra�e: ");
		strasse = Helper.readString();
		System.out.print("Hausnummer: ");
		hausnummer = Helper.readInt();
		System.out.print("PLZ: ");
		plz = Helper.readString();
		System.out.print("Ort: ");
		ort = Helper.readString();
		do {
			korrekt = true;
			System.out.print("Geburtstag (DD.MM.YYYY): ");
			try {
				geburtstag = sdfGeburtstag.parse(Helper.readString());
			} catch (ParseException e) {
				korrekt = false;
			}
		} while (!korrekt);
		System.out.print("Telefonnummer: ");
		telefon_nummer = Helper.readString();
		// fachkonzept.createKunde(new Kunde(anrede, vorname, nachname,
		// geburtstag, plz, ort, strasse, hausnummer, telefon_nummer));
		System.out.println("\nKunde wurde hinzugef�gt!");
		Helper.warteAufTaste();
	}

	private void kundeBearbeiten() {

	}

	private void kundeLoeschen() {
		System.out.print("Kundennummer des zu l�schenden Kunden: ");
		try {
			Kunde kunde = fachkonzept.getKundeByID(Helper.readInt());
			System.out.println("M�chten Sie Kunde \"" + kunde.getKunden_nr()
					+ " " + kunde.getVorname() + " " + kunde.getNachname()
					+ "\" wirklich l�schen? (j/n) ");
			if (Helper.readToLowerChar() == 'j') {
				fachkonzept.deleteKunde(kunde.getKunden_nr());
				System.out.println("Der Kunde wurde gel�scht!");
			}
		} catch (NullPointerException e) {
			System.out.println("Kunde nicht vorhanden!");
		}
		Helper.warteAufTaste();
	}

	private void dvdAnzeigen() {
		int nummer;
		DVD dvd;
		System.out.print("DVD-Nummer: ");
		nummer = Helper.readInt();
		dvd = fachkonzept.getDVDByID(nummer);
		System.out.println("DVD-Nummer: " + dvd.getDvd_nr());
		System.out.println("Titel: " + dvd.getTitel());
		System.out.println("Genre: " + dvd.getGenre());
		System.out.println("Jahr: " + dvd.getErscheinungsjahr());
		Helper.warteAufTaste();
	}

	private void neueDvd() {
		String titel;
		String genre;
		int erscheinungsjahr;
		System.out.print("Titel: ");
		titel = Helper.readString();
		System.out.print("Genre: ");
		genre = Helper.readString();
		System.out.print("Jahr: ");
		erscheinungsjahr = Helper.readInt();
		fachkonzept.createDVD(new DVD(0, titel, genre, erscheinungsjahr));
		System.out.println("DVD wurde hinzugef�gt!");
		Helper.warteAufTaste();
	}

	private void dvdBearbeiten() {

	}

	private void dvdLoeschen() {
		System.out.print("Nummer der zu l�schenden DVD: ");
		try {
			DVD dvd = fachkonzept.getDVDByID(Helper.readInt());
			System.out.println("M�chten Sie die DVD \"" + dvd.getDvd_nr() + " "
					+ dvd.getTitel() + " " + dvd.getErscheinungsjahr()
					+ "\" wirklich l�schen? (j/n) ");
			if (Helper.readToLowerChar() == 'j') {
				fachkonzept.deleteDVD(dvd.getDvd_nr());
				System.out.println("Die DVD wurde gel�scht!");
			}
		} catch (NullPointerException e) {
			System.out.println("DVD nicht vorhanden!");
		}
		Helper.warteAufTaste();
	}

	private void alleLeihvorgaengeAnzeigen() {
		System.out.println("Leihvorgang-�bersicht\n");
		try {
			for (Verleih verleih : fachkonzept.getAllVerleihe()) {
				Kunde kunde = fachkonzept.getKundeByID(verleih.getKunden_nr());
				DVD dvd = fachkonzept.getDVDByID(verleih.getDvd_nr());
				// Nr Kunde DVD Ausleihe Rueckgabe
				System.out.println(verleih.getLeihvorgangs_nr() + " "
						+ kunde.getAnrede() + " " + kunde.getVorname() + " "
						+ kunde.getNachname() + " " + dvd.getTitel() + " "
						+ dvd.getErscheinungsjahr() + " "
						+ verleih.getAusleihe().toString() + " "
						+ verleih.getRueckgabe().toString());

			}
		} catch (NullPointerException npe) {
			System.out.println("Keine Ausleihen vorhanden!");
		}
		Helper.warteAufTaste();
	}

	private void leihvorg�ngeEinesKundenAnzeigen() {
		System.out.println("Leihvorgang-�bersicht\n");
		System.out.print("Kundennummer: ");
		int temp = Helper.readInt();
		try {
			for (Verleih verleih : fachkonzept.getKundenVerleih(temp)) {
				Kunde kunde = fachkonzept.getKundeByID(verleih.getKunden_nr());
				DVD dvd = fachkonzept.getDVDByID(verleih.getDvd_nr());
				// Nr Kunde DVD Ausleihe Rueckgabe
				System.out.println(verleih.getLeihvorgangs_nr() + " "
						+ kunde.getAnrede() + " " + kunde.getVorname() + " "
						+ kunde.getNachname() + " " + dvd.getTitel() + " "
						+ dvd.getErscheinungsjahr() + " "
						+ verleih.getAusleihe().toString() + " "
						+ verleih.getRueckgabe().toString());

			}
		} catch (NullPointerException npe) {
			System.out.println("Keine Ausleihen vorhanden!");
		}
		Helper.warteAufTaste();
	}

	private void neuerLeihvorgang() {
//		fachkonzept.createVerleih(new Verleih(leihvorgangs_nr, dvd_nr, kunden_nr, ausleihe, rueckgabe));
	}

}
