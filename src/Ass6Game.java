//id:213214026
import biuoop.GUI;
import game.AnimationRunner;
import game.GameFlow;
import screens.DirectHit;
import screens.FinalFour;
import screens.Green3;
import screens.LevelInformation;
import screens.WideEasy;

import java.util.ArrayList;
import java.util.List;


/**
 * running the game methods.
 * */

public class Ass6Game {
    /**
     * main method.
     * @param args cmd args
     * */
    public static void main(String[] args) {
        GUI gui = new GUI("Araknoid", 800, 600);
        GameFlow flow = new GameFlow(gui, new AnimationRunner(gui, 60));
        List<LevelInformation> list = new ArrayList<>();
        addLevelsToList(args, list);
        if (list.isEmpty()) {
           list.add(new DirectHit());
           list.add(new WideEasy());
           list.add(new Green3());
           list.add(new FinalFour());
        }
        flow.runLevels(list);
    }
    /**
     * adds levels to the list.
     * @param args the levels' numbers
     * @param list the list of levels
     * */
    private static void addLevelsToList(String[] args, List<LevelInformation> list) {
        for (String arg: args) {
            if (isInteger(arg)) {
                switch (Integer.parseInt(arg)) {
                    case 1: list.add(new DirectHit());
                    break;
                    case 2: list.add(new WideEasy());
                    break;
                    case 3: list.add(new Green3());
                    break;
                    case 4: list.add(new FinalFour());
                    break;
                    default: break;
                }
            }
        }
    }

    /**
     * @return if a string represents an integer.
     * @param s the string
     * */
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
