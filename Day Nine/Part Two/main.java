import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class Rope {
    int LocationY;
    int LocationX;

    Set<List<Integer>> LocationHistory = new HashSet<>();

    public void noteLocation() {
        this.LocationHistory.add(Arrays.asList(this.LocationY, this.LocationX));
    }
}

class Head extends Rope {
    public void MoveHead(String Direction) {
        switch (Direction) {
            case "U" -> LocationY++;
            case "R" -> LocationX++;
            case "D" -> LocationY--;
            case "L" -> LocationX--;
            default -> System.out.println("ERROR: DEFAULT SWITCH GATE HIT - " + Direction);
        }

        this.noteLocation();
    }
}

class Knot extends Rope {
    private final Rope Parent;

    public Knot(Rope Parent) {
        this.Parent = Parent;
    }

    public void FollowParent() {
        int diffY = Math.abs(Parent.LocationY - this.LocationY);
        int diffX = Math.abs(Parent.LocationX - this.LocationX);

        if ((Parent.LocationY == this.LocationY) && (diffX > 1)) {
            if (Parent.LocationX > this.LocationX) {
                this.LocationX++;
            } else {
                this.LocationX--;
            }
        }

        if ((Parent.LocationX == this.LocationX) && (diffY > 1)) {
            if (Parent.LocationY > this.LocationY) {
                this.LocationY++;
            } else {
                this.LocationY--;
            }
        }

        if ((diffX + diffY) > 2) {
            if (Parent.LocationX > this.LocationX) {
                this.LocationX++;
            } else {
                this.LocationX--;
            }

            if (Parent.LocationY > this.LocationY) {
                this.LocationY++;
            } else {
                this.LocationY--;
            }
        }

        this.noteLocation();
    }
}

class NineTwo {
    public static void main(String[] args) throws IOException {
        List<String> listMovements = Files.readAllLines(Path.of("inputData.txt"));

        Head objHead = new Head();
        List<Knot> listKnots = new ArrayList<>();

        listKnots.add(new Knot(objHead));
        listKnots.add(new Knot(listKnots.get(0)));
        listKnots.add(new Knot(listKnots.get(1)));
        listKnots.add(new Knot(listKnots.get(2)));
        listKnots.add(new Knot(listKnots.get(3)));
        listKnots.add(new Knot(listKnots.get(4)));
        listKnots.add(new Knot(listKnots.get(5)));
        listKnots.add(new Knot(listKnots.get(6)));
        listKnots.add(new Knot(listKnots.get(7)));

        for (String Movement: listMovements) {
            String Direction = Movement.split(" " )[0];
            int Distance = Integer.parseInt(Movement.split(" ")[1]);

            while (Distance > 0) {
                objHead.MoveHead(Direction);

                for (Knot objKnot : listKnots) {
                    objKnot.FollowParent();
                }

                Distance--;
            }
        }

        System.out.println("The answer is: " + listKnots.get(listKnots.size()-1).LocationHistory.size());
    }
} 