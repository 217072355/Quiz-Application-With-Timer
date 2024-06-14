import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication {
    private List<Question> questions;
    private int score;
    private List<Boolean> results;

    public QuizApplication() {
        questions = new ArrayList<>();
        score = 0;
        results = new ArrayList<>();
        loadQuestions();
    }

    private void loadQuestions() {
        questions.add(new Question("What is the capital of France?", new String[]{"Berlin", "Paris", "Madrid", "Rome"}, 2));
        questions.add(new Question("What is the largest planet?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 3));
        questions.add(new Question("Who wrote 'To Kill a Mockingbird'?", new String[]{"Harper Lee", "Jane Austen", "Mark Twain", "Ernest Hemingway"}, 1));
        // Add more questions as needed
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println(question);
            System.out.println("You have 15 seconds to answer this question.");

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Time's up! Moving to the next question.");
                    synchronized (scanner) {
                        scanner.notify();
                    }
                }
            };
            timer.schedule(task, 15000); // 15 seconds timer

            int answer = -1;
            synchronized (scanner) {
                try {
                    answer = scanner.nextInt();
                } catch (Exception e) {
                    // Ignore invalid input
                } finally {
                    scanner.nextLine(); // Consume the newline
                    scanner.notify();
                }
            }
            timer.cancel();

            if (answer == question.getCorrectAnswer()) {
                score++;
                results.add(true);
            } else {
                results.add(false);
            }
        }

        scanner.close();
        displayResults();
    }

    private void displayResults() {
    	System.out.println("\n");
        System.out.println("\nQuiz Over!");
        System.out.println("\n");
        System.out.println("Your final score is: " + score + " out of " + questions.size());

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            boolean correct = results.get(i);
            System.out.println((i + 1) + ". " + question.getQuestion());
            System.out.println("Your answer was: " + (correct ? "Correct" : "Incorrect"));
        }
    }
}
