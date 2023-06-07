package com.application.saejeu.saejeu1.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.util.*;


public class Environnement {
    private int x, y; // Coordonnées de l'environnement
    private TileMap tileMap; // Carte de tuiles
    private ObservableList<Acteur> acteurs; // Liste des acteurs présents dans l'environnement
    private Map<Sommet, Set<Sommet>> listeAdj; // Liste d'adjacence pour la recherche de chemin
    private ObservableList<Sommet> obstacles; // Liste des sommets représentant les obstacles
    private ObservableList<Tourelle> tourelles; // Liste des tourelles présentes dans l'environnement
    private final int OBSTACLE_TILE = 400; // Tuile utilisée pour représenter un obstacle

    private BFS bfs; // Algorithme BFS pour la recherche de chemin
    ArrayList<Sommet> chemin; // Chemin trouvé par l'algorithme BFS

    private int vies; // Nombre de vies restantes

    public Environnement(TileMap tileMap) throws IOException {
        this.tileMap = tileMap; // Initialise la carte de tuiles
        this.x = this.tileMap.getX() * this.tileMap.getTileSize(); // Calcule la largeur de l'environnement en pixels
        this.y = this.tileMap.getY() * this.tileMap.getTileSize(); // Calcule la hauteur de l'environnement en pixels
        this.acteurs = FXCollections.observableArrayList(); // Initialise la liste des acteurs
        this.tourelles = FXCollections.observableArrayList(); // Initialise la liste des tourelles
        this.listeAdj = new HashMap(); // Initialise la liste d'adjacence
        this.obstacles = FXCollections.observableArrayList(); // Initialise la liste des obstacles

        construit(); // Construit la liste d'adjacence
        bfs = new BFS(this, getSommet(0, 20)); // Initialise l'algorithme BFS avec un sommet source
        chemin = bfs.cheminVersSource(getSommet(89, 34)); // Trouve le chemin vers un sommet cible
        vies = 3; // Initialise le nombre de vies à 3
    }

    public BFS getBfs() {
        return bfs; // Renvoie l'algorithme BFS utilisé
    }

    public void construit() {
        int i;
        int j;
        // Parcours de la carte de jeu
        for (i = 0; i < this.getTileMap().getMapDeJeu().length; ++i) {
            for (j = 0; j < this.getTileMap().getMapDeJeu()[i].length; ++j) {
                // Vérifie la valeur de la case dans la carte de jeu
                if (this.tileMap.getMapDeJeu()[i][j] == 400) {
                    // Crée un sommet avec une valeur de poids de 400
                    Sommet s = new Sommet(i, j, 400);
                    this.listeAdj.put(s, new HashSet());
                } else {
                    // Crée un sommet avec une valeur de poids de 275
                    Sommet s = new Sommet(i, j, 275);
                    this.listeAdj.put(s, new HashSet());
                    s.setPoids(275);
                }
            }
        }
        // Affichage des clés de la listeAdj (pour le débogage)
        for (Sommet key : this.listeAdj.keySet()) {
            // System.out.println(" key dans construit " + key);
        }
        // Parcours de la carte de jeu pour connecter les sommets adjacents
        for (i = 0; i < this.getTileMap().getMapDeJeu().length; ++i) {
            for (j = 0; j < this.getTileMap().getMapDeJeu()[i].length; ++j) {
                Sommet s = this.getSommet(i, j);
                // Vérifie les cases adjacentes dans la grille
                if (this.dansGrille(i - 1, j)) {
                    ((Set) this.listeAdj.get(s)).add(this.getSommet(i - 1, j));
                }
                if (this.dansGrille(i + 1, j)) {
                    ((Set) this.listeAdj.get(s)).add(this.getSommet(i + 1, j));
                }
                if (this.dansGrille(i, j + 1)) {
                    ((Set) this.listeAdj.get(s)).add(this.getSommet(i, j + 1));
                }
                if (this.dansGrille(i, j - 1)) {
                    ((Set) this.listeAdj.get(s)).add(this.getSommet(i, j - 1));
                }
            }
        }
    }

    public boolean dansGrille(int x, int y) {
        // Vérifie si les coordonnées (x, y) se trouvent dans les limites de la grille
        return x >= 0 && x < this.getX() && y >= 0 && y < this.getY();
    }

    public Sommet getSommet(int x, int y) {
        // Parcours des sommets dans la listeAdj
        for (Sommet sommet : this.listeAdj.keySet()) {
            // Vérifie si les coordonnées (x, y) correspondent à celles du sommet
            if (sommet.getX() == x && sommet.getY() == y) {
                return sommet; // Retourne le sommet correspondant
            }
        }
        return null; // Aucun sommet trouvé, retourne null
    }

    public boolean estDeconnecte(Sommet s) {
        // Vérifie si le sommet est contenu dans la liste des obstacles
        return this.obstacles.contains(s);
    }

    public Set<Sommet> adjacents(Sommet s) {
        if (this.estDeconnecte(s)) {
            return Collections.emptySet(); // Le sommet est déconnecté, retourne un ensemble vide
        } else {
            Set<Sommet> adjacents = listeAdj.getOrDefault(s, new HashSet<>());
            // Retourne les sommets adjacents en filtrant ceux qui ont un poids différent du sommet d'origine
            adjacents.removeIf(adjacent -> adjacent != null && adjacent.getPoids() != s.getPoids());
            return adjacents; // Retourne l'ensemble des sommets adjacents
        }
    }

    public TileMap getTileMap() {
        return this.tileMap;
    }

    public int getX() {
        return x; // Renvoie la largeur de l'environnement
    }

    public void setX(int x) {
        this.x = x; // Modifie la largeur de l'environnement
    }

    public int getY() {
        return y; // Renvoie la hauteur de l'environnement
    }

    public void setY(int y) {
        this.y = y; // Modifie la hauteur de l'environnement
    }

    public ObservableList<Acteur> getActeurs() {
        return acteurs; // Renvoie la liste des acteurs présents dans l'environnement
    }

    public int getVies() {
        return vies; // Renvoie le nombre de vies restantes
    }

    public void decrementerVies() {
        vies--; // Décrémente le nombre de vies
    }

    public void ajouterActeur(Acteur a) {
        this.acteurs.add(a); // Ajoute un acteur à la liste des acteurs
    }

    public ObservableList<Tourelle> getTourelles() {
        return tourelles; // Renvoie la liste des tourelles présentes dans l'environnement
    }

    public void ajouterTourelle(Tourelle tourelle) {
        this.tourelles.add(tourelle); // Ajoute une tourelle à la liste des tourelles
    }

    public Acteur créerZombie() {
        Random rand = new Random();
        int nb = rand.nextInt(3 - 1 + 1) + 1;
        Acteur zombie = null;
        if (nb == 1) {
            zombie = new ZombieRapide(this);
        } else if (nb == 2) {
            zombie = new ZombieLent(this);
        } else {
            zombie = new ZombieGeant(this);
        }
        ajouterActeur(zombie);
        return zombie; // Crée un zombie et l'ajoute à la liste des acteurs
    }

    public boolean isNotObstacle(int x, int y) {
        if (this.getTileMap().getMapDeJeu()[x][y] == this.OBSTACLE_TILE) {
            return false;
        }
        return true; // Vérifie si la tuile à la position (x, y) est un obstacle
    }

    public ArrayList<Sommet> getChemin() {
        return chemin; // Renvoie le chemin trouvé par l'algorithme BFS
    }
}