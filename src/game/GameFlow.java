//id:213214026
package game;

import biuoop.GUI;
import objects.Counter;
import screens.GameOver;
import screens.KeyPressStoppableAnimation;
import screens.LevelInformation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

/**
 * controls the game flow.
 * */
public class GameFlow {
    private GUI gui;
    private AnimationRunner runner;
    private boolean hasWon;
    /**
     * creates a game flow object.
     * @param gui the interface
     * @param runner animation runner
     * */
    public GameFlow(GUI gui, AnimationRunner runner) {
        this.gui = gui;
        this.runner = runner;
        hasWon = true;
    }
    /**
     * runs the levels.
     * @param levels the lise of levels
     * */
    public void runLevels(List<LevelInformation> levels) {
        int highScore = 0;
        File file = new File("src/highscores.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String str = br.readLine();
            while (str != null) {
                highScore = Integer.parseInt(str);
            }
            if (br != null) {
                br.close();
            }
        } catch (Exception e) {

        }
        Counter score = new Counter();
        for (LevelInformation levelInfo:levels) {
            GameLevel level = new GameLevel(levelInfo, score, this.gui);
            level.initialize();
            while (level.getRemainingBalls() > 0 && level.getRemainingBlocks() > 0) {
                level.run();
                if (level.getRemainingBlocks() == 0) {
                    break;
                }
            }
            if (level.getRemainingBalls() == 0) {
                hasWon = false;
                break;
            }
        }
        if (score.getValue() > highScore) {
            try {
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file)));
                writer.println("The highest score so far is: " + score.getValue());
                writer.close();
            } catch (Exception e) {
            }
        }
        runner.run(new KeyPressStoppableAnimation(gui.getKeyboardSensor(), gui.getKeyboardSensor().SPACE_KEY,
                new GameOver(hasWon, score.getValue())));

        gui.close();
    }

}
