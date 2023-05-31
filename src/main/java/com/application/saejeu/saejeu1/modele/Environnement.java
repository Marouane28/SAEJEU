package com.application.saejeu.saejeu1.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.*;


public class Environnement {
    private int x,y;
    private int[][] tileMap;
    private ArrayList<Acteur> acteurs;
    private Map<Sommet, Set<Sommet>> listeAdj;
    private ObservableList<Sommet> obstacles;
    private ArrayList<Tourelle> tourelles;
    private final int OBSTACLE_TILE = 400;

    public Environnement(int x , int y) throws IOException {
        this.x = x;
        this.y = y;
        this.tileMap = new int[x][y];
        this.acteurs = new ArrayList<>();
        this.tourelles=new ArrayList<>();
        this.listeAdj = new HashMap();
        this.obstacles = FXCollections.observableArrayList();

        readMap();
        construit();
    }

    public void readMap() throws IOException {
        File file = new File(getClass().getResource("vraietilemap").getFile());
        BufferedReader terrain = new BufferedReader(new FileReader(file));
        String ligne;
        String[] tout_ligne;

        try {
            int x = 0;
            while ((ligne = terrain.readLine()) != null) {
                tout_ligne = ligne.split(",");
                for (int y = 0; y < tout_ligne.length; y++) {
                    if (!tout_ligne[y].trim().isEmpty()) {
                        this.tileMap[y][x] = Integer.parseInt(tout_ligne[y].trim());
                    }
                }
                x++;
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }

        try {
            terrain.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void construit() {
        int i;
        int j;
        for(i = 0; i < this.x; ++i) {
            for(j = 0; j < this.y; ++j) {

                if (tileMap[i][j] == 400) {
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
        for(i = 0; i < this.x; ++i) {
            for(j = 0; j < this.y; ++j) {
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
            return new HashSet<>();
        } else {
            Set<Sommet> adjacents = new HashSet<>(this.listeAdj.get(s));//il prends tous les sommets adjacents de sommet s
            adjacents.removeIf(adjacent -> adjacent.getPoids() != s.getPoids());//il suprime tous les sommet que les poids ne sont pas égale
            //System.out.println(" "+adjacents);
            return adjacents;
        }
    }

    public int[][] getTileMap() {
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

    public ArrayList<Acteur> getActeurs() {
        return acteurs;
    }

    public void ajouterActeur(Acteur a) {
        this.acteurs.add(a);
    }

    public ArrayList<Tourelle> getTourelles() {
        return tourelles;
    }

    public void ajouterTourelle(Tourelle tourelle){
        this.tourelles.add(tourelle);
    }

    public boolean isNotObstacle(int x, int y) {
            if (this.getTileMap()[x][y] == this.OBSTACLE_TILE) {

                return false;
            }
        return true;
    }

}