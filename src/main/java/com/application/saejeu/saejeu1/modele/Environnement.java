package com.application.saejeu.saejeu1.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.*;


public class Environnement {
    private int x,y;
    private TileMap tileMap;
    private ObservableList<Acteur> acteurs;
    private Map<Sommet, Set<Sommet>> listeAdj;
    private ObservableList<Sommet> obstacles;
    private ObservableList<Tourelle> tourelles;
    private final int OBSTACLE_TILE = 400;
    private IntegerProperty pièces;
    private BFS bfs;
    private ArrayList<Sommet> chemin;
    private Tourelle tourelle;

    public Environnement(TileMap tileMap) throws IOException {
        this.tileMap = tileMap;
        this.x = this.tileMap.getX() * this.tileMap.getTileSize();
        this.y = this.tileMap.getY() * this.tileMap.getTileSize();
        this.acteurs = FXCollections.observableArrayList();
        this.tourelles=FXCollections.observableArrayList();
        this.listeAdj = new HashMap();
        this.obstacles = FXCollections.observableArrayList();
        this.pièces = new SimpleIntegerProperty(0);

        construit();
        bfs= new BFS(this,getSommet(0,20));
        chemin=bfs.cheminVersSource(getSommet(89,34));

    }

    public IntegerProperty getPropertyPièces() {

        return this.pièces;
    }
    public int getPièces() {

        return this.getPropertyPièces().getValue();
    }

    public void setPièces(int i) {

        this.pièces.set(i);
    }
    public void gagnerUnePièce() {

        this.pièces.set(this.getPièces() + 1);
    }

    public void gagnerUnCertainNombreDePièce(int i) {

        this.pièces.set(this.getPièces() + i);
    }

    public BFS getBfs() {
        return bfs;
    }

    public void construit() {
        int i;
        int j;
        for(i = 0; i < this.getTileMap().getMapDeJeu().length; ++i) {
            for(j = 0; j < this.getTileMap().getMapDeJeu()[i].length; ++j) {

                if (this.tileMap.getMapDeJeu()[i][j] == 400) {
                    Sommet s = new Sommet(i, j,400);
                    //System.out.println("dans case 400 ");
                    this.listeAdj.put(s,new HashSet());
                } else{
                    Sommet s = new Sommet(i, j,275);
                    this.listeAdj.put(s, new HashSet());
                    s.setPoids(275);
                }
            }
        }
        for (Sommet key : this.listeAdj.keySet()) {
            //System.out.println(" key dans coustruit " + key);
        }
        for(i = 0; i < this.getTileMap().getMapDeJeu().length; ++i) {
            for(j = 0; j < this.getTileMap().getMapDeJeu()[i].length; ++j) {
                Sommet s = this.getSommet(i, j);
                if (this.dansGrille(i - 1, j)) {
                    ((Set)this.listeAdj.get(s)).add(this.getSommet(i - 1, j));
                }

                if (this.dansGrille(i + 1, j)) {
                    ((Set)this.listeAdj.get(s)).add(this.getSommet(i + 1, j));
                }

                if (this.dansGrille(i, j + 1)) {
                    ((Set)this.listeAdj.get(s)).add(this.getSommet(i, j + 1));
                }

                if (this.dansGrille(i, j - 1)) {
                    ((Set)this.listeAdj.get(s)).add(this.getSommet(i, j - 1));
                }
            }
        }

    }

    public boolean dansGrille(int x, int y) {
        return x >= 0 && x < this.getX() && y >= 0 && y < this.getY();
    }

    public Sommet getSommet(int x, int y) {
        for (Sommet sommet : this.listeAdj.keySet()) {
            if (sommet.getX() == x && sommet.getY() == y) {
                return sommet;
            }
        }
        return null;
    }

    public boolean estDeconnecte(Sommet s) {
        return this.obstacles.contains(s);
    }

    public Set<Sommet> adjacents(Sommet s) {
        if (this.estDeconnecte(s)) {
            return Collections.emptySet();
        } else {
            Set<Sommet> adjacents = listeAdj.getOrDefault(s, new HashSet<>());
            adjacents.removeIf(adjacent -> adjacent != null && adjacent.getPoids() != s.getPoids());
            return adjacents;
        }
    }

    public TileMap getTileMap() {
        return this.tileMap;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ObservableList<Acteur> getActeurs() {
        return acteurs;
    }

    public void ajouterActeur(Acteur a) {
        this.acteurs.add(a);
    }

    public ObservableList<Tourelle> getTourelles() {
        return tourelles;
    }

    public void ajouterTourelle(Tourelle tourelle){
        this.tourelles.add(tourelle);
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

        return zombie;
    }

    public boolean isNotObstacle(int x, int y) {
            if (this.getTileMap().getMapDeJeu()[x][y] == this.OBSTACLE_TILE) {
                return false;
            }
        return true;
    }

    public ArrayList<Sommet> getChemin() {
        return chemin;
    }




}