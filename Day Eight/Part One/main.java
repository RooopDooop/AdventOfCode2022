import com.sun.source.tree.Tree;

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

            int visibleCount = 0;
            for (int z = 0; z < (trees.size()); z++) {
                for (int i = 0; i < (trees.get(z).size()); i++) {
                    boolean TreeInvisible = false;
                    int CurrentTree = trees.get(z).get(i);

                    boolean TopInvisible = false;
                    boolean BottomInvisible = false;
                    boolean LeftInvisible = false;
                    boolean RightInvisible = false;

                    for (int TopPointer = 0; TopPointer < trees.size(); TopPointer++) {
                        int queryInt = trees.get(TopPointer).get(i);
                        if (TopPointer < z) {
                            if (queryInt >= CurrentTree) {
                                TopInvisible = true;
                            }
                        } else if (TopPointer > z) {
                            if (queryInt >= CurrentTree) {
                                BottomInvisible = true;
                            }
                        }
                    }

                    for (int SidePointer = 0; SidePointer < trees.get(z).size(); SidePointer++) {
                        int queryInt = trees.get(z).get(SidePointer);
                        if (SidePointer < i) {
                            if (queryInt >= CurrentTree) {
                                LeftInvisible = true;
                            }
                        } else if (SidePointer > i) {
                            if (queryInt >= CurrentTree) {
                                RightInvisible = true;
                            }
                        }
                    }

                    if (!(TopInvisible && BottomInvisible && LeftInvisible && RightInvisible)) {
                        visibleCount++;
                    }
                }
            }

            System.out.println("The final answer is: " + (visibleCount));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  
} 