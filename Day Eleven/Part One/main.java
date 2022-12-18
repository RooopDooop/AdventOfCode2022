import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

class Monkey {
    List<Integer> Items = new ArrayList<>();
    String Operation = "";
    int Divisible = 0;
    int ifTrue = 0;
    int ifFalse = 0;
    int InspectionCount = 0;

    public void InspectItems(List<Monkey> fellowMonkeys) {
        for (int z = 0; z < Items.size(); z++) {
            InspectionCount++;
            this.calculateNewWorry(z);
            this.throwItem(fellowMonkeys.get(this.testItem(z)), z);
        }

        Items.clear();
    }

    public void calculateNewWorry(int ItemIndex) {
        int oldWorry = Items.get(ItemIndex);
        int operationValue;

        if (this.Operation.split(" ")[2].equals("old")) {
            operationValue = Items.get(ItemIndex);
        } else {
            operationValue = Integer.parseInt(this.Operation.split(" ")[2]);
        }

        int newWorry;
        String calculationType = this.Operation.split(" ")[1];
        try {
            if (calculationType.equals("+")) {
                newWorry = Math.addExact(oldWorry, operationValue);
            } else {
                newWorry = Math.multiplyExact(oldWorry, operationValue);
            }
        } catch (ArithmeticException ex) {
            newWorry = 2147483647;
        }

        Items.set(ItemIndex, (newWorry/3));
    }

    public int testItem(int ItemIndex) {
        if (Items.get(ItemIndex) % Divisible == 0) {
            return ifTrue;
        }

        return ifFalse;
    }

    public void throwItem(Monkey Target, int ItemIndex) {
        Target.Items.add(Items.get(ItemIndex));
    }
}

class ElevenOne {
    public static void main(String[] args) throws IOException {
        List<String> listCommands = Files.readAllLines(Path.of("inputData.txt"));
        List<Monkey> arrMonkeys = new ArrayList<>();

        for (String Command : listCommands) {
            String CommandCategory = Command.split(":")[0];

            switch(CommandCategory.trim()) {
                case "Starting items":
                    arrMonkeys.get(arrMonkeys.size()-1).Items = Arrays.stream(Command.split(": ")[1].split(", ")).map(Integer::parseInt).collect(Collectors.toList());
                    break;
                case "Operation":
                    arrMonkeys.get(arrMonkeys.size()-1).Operation = Command.split(":")[1].split(" = ")[1];
                    break;
                case "Test":
                    arrMonkeys.get(arrMonkeys.size()-1).Divisible = Integer.parseInt(Command.split(":")[1].split(" ")[3]);
                    break;
                case "If true":
                    arrMonkeys.get(arrMonkeys.size()-1).ifTrue = Integer.parseInt(Command.split(":")[1].split(" ")[4]);
                    break;
                case "If false":
                    arrMonkeys.get(arrMonkeys.size()-1).ifFalse = Integer.parseInt(Command.split(":")[1].split(" ")[4]);
                    break;
                default:
                    if (!CommandCategory.isEmpty()) {
                        arrMonkeys.add(new Monkey());
                    }
                    break;
            };
        }

        for (int w = 0; w < 20; w++) {
            for (Monkey objMonkey : arrMonkeys) {
                objMonkey.InspectItems(arrMonkeys);
            }
        }

        List<Integer> totalMonkeyBusiness = new ArrayList<>();
        for (Monkey objMonkey : arrMonkeys) {
            totalMonkeyBusiness.add(objMonkey.InspectionCount);
        }

        totalMonkeyBusiness.sort(Collections.reverseOrder());
        System.out.println("The total amount of simian shenanigans going on is: " + (totalMonkeyBusiness.get(0) * totalMonkeyBusiness.get(1)));
    }
}