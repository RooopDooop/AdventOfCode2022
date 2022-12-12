import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class EightOne {
    public static void main(String[] args){
        try {
            List<List<Integer>> trees = new ArrayList<>();
            for (String textInput : Files.readAllLines(Path.of("inputData.txt"))) {
                trees.add(Arrays.stream(textInput.split("")).map(Integer::parseInt).toList());
            }

            int highestAnswer = 0;
            for (int z = 0; z < trees.size(); z++) {
                for (int i = 0; i < trees.get(z).size(); i++) {
                    int CurrentTree = trees.get(z).get(i);

                    int TopValue = 0;
                    int BottomValue = 0;
                    int LeftValue = 0;
                    int RightValue = 0;

                    for (int TopPointer = z-1; TopPointer > -1; TopPointer--) {
                        int queryInt = trees.get(TopPointer).get(i);
                        TopValue++;
                        if (queryInt >= CurrentTree) {
                            break;
                        }
                    }

                    for (int BottomPointer = z+1; BottomPointer < trees.size(); BottomPointer++) {
                        int queryInt = trees.get(BottomPointer).get(i);
                        BottomValue++;
                        if (queryInt >= CurrentTree) {
                            break;
                        }
                    }

                    for (int LeftPointer = i-1; LeftPointer > -1; LeftPointer--) {
                        int queryInt = trees.get(z).get(LeftPointer);
                        LeftValue++;
                        if (queryInt >= CurrentTree) {
                            break;
                        }
                    }

                    for (int RightPointer = i+1; RightPointer < trees.get(z).size(); RightPointer++) {
                        int queryInt = trees.get(z).get(RightPointer);
                        RightValue++;
                        if (queryInt >= CurrentTree) {
                            break;
                        }
                    }

                    if (highestAnswer < (TopValue  * RightValue * LeftValue * BottomValue)) {
                        highestAnswer = TopValue  * RightValue * LeftValue * BottomValue;
                    }
                }
            }

            System.out.println("The final answer is: " + (highestAnswer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}