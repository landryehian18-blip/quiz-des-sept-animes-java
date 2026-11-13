package otaku;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LecteurQuestions {
    public List<Question> charger(Path chemin) throws IOException {
        List<String> lignes = Files.readAllLines(chemin, StandardCharsets.UTF_8);
        if (lignes.isEmpty() || !lignes.get(0).equals("type,anime,question,option1,option2,option3,reponse")) {
            throw new IOException("En-tête du CSV incorrect ou manquant.");
        }

        List<Question> questions = new ArrayList<>();
        for (int index = 1; index < lignes.size(); index++) {
            String ligne = lignes.get(index).trim();
            if (!ligne.isEmpty()) {
                try {
                    questions.add(lireQuestion(ligne));
                } catch (IllegalArgumentException erreur) {
                    throw new IOException("Ligne " + (index + 1) + " : " + erreur.getMessage(), erreur);
                }
            }
        }
        if (questions.isEmpty()) {
            throw new IOException("Le CSV ne contient aucune question.");
        }
        return questions;
    }

    private Question lireQuestion(String ligne) {
        String[] champs = ligne.split(",", -1);
        if (champs.length != 7) {
            throw new IllegalArgumentException("Sept champs sont attendus.");
        }

        String anime = champs[1].trim();
        String enonce = champs[2].trim();
        int bonneReponse = Integer.parseInt(champs[6].trim());

        if (champs[0].trim().equals("QCM")) {
            return new QuestionQcm(anime, enonce, champs[3].trim(), champs[4].trim(),
                    champs[5].trim(), bonneReponse);
        }
        if (champs[0].trim().equals("VF")) {
            return new QuestionVraiFaux(anime, enonce, bonneReponse);
        }
        throw new IllegalArgumentException("Type inconnu. Utiliser QCM ou VF.");
    }
}
