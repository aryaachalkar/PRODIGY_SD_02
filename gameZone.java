import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class gameZone {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Fun Guess Game");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 1, 10, 10));  // one extra row for play again button

        JLabel promptLabel = new JLabel("Enter your guess:");
        JTextField inputField = new JTextField();

        JButton guessButton = new JButton("Guess");
        guessButton.setBackground(Color.PINK);
        guessButton.setForeground(Color.BLACK);

        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);
        JLabel attemptsLabel = new JLabel("", SwingConstants.CENTER);

        JButton playAgainButton = new JButton("Do you want to play again?");
        playAgainButton.setVisible(false);  // initially hidden

        // Game state holder
        Random random = new Random();
        final int[] numberToGuess = {random.nextInt(100) + 1};
        final int[] attempts = {0};

        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userInput = inputField.getText();
                try {
                    int guess = Integer.parseInt(userInput);
                    attempts[0]++;

                    if (guess < numberToGuess[0]) {
                        if (guess < numberToGuess[0] / 2) {
                            resultLabel.setText("Too low! Try again");
                        } else {
                            resultLabel.setText("A bit low! Try again");
                        }
                        attemptsLabel.setText("Attempts: " + attempts[0]);
                    } else if (guess > numberToGuess[0]) {
                        if (guess > numberToGuess[0] * 2) {
                            resultLabel.setText("Too high! Try again");
                        } else {
                            resultLabel.setText("A bit high! Try again");
                        }
                        attemptsLabel.setText("Attempts: " + attempts[0]);
                    } else {
                        resultLabel.setText("Correct! The number was " + numberToGuess[0]);
                        attemptsLabel.setText("You guessed it in " + attempts[0] + " attempts.");
                        guessButton.setEnabled(false);
                        playAgainButton.setVisible(true);
                    }

                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid input! Please enter a number");
                    attemptsLabel.setText("");
                }
            }
        });

        playAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Reset state
                numberToGuess[0] = random.nextInt(100) + 1;
                attempts[0] = 0;
                inputField.setText("");
                resultLabel.setText("");
                attemptsLabel.setText("");
                guessButton.setEnabled(true);
                playAgainButton.setVisible(false);
            }
        });

        // Add components to frame
        frame.add(promptLabel);
        frame.add(inputField);
        frame.add(guessButton);
        frame.add(resultLabel);
        frame.add(attemptsLabel);
        frame.add(playAgainButton);  // new button

        frame.setVisible(true);
    }
}
