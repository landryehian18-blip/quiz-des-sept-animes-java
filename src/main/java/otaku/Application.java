package otaku;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        try {
            Partie partie = new Partie(new LecteurQuestions().charger(Path.of("questions.csv")));
            try (Scanner clavier = new Scanner(System.in)) {
                jouer(partie, clavier);
            }
        } catch (IOException erreur) {
            System.err.println("Impossible de charger questions.csv. " + erreur.getMessage());
        }
    }

    private static void jouer(Partie partie, Scanner clavier) {
        System.out.println("Quiz des sept animés");
        System.out.println("QCM = 2 points. Vrai ou faux = 1 point. Il faut au moins la moitié des points.");

        while (!partie.estTerminee()) {
            Question question = partie.questionCourante();
            System.out.println("\n[" + question.getAnime() + "] " + question.getEnonce());
            System.out.println(question.choix());

            boolean reponseAcceptee = false;
            while (!reponseAcceptee) {
                System.out.print("Ta réponse : ");
                if (!clavier.hasNextLine()) {
                    System.out.println("\nPartie interrompue.");
                    return;
                }
                try {
                    int reponse = Integer.parseInt(clavier.nextLine().trim());
                    int points = partie.repondre(reponse);
                    if (points > 0) {
                        System.out.println("Bonne réponse. +" + points + " point(s).");
                    } else {
                        System.out.println("Mauvaise réponse.");
                    }
                    reponseAcceptee = true;
                } catch (NumberFormatException erreur) {
                    System.out.println("Saisis un numéro.");
                } catch (ReponseInvalideException erreur) {
                    System.out.println(erreur.getMessage());
                }
            }
        }

        System.out.println("\nScore final : " + partie.getScore() + "/" + partie.scoreMaximum());
        if (partie.estReussie()) {
            System.out.println("Quiz réussi !");
        } else {
            System.out.println("Quiz à retenter !");
        }
    }
}
