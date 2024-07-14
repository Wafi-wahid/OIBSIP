import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

class Play extends JPanel {

    final Game game;
    RandomNumber randomNumber = new RandomNumber();
    ScoringSystem scoringSystem = new ScoringSystem();
    ScoreFiles scoreFiles = new ScoreFiles();

    public Play(Game game) {
        this.game = game;
        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxlayout);
        createGUI();
    }

    /* TODO: creating game GUI */

    void createGUI() {
        JLabel playScore, gameText, mysteryNumber, statusImage;
        JButton continueButton, enterButton;
        JTextField inputText;
        JPanel gridPanel;
        int random = randomNumber.generateNumber();

        playScore = new JLabel("Score: " + scoreFiles.intScore("current_score.txt") + "   Games: " + scoreFiles.intScore("num_game.txt"));
        playScore.setFont(new Font("MV Boli", Font.BOLD, 24));
        playScore.setForeground(new Color(128, 0, 128));
        playScore.setBorder(new EmptyBorder(20, 0, 0, 0));
        playScore.setAlignmentX(CENTER_ALIGNMENT);
        add(playScore);

        gameText = new JLabel("Guess the Number");
        gameText.setForeground(new Color(128, 0, 128));
        gameText.setFont(new Font("Ink Free", Font.BOLD, 30));
        gameText.setBorder(new EmptyBorder(30, 0, 0, 0));
        gameText.setAlignmentX(CENTER_ALIGNMENT);
        add(gameText);

        JLabel gameText2 = new JLabel("between 1 and 5");
        gameText2.setForeground(new Color(128, 0, 128));
        gameText2.setFont(new Font("Ink Free", Font.BOLD, 30));
        gameText2.setBorder(new EmptyBorder(5, 0, 0, 0));
        gameText2.setAlignmentX(CENTER_ALIGNMENT);
        add(gameText2);

        mysteryNumber = new JLabel("?");
        mysteryNumber.setIcon(new ImageIcon("mystery_square.png"));
        mysteryNumber.setHorizontalTextPosition(JLabel.CENTER);
        mysteryNumber.setFont(new Font("MV Boli", Font.BOLD, 100));
        mysteryNumber.setForeground(new Color(0x62355F));
        mysteryNumber.setAlignmentX(CENTER_ALIGNMENT);
        add(mysteryNumber);

        statusImage = new JLabel("Input a number");
        statusImage.setForeground(new Color(128, 0, 128));
        statusImage.setFont(new Font("MV Boli", Font.BOLD, 30));
        statusImage.setAlignmentX(CENTER_ALIGNMENT);
        statusImage.setBorder(BorderFactory.createEmptyBorder(-10, 0, 15, 0));
        statusImage.setBorder(new EmptyBorder(20, 30, 20, 30)); 
        add(statusImage);

        gridPanel = new JPanel();
        gridPanel.setMaximumSize(new Dimension(260, 50));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        gridPanel.setLayout(new GridLayout(0, 2));
        gridPanel.setOpaque(false);

        inputText = new JTextField();
        inputText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        inputText.setBackground(new Color(253, 233, 180));
        inputText.setForeground(new Color(0x62355F));
        inputText.setHorizontalAlignment(JTextField.CENTER);
        inputText.setBorder(BorderFactory.createLineBorder(new Color(128, 0, 128), 3));
        inputText.setFont(new Font("MV Boli", Font.BOLD, 28));
        gridPanel.add(inputText);

        enterButton = new JButton("Enter");
        enterButton.setForeground(new Color(128, 0, 128));
        enterButton.setFont(new Font("MV Boli", Font.BOLD, 25));
        enterButton.setBackground(new Color(128, 0, 128));
        enterButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        enterButton.setHorizontalAlignment(JTextField.CENTER);
        enterButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gridPanel.add(enterButton);
        add(gridPanel);
        

        continueButton = new JButton("Continue Playing?");
    
        continueButton.setForeground(new Color(128, 0, 128));
        continueButton.setBackground(new Color(128, 0, 128));
        continueButton.setFont(new Font("MV Boli", Font.BOLD, 20));
        continueButton.setPreferredSize(new Dimension(250, 50));
        continueButton.setBorder(BorderFactory.createLineBorder(new Color(128, 0, 128), 3));
       
        continueButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        continueButton.setAlignmentX(CENTER_ALIGNMENT);
        continueGame(continueButton);
        add(continueButton);
        
        

        JButton backButton1 = new JButton("Back to Menu");
        backButton1.setFont(new Font("MV Boli", Font.BOLD, 20));
        backButton1.setForeground(new Color(128, 0, 128));
        backButton1.setBackground(new Color(128, 0, 128));
        backButton1.setPreferredSize(new Dimension(200, 50));
        backButton1.setBorder(BorderFactory.createLineBorder(new Color(128, 0, 128), 3));
        
   

        backButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton1.setAlignmentX(CENTER_ALIGNMENT);
        linkMenu(backButton1);
        add(backButton1);

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeStatus(inputText, mysteryNumber, random, statusImage, gridPanel, continueButton, backButton1);
            }
        });

        inputText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeStatus(inputText, mysteryNumber, random, statusImage, gridPanel, continueButton, backButton1);
            }
        });
    }

    public void linkMenu(JButton jLabel) {
        jLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Want to Stop the Game?", "Are you Sure", JOptionPane.YES_NO_OPTION);
                if (dialogResult == 0) {
                    scoreFiles.compareScore("high_score.txt", "current_score.txt", "num_game.txt");
                    game.showView(new Menu(game));
                }
            }
        });
    }

    public void continueGame(JButton jLabel) {
        jLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game.showView(new Play(game));
            }
        });
    }

  
    void changeStatus(JTextField input, JLabel mysterynum, int randnum, JLabel status, JPanel gridPanel, JButton contButton, JButton backButton) {
        if (String.valueOf(randnum).equals(input.getText())) {
            status.setText("Correct!");
            status.setFont(new Font("MV Boli", Font.BOLD, 30));
            status.setForeground(new Color(0, 255, 0));
            status.setPreferredSize(new Dimension(200, 50));

            mysterynum.setText(input.getText());

            gridPanel.setVisible(false);

            contButton.setVisible(true);
            scoringSystem.scoreAttempt();
            scoreFiles.write("current_score.txt", scoreFiles.intScore("current_score.txt") + scoringSystem.getScore());
            scoreFiles.write("num_game.txt", scoreFiles.intScore("num_game.txt") + 1);

        } else {
            try {
                int textToInt = Integer.parseInt(input.getText());
                if (textToInt > 5 || textToInt < 1) {
                    status.setText("Out of Range");
                    status.setFont(new Font("MV Boli", Font.BOLD, 30));
                    status.setForeground(new Color(128,0,128));
                    status.setPreferredSize(new Dimension(200, 50));

                } else if (textToInt > randnum) {
                    status.setText("Too High!! Try Again");
                    status.setFont(new Font("MV Boli", Font.BOLD, 30));
                    status.setForeground(new Color(255, 165, 0));
                    status.setPreferredSize(new Dimension(200, 50));

                } else {
                    status.setText("Too Low!! Try Again");
                    status.setFont(new Font("MV Boli", Font.BOLD, 30));
                    status.setForeground(new Color(0, 0, 255));
                    status.setPreferredSize(new Dimension(200, 50));
                }
            } catch (Exception error) {
                status.setText("Not a Number");
                status.setFont(new Font("MV Boli", Font.BOLD, 30));
                status.setForeground(new Color(255, 0, 0));
                status.setPreferredSize(new Dimension(200, 50));
            }
        }
        input.setText("");
    }
}

 /* TODO: Game class */

class Game {
    private JFrame frame;

    public Game() {
        frame = new JFrame("Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void showView(JPanel panel) {
        frame.setContentPane(panel);
        frame.revalidate();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.showView(new Menu(game));
    }
}

 /* TODO: Menu class */

class Menu extends JPanel {
   public Game game;

    public Menu(Game game) {
        this.game = game;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(50, 50, 50, 50));
        add(new JLabel("Menu"));
        JButton startButton = new JButton("Start Game");
        startButton.setAlignmentX(CENTER_ALIGNMENT);
        add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.showView(new Play(game));
            }
        });
    }
}

 /* TODO: Class generating random number */

class RandomNumber {
    public int generateNumber() {
        return (int) (Math.random() * 5 + 1);
    }
}

 /* TODO: Scoring system */

 class ScoringSystem {
    int score, attempts;

    public ScoringSystem() {
        this.attempts = 1;
    }

    public int getScore() {
        return score;
    }

    public int getAttempts() {
        return attempts;
    }

    public void scoreAttempt(){
        if(this.attempts == 1)
            this.score += 5;
        else if(this.attempts == 2)
            this.score += 2;
        else
            this.score++;
    }

    public void incrementAttempt(){
        this.attempts++;
    }
}

/* TODO: Storing score files */
class ScoreFiles {

    public String showScore(String filename){
        String score = "0";
        try{
            File text = new File(filename);
            Scanner scan = new Scanner(text);
            score = scan.nextLine();
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return score;
    }
    public String showGames(String filename){
        String attempt = "0";
        try{
            File text = new File(filename);
            Scanner scan = new Scanner(text);
            scan.nextLine();
            attempt = scan.nextLine();
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return attempt;
    }

    // Convert the showScore method to int
    public int intScore(String filename){
        return Integer.parseInt(showScore(filename));
    }

    // Convert the showsGames method to int
    public int intGames(String filename){
        return Integer.parseInt(showGames(filename));
    }

    // Used in current_score.txt and num_game.txt
    public void write(String filename, int score){
        try{
            FileWriter score_writer = new FileWriter(filename);
            score_writer.write(String.valueOf(score));
            score_writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Overwrites the text file
    // Used in high_score.txt
    public void writeScoreAttempts(String filename, int score, int attemtps){
        try{
            FileWriter score_writer = new FileWriter(filename);
            score_writer.write(String.valueOf(score));
            score_writer.write("\n");
            score_writer.write(String.valueOf(attemtps));
            score_writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Compare the score if it is a high score
    public void compareScore(String high_score, String current_score, String current_played_games){
        if (intScore(high_score) < intScore(current_score)){
            writeScoreAttempts(high_score, intScore(current_score), intScore(current_played_games));
        }
        else if(intScore(high_score) == intScore(current_score)){
            if (intGames(high_score) > intScore(current_played_games)){
                writeScoreAttempts(high_score, intScore(current_score), intScore(current_played_games));
            }
        }
    }
}



/* TODO: Main class */

class Main {
    public static void main(String[] args) {
        new Game();
    }
}