package otaku;

public class QuestionVraiFaux extends Question {
    public QuestionVraiFaux(String anime, String enonce, int bonneReponse) {
        super(anime, enonce, bonneReponse);
        if (bonneReponse < 1 || bonneReponse > 2) {
            throw new IllegalArgumentException("La bonne réponse doit être 1 (vrai) ou 2 (faux).");
        }
    }

    @Override
    public String choix() {
        return "1. Vrai\n2. Faux";
    }

    @Override
    public int nombreDeChoix() {
        return 2;
    }

    @Override
    public int pointsPossibles() {
        return 1;
    }
}
