# Quiz des sept animés

Jeu en console autour de Naruto, Dragon Ball, Shingeki no Kyojin, Demon Slayer, Death Note, Hunter x Hunter et My Hero Academia. Le joueur répond à une question par série avec le fichier fourni. Un QCM vaut 2 points et un vrai ou faux 1 point. Une réponse hors limites laisse la question ouverte. Pour réussir, le joueur doit obtenir au moins la moitié des points, soit 6 sur 11 avec les questions fournies.

## Lancer avec IntelliJ IDEA 2026.2.2

1. Décompresser l'archive, puis ouvrir le dossier `EHIAN Ange Claude-Landry` avec **File > Open**. Importer le projet Maven si IntelliJ le propose.
2. Sélectionner le JDK 26 déjà installé, ou un JDK 17 ou supérieur, dans **File > Project Structure > Project SDK**.
3. Ouvrir `Application.java` et cliquer sur le triangle vert à côté de `main`. Le répertoire de travail doit être le dossier qui contient `questions.csv`.
4. Répondre dans la console avec le numéro du choix, puis appuyer sur Entrée.

Pour les tests, ouvrir `PartieTest.java` et lancer la classe avec le triangle vert. Le cycle **test** dans la fenêtre Maven ou la commande `mvn test` lance aussi les tests. Le premier import peut nécessiter une connexion pour télécharger JUnit et les plugins Maven.

## Organisation du code

| Classe | Rôle |
| --- | --- |
| `Question` | Classe abstraite qui contient l'animé, l'énoncé et la bonne réponse. Elle vérifie la saisie et calcule les points obtenus. |
| `QuestionQcm` et `QuestionVraiFaux` | Héritent de `Question` et redéfinissent les choix, le nombre de choix et le barème. |
| `Partie` | Gère la liste de questions, la progression, le score et le seuil de réussite. |
| `LecteurQuestions` | Lit le CSV au démarrage et construit les objets. |
| `Application` | Affiche le quiz et lit les réponses au clavier. |
| `ReponseInvalideException` | Signale un choix hors limites. La console affiche la plage autorisée et redemande une réponse. |

## Choix techniques

1. **Héritage et polymorphisme.** La classe abstraite évite de répéter les champs communs et la validation de la réponse. `Partie` manipule une `List<Question>`. Quand le programme appelle `pointsPossibles()`, le type réel de la question détermine le barème.
2. **Responsabilité unique et principe ouvert/fermé.** La console, la lecture du fichier et les règles du jeu ont chacune leur classe. Un nouveau type de question peut définir son barème sans changer `Partie`. Il faut apprendre au lecteur à reconnaître son type dans le CSV.
3. **Encapsulation du score.** `Partie.repondre()` valide la réponse avant de modifier le score ou d'avancer. Le seuil est calculé à partir du total des questions chargées.
4. **Questions modifiables dans un CSV.** Le fichier utilise UTF-8 et sept colonnes. Dans une ligne `QCM`, écrire trois options et le numéro de la bonne réponse. Dans une ligne `VF`, laisser les trois options vides et écrire `1` pour vrai ou `2` pour faux. Éviter les virgules dans les énoncés et les options. Un fichier invalide affiche une erreur de chargement.
