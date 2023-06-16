package com.application.saejeu.saejeu1.controleur;
import com.application.saejeu.saejeu1.modele.Projectile.Projectile;
import com.application.saejeu.saejeu1.vue.VueProjectile;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
public class ListObsProjectile implements ListChangeListener<Projectile> {


    private Pane panneauDeJeu;// Le panneau du jeu où seront ajoutées les vues des projectiles
    private ArrayList<VueProjectile> vuesProjectile;// Liste des vues des projectiles

    public ListObsProjectile(Pane panneauDeJeu) {
        this.panneauDeJeu = panneauDeJeu;// Initialise le panneau du jeu
        this.vuesProjectile = new ArrayList<>();// Initialise la liste des vues des projectiles
    }

    @Override
    public void onChanged(Change<? extends Projectile> change) {

        while (change.next()) {
            if (change.wasAdded()) {
                for (Projectile p : change.getAddedSubList()) {
                    ajouterProjectile(p); // Ajoute la vue de chaque nouveau projectile
                }
            }
            if (change.wasRemoved()) {
                for (Projectile p : change.getRemoved()) {
                    enleverProjectile(p); // Retire la vue de chaque projectile supprimé
                }
            }
        }
    }

    private void ajouterProjectile(Projectile p){
        VueProjectile vueProjectile = new VueProjectile(panneauDeJeu, p);// Crée une nouvelle vue pour le projectile
        vuesProjectile.add(vueProjectile); // Ajoute la vue à la liste des vues des projectiles
        p.setVueProjectile(vueProjectile);// Associe la vue au projectile
    }


    private void enleverProjectile (Projectile proj){
        VueProjectile vueProjectileToRemove = null;
        for (VueProjectile vueProjectile : vuesProjectile) {
            if (vueProjectile.getProjectile() == proj) { // Recherche la vue correspondant au projectile supprimé
                vueProjectileToRemove = vueProjectile; // Stocke la vue à supprimer
                break;
            }
        }

        if (vueProjectileToRemove != null) {
            vueProjectileToRemove.retirerImageProjectile(proj); // Retire l'image du projectile dans la vue
            vuesProjectile.remove(vueProjectileToRemove); // Supprime la vue des projectiles
        }
    }
}