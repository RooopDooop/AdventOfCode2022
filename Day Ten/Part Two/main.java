import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
class CPU {
    HashMap<Integer, Integer> SignalHistory = new HashMap<>();
    int currentCycle = 0;
    int XValue = 1;
    CRT objCRT = new CRT();

    public CPU() {
        SignalHistory.put(currentCycle, XValue);
    }

    public void addNoop() {
        currentCycle++;
        SignalHistory.put(currentCycle, XValue);
        objCRT.insertRow(this.SignalHistory.get(currentCycle));
    }

    public void addX(int X) {
        for (int i = 1; i < 3; i++) {
            currentCycle++;
            SignalHistory.put(currentCycle, XValue);
            objCRT.insertRow(this.SignalHistory.get(currentCycle));
        }

        XValue += X;
    }

    public int calculateSum() {
        int sumNumber = 0;

        sumNumber += 20 * this.SignalHistory.get(20);
        sumNumber += 60 * this.SignalHistory.get(60);
        sumNumber += 100 * this.SignalHistory.get(100);
        sumNumber += 140 * this.SignalHistory.get(140);
        sumNumber += 180 * this.SignalHistory.get(180);
        sumNumber += 220 * this.SignalHistory.get(220);

        return sumNumber;
    }
}

class CRT {
    //Start from left to right
    //String[][] Screen = new String[6][40];

    List<String> ScreenOne = new ArrayList<>();
    List<String> ScreenTwo = new ArrayList<>();
    List<String> ScreenThree = new ArrayList<>();
    List<String> ScreenFour = new ArrayList<>();
    List<String> ScreenFive = new ArrayList<>();
    List<String> ScreenSix = new ArrayList<>();

    public void insertRow(int XValue) {
        if (ScreenOne.size() < 40) {

        } else if (ScreenTwo.size() < 80) {

        } else if (ScreenThree.size() < 120) {

        } else if (ScreenFour.size() < 160) {

        } else if (ScreenFive.size() < 200) {

        } else if (ScreenSix.size() < 240) {

        }


        /*for (int drawn = 0; drawn < XValue; drawn++) {
            if (drawn == XValue - 1) {
                System.out.print("### \n");

                if (Screen.size() == XValue) {
                    Screen.add("#");
                } else {
                    Screen.add(".");
                }
            } else {
                System.out.print(".");
            }
        }*/
    }

    public void DrawScreen() {
        //System.out.println(Screen);
        /*for (String[] row : Screen) {
            for (String pixel : row) {
                System.out.print(pixel);
            }
            System.out.println();
        }*/
    }
}

class TenTwo {
    public static void main(String[] args) throws IOException {
        List<String> listCommands = Files.readAllLines(Path.of("inputData.txt"));
        CPU objCPU = new CPU();

        for (String Command : listCommands) {
            if (Command.equals("noop")) {
                objCPU.addNoop();
            } else {
                int X = Integer.parseInt(Command.split(" ")[1]);
                objCPU.addX(X);
            }
        }

        /*System.out.println(objCPU.SignalHistory);
        System.out.println(objCPU.calculateSum());*/


        //objCRT.DrawScreen();
        //objCRT.insertRow(objCPU.SignalHistory.get(1));
    }
} 