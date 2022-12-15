import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class CPU {
    HashMap<Integer, Integer> SignalHistory = new HashMap<>();
    int currentCycle = 0;
    int XValue = 1;

    public CPU() {
        SignalHistory.put(currentCycle, XValue);
    }


    public void addNoop() {
        currentCycle++;
        SignalHistory.put(currentCycle, XValue);
    }

    public void addX(int X) {
        for (int i = 1; i < 3; i++) {
            currentCycle++;
            SignalHistory.put(currentCycle, XValue);
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

class TenOne {
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

        System.out.println(objCPU.SignalHistory);
        System.out.println(objCPU.calculateSum());

        //System.out.println("The answer is: " + listKnots.get(listKnots.size()-1).LocationHistory.size());
    }
} 