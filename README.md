**How It Works**:

*Setup*:

The program initializes a list of Question objects in the QuizApplication class.
Each Question object contains the question text, multiple-choice options, and the correct answer index.

*Quiz Execution*:

The startQuiz method iterates through the list of questions.
For each question, the user has 15 seconds to provide an answer.
A timer is set for each question, and if the time runs out, the program moves to the next question.

*Answer Submission and Scoring*:

The user inputs their answer, and the program checks if the answer is correct.
The score is updated accordingly, and the result (correct/incorrect) is stored.

*Result Display*:

At the end of the quiz, the program displays the user's final score and a summary of their answers for each question.
