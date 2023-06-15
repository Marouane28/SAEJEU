package com.application.saejeu.saejeu1.controleur;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    private static Clip clipFond;      // Variable pour la musique de fond
    private static Clip clipVictoire;  // Variable pour la musique de victoire
    private static Clip clipDefaite;   // Variable pour la musique de défaite

    public static void PlayMusicFond(String location) {
        AudioInputStream audioInputStream = null;
        try {
            // Obtention de l'AudioInputStream à partir du fichier audio spécifié par l'emplacement
            audioInputStream = AudioSystem.getAudioInputStream(new File(location));
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Création de l'objet DataLine.Info pour le clip audio
        DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
        try {
            // Obtention de la ligne de données pour le clip audio
            clipFond = (Clip) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            // Ouverture du clip audio avec l'AudioInputStream
            clipFond.open(audioInputStream);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Démarrage de la lecture du clip audio de fond
        clipFond.start();
    }

    public static boolean verifSon() {
        // Vérification si le clip de musique de fond est en cours de lecture
        if (!clipFond.isRunning()) {
            return false;
        }
        return true;
    }

    public static void stopMusicFond() {
        // Arrêt et fermeture du clip de musique de fond s'il est en cours de lecture
        if (clipFond != null && clipFond.isRunning()) {
            clipFond.stop();
            clipFond.close();
        }
    }

    public static void PlayMusicVictoire(String location) {
        AudioInputStream audioInputStream = null;
        try {
            // Obtention de l'AudioInputStream à partir du fichier audio spécifié par l'emplacement
            audioInputStream = AudioSystem.getAudioInputStream(new File(location));
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Création de l'objet DataLine.Info pour le clip audio
        DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
        try {
            // Obtention de la ligne de données pour le clip audio
            clipVictoire = (Clip) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            // Ouverture du clip audio avec l'AudioInputStream
            clipVictoire.open(audioInputStream);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Démarrage de la lecture du clip audio de victoire
        clipVictoire.start();
    }

    public static void stopMusicVictoire() {
        // Arrêt et fermeture du clip de musique de victoire s'il est en cours de lecture
        if (clipVictoire != null && clipVictoire.isRunning()) {
            clipVictoire.stop();
            clipVictoire.close();
        }
    }

    public static void PlayMusicDefaite(String location) {
        AudioInputStream audioInputStream = null;
        try {
            // Obtention de l'AudioInputStream à partir du fichier audio spécifié par l'emplacement
            audioInputStream = AudioSystem.getAudioInputStream(new File(location));
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Création de l'objet DataLine.Info pour le clip audio
        DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
        try {
            // Obtention de la ligne de données pour le clip audio
            clipDefaite = (Clip) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            // Ouverture du clip audio avec l'AudioInputStream
            clipDefaite.open(audioInputStream);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Démarrage de la lecture du clip audio de défaite
        clipDefaite.start();
    }

    public static void stopMusicDefaite() {
        // Arrêt et fermeture du clip de musique de défaite s'il est en cours de lecture
        if (clipDefaite != null && clipDefaite.isRunning()) {
            clipDefaite.stop();
            clipDefaite.close();
        }
    }
}
