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
        for (int i = 0; i < 2; i++) {
            currentCycle++;
            SignalHistory.put(currentCycle, XValue);
        }

        XValue += X;
    }
    public int fetchAddX(int target) {
        return this.SignalHistory.get(target);
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

        for (int keyVal : objCPU.SignalHistory.keySet()) {
            boolean writePixel = false;

            if (keyVal > 0 && keyVal < 40) {
                writePixel = (keyVal >= objCPU.fetchAddX(keyVal) && keyVal <= objCPU.fetchAddX(keyVal)+2);
            } else if (keyVal > 39 && keyVal < 80) {
                int diff = keyVal - 40;
                writePixel = (diff >= objCPU.fetchAddX(keyVal) && diff <= objCPU.fetchAddX(keyVal)+2);
            } else if (keyVal > 79 && keyVal < 120) {
                int diff = keyVal - 80;
                writePixel = (diff >= objCPU.fetchAddX(keyVal) && diff <= objCPU.fetchAddX(keyVal)+2);
            } else if (keyVal > 119 && keyVal < 160) {
                int diff = keyVal - 120;
                writePixel = (diff >= objCPU.fetchAddX(keyVal) && diff <= objCPU.fetchAddX(keyVal)+2);
            } else if (keyVal > 159 && keyVal < 200) {
                int diff = keyVal - 160;
                writePixel = (diff >= objCPU.fetchAddX(keyVal) && diff <= objCPU.fetchAddX(keyVal)+2);
            } else {
                int diff = keyVal - 200;
                writePixel = (diff >= objCPU.fetchAddX(keyVal) && diff <= objCPU.fetchAddX(keyVal)+2);
            }

            if (writePixel) {
                System.out.print("#");
            } else {
                System.out.print(" ");
            }

            if (keyVal == 39 || keyVal == 79 || keyVal == 119 || keyVal == 159 || keyVal == 199 ) {
                System.out.println();
            }
        }
    }
}
