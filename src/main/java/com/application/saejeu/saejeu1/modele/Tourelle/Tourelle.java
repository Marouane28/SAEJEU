package com.application.saejeu.saejeu1.modele.Tourelle;

import com.application.saejeu.saejeu1.modele.Environnement;
import com.application.saejeu.saejeu1.modele.Zombie.Acteur;
import com.application.saejeu.saejeu1.vue.VueTourelle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Tourelle {
    private IntegerProperty x, y, coûtAmélioration; // Coordonnées x, y de la tourelle et son niveau (1, 2 ou 3)
    private int portée; // Portée d'attaque de la tourelle
    private int pv; // Points de vie de la tourelle
    private int dégât; // Dégâts infligés par la tourelle
    protected Environnement environnement; // Référence à l'environnement du jeu
    protected Acteur cible; // Référence à l'ennemi actuellement ciblé
    private String nomImage; // Nom du fichier d'image associé à la tourelle
    private VueTourelle vueTourelle; // Référence à la vue de la tourelle
    private IntegerProperty coût; // Propriété représentant le coût de la tourelle
    private boolean estAméliorée; // Indicateur indiquant si la tourelle est améliorée ou non

    public Tourelle(int x, int y, int portée, int pv, int dégât, Environnement env, String nomImage, int coût) {
        this.x = new SimpleIntegerProperty(x); // Propriété représentant la position x de la tourelle
        this.y = new SimpleIntegerProperty(y); // Propriété représentant la position y de la tourelle
        this.portée = portée; // Portée de la tourelle
        this.pv = pv; // Points de vie de la tourelle
        this.dégât = dégât; // Dégât infligé par la tourelle
        this.environnement = env; // Référence à l'environnement dans lequel se trouve la tourelle
        this.nomImage = nomImage; // Nom de l'image associée à la tourelle
        this.coût = new SimpleIntegerProperty(coût); // Propriété représentant le coût de la tourelle
        this.estAméliorée = false; // La tourelle n'est pas améliorée par défaut
        this.coûtAmélioration = new SimpleIntegerProperty(100); // Propriété représentant le coût d'amélioration initial de la tourelle
    }

    public void améliorer() {
        // Méthode pour améliorer la tourelle
        if (this.environnement.getPièces() >= this.getCoûtAmélioration()) {
            // Vérification si l'environnement a suffisamment de pièces pour l'amélioration
            this.setDégât(getDégât() + 10); // Augmentation du dégât de la tourelle de 10
            this.vueTourelle.retirerRayonPortee(); // Retrait du rayon de portée de la vue de la tourelle
            this.setPortée(getPortée() + 2*16); // Augmentation de la portée de la tourelle de 2*16 (à définir unité de mesure)
            this.vueTourelle.afficherRayonPorteeAmélioré(); // Affichage du rayon de portée amélioré dans la vue de la tourelle
            this.setPv(getPv() + 30); // Augmentation des points de vie de la tourelle de 30
            this.setEstAméliorée(true); // La tourelle est maintenant améliorée
            this.environnement.gagnerUnCertainNombreDePièce(-getCoûtAmélioration()); // Réduction du nombre de pièces de l'environnement par le coût d'amélioration
            this.vueTourelle.actualiserBarreEtat(); // actualiser barre de vie/
        }
    }

    public final int getCoûtAmélioration() {
        // Méthode pour obtenir le coût d'amélioration de la tourelle
        return this.coûtAmélioration.getValue();
    }

    public IntegerProperty getCoûtAmProperty() {
        // Méthode pour obtenir la propriété représentant le coût d'amélioration de la tourelle
        return this.coûtAmélioration;
    }

    public boolean isEstAméliorée() {
        // Méthode pour vérifier si la tourelle est améliorée
        return this.estAméliorée;
    }

    public void setEstAméliorée(boolean b) {
        // Méthode pour définir si la tourelle est améliorée
        this.estAméliorée = b;
    }

    public IntegerProperty getCoûtProperty() {
        // Méthode pour obtenir la propriété représentant le coût de la tourelle
        return this.coût;
    }

    public Environnement getEnvironnement() {
        // Méthode pour obtenir l'environnement dans lequel se trouve la tourelle
        return this.environnement;
    }

    public final int getCoût() {
        // Méthode pour obtenir le coût de la tourelle
        return this.coût.getValue();
    }

    // Méthode pour obtenir le nom du fichier d'image associé à la tourelle
    public String getNomImage() {

        return this.nomImage;
    }
    // Méthodes pour accéder aux propriétés x et y de la tourelle
    public IntegerProperty xProperty() {
        return this.x;
    }

    public IntegerProperty yProperty() {
        return this.y;
    }
    // Méthodes pour obtenir et définir la valeur de la coordonnée x de la tourelle
    public final double getX() {
        return this.xProperty().getValue();
    }

    // Méthodes pour obtenir et définir la valeur de la coordonnée y de la tourelle
    public final double getY() {
        return this.yProperty().getValue();
    }

    // Méthode pour obtenir la portée d'attaque de la tourelle
    public int getPortée() {
        return this.portée;
    }
    public void setPortée(int i) {
        this.portée = i;
    }
    // Méthode pour obtenir les points de vie de la tourelle
    public int getPv() {
        return this.pv;
    }

    public void setPv(int i) {
        this.pv = i;
    }

    // Méthode pour obtenir les dégâts infligés par la tourelle
    public int getDégât() {
        return this.dégât;
    }
    // Méthode pour définir les dégâts infligés par la tourelle
    public void setDégât(int dégât) {
        this.dégât = dégât;
    }
    // Méthode pour définir la cible de la tourelle
    public void setCible(Acteur cible) {
        this.cible = cible;
    }

    // Méthode abstraite pour effectuer l'attaque de la tourelle (à implémenter dans les sous-classes)
    public abstract void attaquer();
    // Méthode pour vérifier si l'ennemi est à portée de la tourelle

    public boolean estEnPortée(Acteur ennemi) {
        // Calcul de la distance entre la tourelle et l'ennemi en utilisant le théorème de Pythagore
        double distance = Math.sqrt(Math.pow(ennemi.getX() - getX(), 2) + Math.pow(ennemi.getY() - getY(), 2)); //racine de (x - x')^2 + (y - y')^2
        // Vérification si la distance est inférieure ou égale à la portée de la tourelle
        return distance <= this.portée;
    }

    // Méthode pour définir la vue de la tourelle
    public void setVueTourelle(VueTourelle vueTourelle) {
        this.vueTourelle = vueTourelle;
    }

    // Méthode pour décrémenter les points de vie de la tourelle et actualiser la barre d'état correspondante dans la vue
    public void décrémenterPv(int valeur) {
        this.pv -= valeur;
        this.vueTourelle.actualiserBarreEtat();
    }

    // Méthode pour vérifier si la tourelle est en marche (a encore des points de vie)
    public boolean estEnMarche() {
        return this.pv > 0;
    }
}