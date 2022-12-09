import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

class SevenOne{  
    static class objDirectory {
        private String name;
        private objDirectory parent = null;
        private int localSize = 0;
        private int inheritedSize = 0;
        private Map<String, objDirectory> children = new HashMap<>();
    
        objDirectory(String name, objDirectory parent) {
            this.name = name;
            this.parent = parent;
            //System.out.println("Const");
        }
    }

    public static void main(String args[]){
        objDirectory MasterDir = new objDirectory("/", null); 
        objDirectory currentLocation = MasterDir;

        List<String> terminalInputs = new ArrayList<>();

        try {
            File inputFile = new File("../inputData.txt");
            Scanner fileReader = new Scanner(inputFile);

            while (fileReader.hasNextLine()) {
                terminalInputs.add(fileReader.nextLine());
            }

            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        for (int z = 1; z < terminalInputs.size(); z++) {
            String terminalInput = terminalInputs.get(z);
            
            if (terminalInput.toCharArray()[0] == '$') {
                String command = terminalInput.split(" ")[1];

                //System.out.println(command);

                if (command.equals("cd")) {
                    //Move forward into child that should already be there
                    String parameter = terminalInput.split(" ")[2];

                    if (parameter.equals("..")) {
                        System.out.println(currentLocation + " - Move: " + parameter + " - " + currentLocation.children);
                        currentLocation = currentLocation.parent;
                        //System.out.println(test);
                    } else {
                        System.out.println(currentLocation + " - Move: " + parameter + " - " + currentLocation.children);
                        currentLocation = currentLocation.children.get(parameter);
                        //System.out.println(test);
                    }

                    

                    /*objDirectory newDir = new objDirectory(parameter, currentLocation);
                    currentLocation.children.add(newDir);
                    currentLocation = newDir;

                    System.out.println(currentLocation);
                    
                    System.out.println(command + " - " + parameter);*/
                } else {
                    //System.out.println(command);
                }
            } else {
                String dirUnknown = terminalInput;

                if (dirUnknown.split(" ")[0].equals("dir")) {
                    objDirectory newDir = new objDirectory(dirUnknown.split(" ")[1], currentLocation);
                    currentLocation.children.put(dirUnknown.split(" ")[1], newDir);

                    //System.out.println(currentLocation.children);
                } else {
                    //System.out.println(dirUnknown);
                }


                //System.out.println("TODO command: " + terminalInput);
            }
        }

        //System.out.println(MasterDir.children.get(0).children.get(0).name);



        //System.out.println(terminalInputs.get(0));
        

        /*objDirectory MasterDir = new objDirectory("Master"); 
        objDirectory currentLocation = MasterDir;

        MasterDir.children.add(0, new objDirectory("Sub"));

        System.out.println(MasterDir);
        System.out.println(currentLocation);*/

        /*System.out.println(MasterDir.name);
        System.out.println(currentLocation);
        System.out.println(MasterDir.children.get(0));

        currentLocation = MasterDir.children.get(0);
        currentLocation.name = "Cheese";

        System.out.println(currentLocation.name);*/
    }  
} 