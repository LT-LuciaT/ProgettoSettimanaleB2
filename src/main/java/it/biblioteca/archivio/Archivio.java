package it.biblioteca.archivio;

import it.biblioteca.categorie.ElementoCatalogo;
import it.biblioteca.categorie.Libro;
import it.biblioteca.categorie.Rivista;
import it.biblioteca.exception.ElementoNonTrovatoException;

import java.util.*;
import java.util.stream.Collectors;

public class Archivio {
    private Map<String, ElementoCatalogo> catalogo;

    public Archivio() {
        catalogo = new HashMap<>();
    }

    public void aggiungiElemento(ElementoCatalogo elemento) {
        if (catalogo.containsKey(elemento.getIsbn())) {
            throw new IllegalArgumentException("Elemento con ISBN " + elemento.getIsbn() + " già presente.");
        }
        catalogo.put(elemento.getIsbn(), elemento);
    }
    public ElementoCatalogo ricercaPerIsbn(String isbn) throws ElementoNonTrovatoException {
        ElementoCatalogo elemento = catalogo.get(isbn);
        if (elemento == null) {
            throw new ElementoNonTrovatoException("Elemento con ISBN " + isbn + " non trovato.");
        }
        return elemento;
    }

    public void rimuoviElemento(String isbn) throws ElementoNonTrovatoException {
        if (!catalogo.containsKey(isbn)) {
            throw new ElementoNonTrovatoException("Elemento con ISBN " + isbn + " non trovato.");
        }
        catalogo.remove(isbn);
    }

    public List<ElementoCatalogo> ricercaPerAnnoPubblicazione(int anno) {
        return catalogo.values().stream()
                .filter(e -> e.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
    }
    public List<Libro> ricercaPerAutore(String autore) {
        return catalogo.values().stream()
                .filter(e -> e instanceof Libro)
                .map(e -> (Libro) e)
                .filter(l -> l.getAutore().equals(autore))
                .collect(Collectors.toList());
    }
    public void aggiornaElemento(String isbn, ElementoCatalogo nuovoElemento) throws ElementoNonTrovatoException {
        if (!catalogo.containsKey(isbn)) {
            throw new ElementoNonTrovatoException("Elemento con ISBN " + isbn + " non trovato.");
        }
        catalogo.put(isbn, nuovoElemento);
    }

    public void statisticheCatalogo() {
        long numeroLibri = catalogo.values().stream()
                .filter(e -> e instanceof Libro)
                .count();

        long numeroRiviste = catalogo.values().stream()
                .filter(e -> e instanceof Rivista)
                .count();

        Optional<ElementoCatalogo> elementoConPiuPagine = catalogo.values().stream()
                .max(Comparator.comparingInt(ElementoCatalogo::getNumeroPagine));

        double mediaPagine = catalogo.values().stream()
                .mapToInt(ElementoCatalogo::getNumeroPagine)
                .average()
                .orElse(0.0);

        System.out.println("Numero totale di libri: " + numeroLibri);
        System.out.println("Numero totale di riviste: " + numeroRiviste);
        elementoConPiuPagine.ifPresent(e -> System.out.println("Elemento con più pagine: " + e));
        System.out.println("Media delle pagine: " + mediaPagine);
    }
}


