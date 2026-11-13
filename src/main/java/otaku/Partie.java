package otaku;

import java.util.ArrayList;
import java.util.List;

public class Partie {
    private final List<Question> questions;
    private int index;
    private int score;

    public Partie(List<Question> questions) {
        if (questions.isEmpty()) {
            throw new IllegalArgumentException("La partie doit contenir au moins une question.");
        }
        this.questions = new ArrayList<>(questions);
    }

    public boolean estTerminee() {
        return index == questions.size();
    }

    public Question questionCourante() {
        if (estTerminee()) {
            throw new IllegalStateException("La partie est terminée.");
        }
        return questions.get(index);
    }

    public int repondre(int reponse) throws ReponseInvalideException {
        int points = questionCourante().pointsPour(reponse);
        score += points;
        index++;
        return points;
    }

    public int getScore() {
        return score;
    }

    public int scoreMaximum() {
        int maximum = 0;
        for (Question question : questions) {
            maximum += question.pointsPossibles();
        }
        return maximum;
    }

    public boolean estReussie() {
        return estTerminee() && score * 2 >= scoreMaximum();
    }
}
