package it.biblioteca;

import it.biblioteca.archivio.Archivio;
import it.biblioteca.categorie.Libro;
import it.biblioteca.categorie.Rivista;

public class Test {
    public static void main(String[] args) {
        Archivio archivio = new Archivio();

        archivio.aggiungiElemento(new Libro("12345", "Il Signore degli Anelli", 1954, 1000, "J.R.R. Tolkien", "Fantasy"));
        archivio.aggiungiElemento(new Rivista("67890", "National Geographic", 2020, 120, Rivista.Periodicita.MENSILE));

        System.out.println("Test 1: Verifica l'aggiunta di elementi");
        System.out.println("Catalogo dopo l'aggiunta:");
        archivio.statisticheCatalogo();

        System.out.println("\nTest 2: Ricerca per ISBN");
        try {
            System.out.println("Ricerca per ISBN 12345:");
            System.out.println(archivio.ricercaPerIsbn("12345"));
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }

        System.out.println("\nTest 3: Rimozione di un elemento");
        try {
            archivio.rimuoviElemento("67890");
            System.out.println("Rivista con ISBN 67890 rimossa.");
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
        System.out.println("Catalogo dopo la rimozione:");
        archivio.statisticheCatalogo();

        System.out.println("\nTest 4: Ricerca per anno di pubblicazione (1954)");
        System.out.println("Risultati della ricerca:");
        archivio.ricercaPerAnnoPubblicazione(1954).forEach(System.out::println);

        System.out.println("\nTest 5: Ricerca per autore (J.R.R. Tolkien)");
        System.out.println("Risultati della ricerca:");
        archivio.ricercaPerAutore("J.R.R. Tolkien").forEach(System.out::println);

        System.out.println("\nTest 6: Aggiornamento di un elemento");
        try {
            archivio.aggiornaElemento("12345", new Libro("12345", "Il Signore degli Anelli", 1954, 1200, "J.R.R. Tolkien", "Fantasy"));
            System.out.println("Libro aggiornato.");
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
        System.out.println("Catalogo dopo l'aggiornamento:");
        archivio.statisticheCatalogo();

        System.out.println("\nTest 7: Statistiche del catalogo");
        archivio.statisticheCatalogo();
    }
}
