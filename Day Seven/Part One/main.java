import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner; 

class SevenOne{  
    static class objDirectory {
        private String name;
        private objDirectory parent = null;
        private int dataSize = 0;
        private Map<String, objDirectory> children = new HashMap<>();
    
        objDirectory(String name, objDirectory parent) {
            this.name = name;
            this.parent = parent;
        }

        public ArrayList<Integer> findSize() {
            ArrayList<Integer> tempSize = new ArrayList<>();

            for (var objChild: this.children.entrySet()) {
                tempSize.addAll(objChild.getValue().findSize());
                this.dataSize += objChild.getValue().dataSize;
            }

            tempSize.add(this.dataSize);
            return tempSize;
        }
    }

    public static void main(String[] args){
        objDirectory MasterDir = new objDirectory("/", null); 
        objDirectory currentLocation = MasterDir;

        try {
            //Change this, this should be closable try-catch
            List<String> terminalInputs = Files.readAllLines(Path.of("../inputData.txt"));
        
            for (int z = 1; z < terminalInputs.size(); z++) {
                String terminalInput = terminalInputs.get(z);
                
                if (terminalInput.toCharArray()[0] == '$') {
                    String command = terminalInput.split(" ")[1];

                    if (command.equals("cd")) {
                        //Move forward into child that should already be there
                        String parameter = terminalInput.split(" ")[2];

                        if (parameter.equals("..")) {
                            currentLocation = currentLocation.parent;
                        } else {
                            currentLocation = currentLocation.children.get(parameter);
                        }
                    }
                } else {
                    String dirUnknown = terminalInput;

                    if (dirUnknown.split(" ")[0].equals("dir")) {
                        objDirectory newDir = new objDirectory(dirUnknown.split(" ")[1], currentLocation);
                        currentLocation.children.put(dirUnknown.split(" ")[1], newDir);
                    } else {
                        currentLocation.dataSize += Integer.parseInt(dirUnknown.split(" ")[0]);
                    }
                }
            }

            int finalAnswer = 0;
            for (int instanceSize: MasterDir.findSize()) {
                if (instanceSize <= 100000) {
                    finalAnswer += instanceSize;
                }
            }

            System.out.println("The final answer is: " + finalAnswer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  
} 