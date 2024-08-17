import java.util.*;
import java.util.concurrent.*;

public class QuizGame {

    static class Question {
        String questionText;
        String[] options;
        int correctIndex;

        public Question(String questionText, String[] options, int correctIndex) {
            this.questionText = questionText;
            this.options = options;
            this.correctIndex = correctIndex;
        }
    }

    private List<Question> questions = new ArrayList<>();
    private int score = 0;

    public QuizGame() {
        // Initialize questions
        questions.add(new Question("What is the capital of France?", new String[] {"Berlin", "Madrid", "Paris", "Rome"}, 2));
        questions.add(new Question("What is 2 + 2?", new String[] {"3", "4", "5", "6"}, 1));
        questions.add(new Question("What is the color of the sky?", new String[] {"Red", "Blue", "Green", "Yellow"}, 1));
        questions.add(new Question("What is the largest planet in our solar system?", new String[] {"Earth", "Jupiter", "Saturn", "Venus"}, 1));
        questions.add(new Question("Who wrote 'Romeo and Juliet'?", new String[] {"Mark Twain", "Charles Dickens", "William Shakespeare", "Ernest Hemingway"}, 2));
        questions.add(new Question("What is the chemical symbol for water?", new String[] {"O2", "H2O", "CO2", "H2"}, 1));
        questions.add(new Question("Which element has the highest atomic number?", new String[] {"Oganesson", "Uranium", "Carbon", "Hydrogen"}, 0));
        questions.add(new Question("What is the first element on the periodic table?", new String[] {"Helium", "Hydrogen", "Lithium", "Beryllium"}, 1));
        questions.add(new Question("In what year did the Titanic sink?", new String[] {"1912", "1905", "1920", "1898"}, 0));
        questions.add(new Question("Who painted the Mona Lisa?", new String[] {"Pablo Picasso", "Vincent Van Gogh", "Leonardo da Vinci", "Claude Monet"}, 2));
        // You can add more questions here
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        for (Question question : questions) {
            System.out.println(question.questionText);
            for (int i = 0; i < question.options.length; i++) {
                System.out.println((i + 1) + ". " + question.options[i]);
            }
            System.out.println("You have 10 seconds to answer.");

            // Create a FutureTask to handle timing
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<Integer> future = executor.submit(() -> {
                int answer = 0;
                try {
                    answer = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. You will get 0 points.");
                }
                return answer - 1; // Adjusting for 0-indexing
            });

            // Use a timer to limit answering time
            try {
                Integer answer = future.get(10, TimeUnit.SECONDS);
                if (answer != null && answer == question.correctIndex) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Incorrect! The correct answer was: " + question.options[question.correctIndex]);
                }
            } catch (TimeoutException | InterruptedException | ExecutionException e) {
                System.out.println("Time's up! The correct answer was: " + question.options[question.correctIndex]);
                future.cancel(true);
            } finally {
                executor.shutdown();
            }
            System.out.println();
        }
        displayResults();
    }

    private void displayResults() {
        System.out.println("Quiz Finished!");
        System.out.println("Your final score: " + score + "/" + questions.size());
    }

    public static void main(String[] args) {
        QuizGame quizGame = new QuizGame();
        quizGame.startQuiz();
    }
}