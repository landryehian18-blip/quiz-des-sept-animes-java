package otaku;

public abstract class Question {
    private final String anime;
    private final String enonce;
    private final int bonneReponse;

    protected Question(String anime, String enonce, int bonneReponse) {
        if (anime.isEmpty() || enonce.isEmpty()) {
            throw new IllegalArgumentException("L'animé et l'énoncé sont obligatoires.");
        }
        this.anime = anime;
        this.enonce = enonce;
        this.bonneReponse = bonneReponse;
    }

    public String getAnime() {
        return anime;
    }

    public String getEnonce() {
        return enonce;
    }

    public abstract String choix();

    public abstract int nombreDeChoix();

    public abstract int pointsPossibles();

    public int pointsPour(int reponse) throws ReponseInvalideException {
        if (reponse < 1 || reponse > nombreDeChoix()) {
            throw new ReponseInvalideException("Choisis un numéro de 1 à " + nombreDeChoix() + ".");
        }
        if (reponse == bonneReponse) {
            return pointsPossibles();
        }
        return 0;
    }
}
