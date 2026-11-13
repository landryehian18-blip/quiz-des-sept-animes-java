package otaku;

public class QuestionQcm extends Question {
    private final String premierChoix;
    private final String deuxiemeChoix;
    private final String troisiemeChoix;

    public QuestionQcm(String anime, String enonce, String premierChoix,
                       String deuxiemeChoix, String troisiemeChoix, int bonneReponse) {
        super(anime, enonce, bonneReponse);
        if (premierChoix.isEmpty() || deuxiemeChoix.isEmpty() || troisiemeChoix.isEmpty()) {
            throw new IllegalArgumentException("Un QCM doit proposer trois choix.");
        }
        if (bonneReponse < 1 || bonneReponse > 3) {
            throw new IllegalArgumentException("La bonne réponse du QCM doit être entre 1 et 3.");
        }
        this.premierChoix = premierChoix;
        this.deuxiemeChoix = deuxiemeChoix;
        this.troisiemeChoix = troisiemeChoix;
    }

    @Override
    public String choix() {
        return "1. " + premierChoix + "\n2. " + deuxiemeChoix + "\n3. " + troisiemeChoix;
    }

    @Override
    public int nombreDeChoix() {
        return 3;
    }

    @Override
    public int pointsPossibles() {
        return 2;
    }
}
