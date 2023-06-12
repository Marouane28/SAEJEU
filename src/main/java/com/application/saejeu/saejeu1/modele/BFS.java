package com.application.saejeu.saejeu1.modele;

import java.util.*;

public class BFS {
    /**
     * Le graphe (ou grille) sur lequel on travaille
     */
    private Environnement map;
    /**
     * Le sommet source de l'algo
     */
    private Sommet source;
    /**
     * Liste des sommets de la composante connexe de g obtenue par un parcours en largeur depuis le sommet source
     */
    private ArrayList<Sommet> parcours;
    /**
     * Chaque sommet (clé) est associé à son prédécesseur (valeur) du parcours en largeur
     */
    private Map<Sommet, Sommet> predecesseurs;

    public BFS(Environnement map, Sommet source) {
        this.map = map;
        this.source = source;
        parcours = new ArrayList<>();
        predecesseurs = new HashMap<Sommet, Sommet>();
        algoBFS();
    }

    private void algoBFS() {
        LinkedList<Sommet> fifo = new LinkedList<>(); // Crée une file d'attente pour le parcours en largeur
        LinkedList<Sommet> marquage = new LinkedList<>(); // Liste des sommets marqués
        fifo.add(source); // Ajoute le sommet source à la file d'attente
        marquage.addFirst(source); // Marque le sommet source
        predecesseurs.put(source, null); // Définit le prédécesseur du sommet source comme null

        while (!fifo.isEmpty()) { // Tant que la file d'attente n'est pas vide
            Sommet suivant = fifo.pollLast(); // Récupère le prochain sommet de la file d'attente
            parcours.add(suivant); // Ajoute le sommet au parcours

            for (Sommet t : map.adjacents(suivant)) { // Pour chaque sommet adjacent à 'suivant' dans le graphe
                if (!marquage.contains(t)) { // Vérifie si le sommet n'est pas déjà marqué
                    marquage.addFirst(t); // Marque le sommet en l'ajoutant en premier dans la liste de marquage
                    fifo.addFirst(t); // Ajoute le sommet en premier dans la file d'attente
                    predecesseurs.put(t, suivant); // Définit 'suivant' comme le prédécesseur de 't' dans le parcours
                }
            }
        }
    }

    public ArrayList<Sommet> cheminVersSource(Sommet cible) {
        ArrayList<Sommet> chemin = new ArrayList<>(); // Liste pour stocker le chemin depuis la cible jusqu'à la source
        Sommet courant = cible; // Démarre avec la cible comme sommet courant

        while (courant != null) { // Tant que le sommet courant n'est pas null (pas encore atteint la source)
            chemin.add(courant); // Ajoute le sommet courant au chemin
            courant = predecesseurs.get(courant); // Met à jour le sommet courant en récupérant son prédécesseur dans le parcours
        }

        Collections.reverse(chemin); // Inverse l'ordre des sommets dans le chemin pour avoir le chemin de la source à la cible
        System.out.println("Le chemin vers le sommet cible:");
        for (Sommet s : chemin) {
            System.out.println("" + s); // Affiche chaque sommet du chemin
        }

        return chemin; // Retourne le chemin de la source à la cible
    }
}

