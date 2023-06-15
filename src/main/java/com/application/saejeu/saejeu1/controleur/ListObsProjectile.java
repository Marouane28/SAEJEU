package com.application.saejeu.saejeu1.controleur;
import com.application.saejeu.saejeu1.modele.Projectile;
import com.application.saejeu.saejeu1.vue.VueProjectile;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
public class ListObsProjectile implements ListChangeListener<Projectile> {


    private Pane panneauDeJeu;
    private ArrayList<VueProjectile> vuesProjectile;

    public ListObsProjectile(Pane panneauDeJeu) {
        this.panneauDeJeu = panneauDeJeu;
        this.vuesProjectile = new ArrayList<>();
    }

    @Override
    public void onChanged(Change<? extends Projectile> change) {

        while (change.next()) {
            if (change.wasAdded()) {
                for (Projectile proj : change.getAddedSubList()) {
                    VueProjectile vueProjectile = new VueProjectile(panneauDeJeu, proj);
                    vuesProjectile.add(vueProjectile);
                    proj.setVueProjectile(vueProjectile);
                }
            }
            if (change.wasRemoved()) {
                for (Projectile proj : change.getRemoved()) {
                    enleverProjectile(proj);
                }
            }
        }
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