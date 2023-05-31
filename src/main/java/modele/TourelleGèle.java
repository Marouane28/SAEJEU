package modele;

public class TourelleGèle extends Tourelle {


    public TourelleGèle(int x, int y, int portee, int pv, int degat, Environnement env) {
        super(x, y, portee, pv, degat, env);
    }

    public TourelleGèle(){

    }
    @Override
    public void attaquer() {
        if (cible != null && estEnPortee(cible)) {
            // Ralentir le zombie en réduisant sa vitesse de déplacement
            cible.setGele(true);
            // Effectuer l'attaque sur l'ennemi ciblé
            cible.decrementerPv(getDegat());
            System.out.println("Tourelle Gèle l'ennemi !");
        } else {
            // L'ennemi n'est plus à portée ou aucun ennemi n'est ciblé
            cible.setGele(false);
            System.out.println("Aucune cible valide pour la tourelle Gèle !");
        }
    }


}