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

    public void DrawScreen() {
        for (Integer signal : this.SignalHistory.values()) {
            this.objCRT.insertRow(signal);
        }

        this.objCRT.DrawScreen();
    }

    public void addNoop() {
        currentCycle++;
        SignalHistory.put(currentCycle, XValue);
        //objCRT.insertRow(this.SignalHistory.get(currentCycle));
    }

    public void addX(int X) {
        for (int i = 1; i < 3; i++) {
            currentCycle++;
            SignalHistory.put(currentCycle, XValue);
            //objCRT.insertRow(this.SignalHistory.get(currentCycle));
        }

        XValue += X;
    }

    public int fetchFrame(int targetFrame) {
        return targetFrame * this.SignalHistory.get(targetFrame);
    }
}

class CRT {
    //Start from left to right
    //String[][] Screen = new String[6][40];

    List<String> Screen = new ArrayList<>();

    public void insertRow(int XValue) {
        String value = ".";

        if (XValue == (this.Screen.size()-1) || XValue+1 == (this.Screen.size()-1) || XValue+2 == (this.Screen.size()-1)) {
            value = "#";
        }

        this.Screen.add(value);

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
        int cycle = 1;
        for (String pixel : this.Screen) {
            //this.objCRT.insertRow(signal);
            System.out.print(pixel);

            if (cycle == 40 || cycle == 80 || cycle == 120 || cycle == 160 || cycle == 200 || cycle == 240) {
                System.out.println();
            }

            cycle++;
        }

        System.out.println(this.Screen);
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

        objCPU.DrawScreen();

        /*System.out.println(objCPU.SignalHistory);
        System.out.println(objCPU.calculateSum());*/


        //objCRT.DrawScreen();
        //objCRT.insertRow(objCPU.SignalHistory.get(1));
    }
} 