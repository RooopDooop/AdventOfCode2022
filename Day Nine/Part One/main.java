import com.sun.source.tree.Tree;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class EightOne {
    public static void main(String[] args){
        //Y, X
        List<Integer> headLocation = Arrays.asList(0, 0);
        List<Integer> tailLocation = Arrays.asList(0, 0);

        Set<List<Integer>> VisitedLocations = new HashSet<>(new ArrayList<>());

        try {
            List<String> listMovements = new ArrayList<>(Files.readAllLines(Path.of("inputData.txt")));

           for (String Movement: listMovements) {
               String Direction = Movement.split(" " )[0];
               int Distance = Integer.parseInt(Movement.split(" ")[1]);

               while (Distance > 0) {
                   List<Integer> previousLocation = Arrays.asList(headLocation.get(0), headLocation.get(1));

                   switch (Direction) {
                       case "U":
                           headLocation.set(0, (headLocation.get(0) + 1));
                           tailLocation = tailMove(previousLocation.get(0), previousLocation.get(1), tailLocation.get(0), tailLocation.get(1));
                           break;
                       case "R":
                           headLocation.set(1, (headLocation.get(1) + 1));
                           tailLocation = tailMove(previousLocation.get(0), previousLocation.get(1), tailLocation.get(0), tailLocation.get(1));
                           break;
                       case "D":
                           headLocation.set(0, (headLocation.get(0) - 1));
                           tailLocation = tailMove(previousLocation.get(0), previousLocation.get(1), tailLocation.get(0), tailLocation.get(1));
                           break;
                       case "L":
                           headLocation.set(1, (headLocation.get(1) - 1));
                           tailLocation = tailMove(previousLocation.get(0), previousLocation.get(1), tailLocation.get(0), tailLocation.get(1));
                           break;
                       default:
                           System.out.println("UNKNOWN - " + Direction + " - " + Distance);
                   }

                   VisitedLocations.add(tailLocation);
                   Distance--;

                   System.out.println("Command: " + Movement + " Head Location: [" + headLocation.get(0) + "][" + headLocation.get(1) + "] - Tail Location: [" + tailLocation.get(0) + "][" + tailLocation.get(1) + "]");
               }


               //System.out.println("==============================================================================================");
           }

           System.out.println(VisitedLocations.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> tailMove(int headLocationY, int headLocationX, int tailLocationY, int tailLocationX) {
        //Return string list with commands for tail
        List<Integer> tailPosition = Arrays.asList(tailLocationY, tailLocationX);

        int diffY = Math.abs(Math.abs(headLocationY) - Math.abs(tailLocationY));
        int diffX = Math.abs(Math.abs(headLocationX) - Math.abs(tailLocationX));

        if ((diffY == 0 || diffY == 1) && (diffX == 0 || diffX == 1)) {
            //Nothing is required, return current position
            System.out.println("Y: " + diffY + " - X: " + diffX);
            return tailPosition;
        } else {
            System.out.println("Y: " + diffY + " - X: " + diffX);
            return Arrays.asList(headLocationY, headLocationX);
        }

        /*List<Integer> TopAvailableDiff = Arrays.asList(((headLocationY+1) - tailLocationY), (headLocationX - tailLocationX));
        List<Integer> BottomAvailableDiff = Arrays.asList(((headLocationY-1) - tailLocationY), (headLocationX - tailLocationX));
        List<Integer> LeftAvailableDiff = Arrays.asList((headLocationY - tailLocationY), ((headLocationX-1) - tailLocationX));
        List<Integer> RightAvailableDiff = Arrays.asList((headLocationY - tailLocationY), ((headLocationX+1) - tailLocationX));

        System.out.println("Top: " + TopAvailableDiff + " - Bottom: " + BottomAvailableDiff + " - Left: " + LeftAvailableDiff + " - Right: " + RightAvailableDiff);

        System.out.println(Math.abs(TopAvailableDiff.get(0)) + Math.abs(TopAvailableDiff.get(1)));
        System.out.println(Math.abs(BottomAvailableDiff.get(0)) + Math.abs(BottomAvailableDiff.get(1)));
        System.out.println(Math.abs(LeftAvailableDiff.get(0)) + Math.abs(LeftAvailableDiff.get(1)));
        System.out.println(Math.abs(RightAvailableDiff.get(0)) + Math.abs(RightAvailableDiff.get(1)));*/


        //System.out.println("==============================================================================================");
        //System.out.println(TopAvailableDiff + " - " + BottomAvailableDiff + " - " + LeftAvailableDiff + " - " + RightAvailableDiff);
        //System.out.println("Calculated Diff: " + (TopAvailableDiff.get(0) - tailPosition.get(0)) + " - " + (TopAvailableDiff.get(1) - tailPosition.get(1)));

        /*if (headLocationY > tailLocationY) {
            while (diffY > 0) {
                //System.out.println("Tail moving up - Head Location: [" + headLocationY + "][" + headLocationX + "] - Tail Location: [" + tailLocationY + "][" + tailLocationX + "]");
                //tailLocationY++;

                tailPosition.set(0, tailPosition.get(0) + 1);
                diffY--;
            }
        } else {
            while (diffY > 0) {
                //System.out.println("Tail moving down - Head Location: [" + headLocationY + "][" + headLocationX + "] - Tail Location: [" + tailLocationY + "][" + tailLocationX + "]");
                //tailLocationY--;
                tailPosition.set(0, tailPosition.get(0) - 1);
                diffY--;
            }
        }

        if (headLocationX > tailLocationX) {
            while (diffX > 0) {
                //System.out.println("Tail moving up - Head Location: [" + headLocationY + "][" + headLocationX + "] - Tail Location: [" + tailLocationY + "][" + tailLocationX + "]");
                //tailLocationY++;

                tailPosition.set(1, tailPosition.get(1) + 1);
                diffX--;
            }
        } else {
            while (diffX > 0) {
                //System.out.println("Tail moving down - Head Location: [" + headLocationY + "][" + headLocationX + "] - Tail Location: [" + tailLocationY + "][" + tailLocationX + "]");
                //tailLocationY--;

                tailPosition.set(1, tailPosition.get(1) - 1);
                diffX--;
            }
        }*/



        //If head had both of these true, it would be top right of the cartesian map.
        //This is how you get the direction of the diff

        /*
        //Head is higher than Tail (cartesian map)
        if (headLocationY > tailLocationY) {
            while (diffY > 0) {
                System.out.println("Tail moving up - Head Location: [" + headLocationY + "][" + headLocationX + "] - Tail Location: [" + tailLocationY + "][" + tailLocationX + "]");
                tailLocationY++;
                diffY--;
            }
        } else {
            while (diffY > 0) {
                System.out.println("Tail moving down - Head Location: [" + headLocationY + "][" + headLocationX + "] - Tail Location: [" + tailLocationY + "][" + tailLocationX + "]");
                tailLocationY--;
                diffY--;
            }
        }

        //Head is on the right of tail
        if (headLocationX > tailLocationX) {
            while (diffX > 0) {
                System.out.println("Tail moving Left - Head Location: [" + headLocationY + "][" + headLocationX + "] - Tail Location: [" + tailLocationY + "][" + tailLocationX + "]");
                tailLocationX++;
                diffX--;
            }
        } else {
            while (diffX > 0) {
                System.out.println("Tail moving Right - Head Location: [" + headLocationY + "][" + headLocationX + "] - Tail Location: [" + tailLocationY + "][" + tailLocationX + "]");
                tailLocationX--;
                diffX--;
            }
        }*/

        //return tailPosition;
    }

    private static int tailMove() {
        return 0;
    }
} 