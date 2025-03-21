package it.biblioteca;

import it.biblioteca.archivio.Archivio;
import it.biblioteca.categorie.ElementoCatalogo;
import it.biblioteca.categorie.Libro;
import it.biblioteca.categorie.Rivista;
import it.biblioteca.exception.ElementoNonTrovatoException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Archivio archivio = new Archivio();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nScegli un'operazione:");
            System.out.println("1. Aggiungi elemento");
            System.out.println("2. Ricerca per ISBN");
            System.out.println("3. Rimuovi elemento");
            System.out.println("4. Ricerca per anno pubblicazione");
            System.out.println("5. Ricerca per autore");
            System.out.println("6. Aggiorna elemento");
            System.out.println("7. Statistiche catalogo");
            System.out.println("8. Esci");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (scelta) {

                    case 1:
                        System.out.println("Inserisci tipo (Libro/Rivista):");
                        String tipo = scanner.nextLine();
                        System.out.println("Inserisci ISBN:");
                        String isbn = scanner.nextLine();
                        System.out.println("Inserisci titolo:");
                        String titolo = scanner.nextLine();
                        System.out.println("Inserisci anno pubblicazione:");
                        int anno = scanner.nextInt();
                        System.out.println("Inserisci numero pagine:");
                        int pagine = scanner.nextInt();
                        scanner.nextLine(); // Consuma il newline

                        if (tipo.equalsIgnoreCase("Libro")) {
                            System.out.println("Inserisci autore:");
                            String autore = scanner.nextLine();
                            System.out.println("Inserisci genere:");
                            String genere = scanner.nextLine();
                            archivio.aggiungiElemento(new Libro(isbn, titolo, anno, pagine, autore, genere));
                }
                else if (tipo.equalsIgnoreCase("Rivista")) {
                            System.out.println("Inserisci periodicità (SETTIMANALE, MENSILE, SEMESTRALE):");
                            Rivista.Periodicita periodicita = Rivista.Periodicita.valueOf(scanner.nextLine().toUpperCase());
                            archivio.aggiungiElemento(new Rivista(isbn, titolo, anno, pagine, periodicita));
                        }
                        break;

                    case 2:
                        System.out.println("Inserisci ISBN:");
                        String isbnRicerca = scanner.nextLine();
                        ElementoCatalogo elemento = archivio.ricercaPerIsbn(isbnRicerca);
                        System.out.println("Elemento trovato: " + elemento);
                        break;

                    case 3:
                        System.out.println("Inserisci ISBN:");
                        String isbnRimozione = scanner.nextLine();
                        archivio.rimuoviElemento(isbnRimozione);
                        System.out.println("Elemento rimosso.");
                        break;

                    case 4:
                        System.out.println("Inserisci anno pubblicazione:");
                        int annoRicerca = scanner.nextInt();
                        scanner.nextLine(); // Consuma il newline
                        archivio.ricercaPerAnnoPubblicazione(annoRicerca).forEach(System.out::println);
                        break;

                    case 5:
                        System.out.println("Inserisci autore:");
                        String autoreRicerca = scanner.nextLine();
                        archivio.ricercaPerAutore(autoreRicerca).forEach(System.out::println);
                        break;

                    case 6:
                        System.out.println("Inserisci ISBN dell'elemento da aggiornare:");
                        String isbnAggiornamento = scanner.nextLine();
                        System.out.println("Inserisci nuovo titolo:");
                        String nuovoTitolo = scanner.nextLine();
                        System.out.println("Inserisci nuovo anno pubblicazione:");
                        int nuovoAnno = scanner.nextInt();
                        System.out.println("Inserisci nuovo numero pagine:");
                        int nuovePagine = scanner.nextInt();
                        scanner.nextLine(); // Consuma il newline

                        System.out.println("Inserisci tipo (Libro/Rivista):");
                        String nuovoTipo = scanner.nextLine();
                        if (nuovoTipo.equalsIgnoreCase("Libro")) {
                            System.out.println("Inserisci nuovo autore:");
                            String nuovoAutore = scanner.nextLine();
                            System.out.println("Inserisci nuovo genere:");
                            String nuovoGenere = scanner.nextLine();
                            archivio.aggiornaElemento(isbnAggiornamento, new Libro(isbnAggiornamento, nuovoTitolo, nuovoAnno, nuovePagine, nuovoAutore, nuovoGenere));
                        } else if (nuovoTipo.equalsIgnoreCase("Rivista")) {
                            System.out.println("Inserisci nuova periodicità (SETTIMANALE, MENSILE, SEMESTRALE):");
                            Rivista.Periodicita nuovaPeriodicita = Rivista.Periodicita.valueOf(scanner.nextLine().toUpperCase());
                            archivio.aggiornaElemento(isbnAggiornamento, new Rivista(isbnAggiornamento, nuovoTitolo, nuovoAnno, nuovePagine, nuovaPeriodicita));
                        }
                        break;

                    case 7:
                        archivio.statisticheCatalogo();
                        break;

                    case 8:
                        running = false;
                        break;

                    default:
                        System.out.println("Scelta non valida.");
                }
            } catch (ElementoNonTrovatoException | IllegalArgumentException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }

        scanner.close();
                        }
            }

