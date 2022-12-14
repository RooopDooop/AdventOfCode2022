import com.sun.source.tree.Tree;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

class NineOne {
    public static void main(String[] args){
        //Y, X
        List<Integer> headLocation = Arrays.asList(0, 0);
        List<Integer> tailLocation = Arrays.asList(0, 0);

        Set<List<Integer>> VisitedLocations = new HashSet<>();
        VisitedLocations.add(Arrays.asList(0,0));

        try {
            List<String> listMovements = Files.readAllLines(Path.of("inputData.txt"));

           for (String Movement: listMovements) {
               String Direction = Movement.split(" " )[0];
               int Distance = Integer.parseInt(Movement.split(" ")[1]);

               while (Distance > 0) {
                   switch (Direction) {
                       case "U":
                           headLocation.set(0, (headLocation.get(0) + 1));
                           break;
                       case "R":
                           headLocation.set(1, (headLocation.get(1) + 1));
                           break;
                       case "D":
                           headLocation.set(0, (headLocation.get(0) - 1));
                           break;
                       case "L":
                           headLocation.set(1, (headLocation.get(1) - 1));
                           break;
                       default:
                           System.out.println("ERROR: DEFAULT SWITCH GATE HIT - " + Direction + " - " + Distance);
                   }

                   tailLocation = tailMove(headLocation.get(0), headLocation.get(1), tailLocation.get(0), tailLocation.get(1));
                   VisitedLocations.add(Arrays.asList(tailLocation.get(0), tailLocation.get(1)));
                   Distance--;
               }
           }

           System.out.println("The answer is: " + VisitedLocations.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> tailMove(int headLocationY, int headLocationX, int tailLocationY, int tailLocationX) {
        //Return string list with commands for tail
        int diffY = Math.abs(headLocationY - tailLocationY);
        int diffX = Math.abs(headLocationX - tailLocationX);

        if ((headLocationY == tailLocationY) && (diffX > 1)) {
            if (headLocationX > tailLocationX) {
                tailLocationX++;
            } else {
                tailLocationX--;
            }
        }

        if ((headLocationX == tailLocationX) && (diffY > 1)) {
            if (headLocationY > tailLocationY) {
                tailLocationY++;
            } else {
                tailLocationY--;
            }
        }

        if ((diffX + diffY) > 2) {
            if (headLocationX > tailLocationX) {
                tailLocationX++;
            } else {
                tailLocationX--;
            }

            if (headLocationY > tailLocationY) {
                tailLocationY++;
            } else {
                tailLocationY--;
            }
        }

        return Arrays.asList(tailLocationY, tailLocationX);
    }
} 