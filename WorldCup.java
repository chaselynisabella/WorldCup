import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * WorldCup is a tester for TeamSet
 * Feel free to add your own checks!
 * @author Sara
 * @version 2
 */
public class WorldCup {

    public static void main(String[] args) throws Exception {
        TeamSet<Player> argentina = new TeamSet<>();

        // Check add
        argentina.add(new Player("Messi", "F"));
        argentina.add(new Player("Biglia", "M"));
        argentina.add(new Player("Paredes", "M"));
        argentina.add(new Player("Mercado", "D"));
        argentina.add(new Player("Fazio", "D"));
        argentina.add(new Player("Romero", "GK"));
        Player[] checkArg = {new Player("Messi", "F"),
            new Player("Biglia", "M"),
            new Player("Paredes", "M"),
            new Player("Mercado", "D"),
            new Player("Fazio", "D"),
            new Player("Romero", "GK")};
        Object[] obj = argentina.getBackingArray();
        System.out.println(java.util.Arrays.toString(obj));
        for (int i = 0; i < checkArg.length; i++) {
            Player p = (Player) obj[i];
            if (!(p.equals(checkArg[i]))) {
                throw new Exception("Add not working properly, expected: "
                    + checkArg[i] + " received: "
                    + argentina.getBackingArray()[i]);
            }
        }
        Player kroos = new Player("Kroos", "M");
        System.out.println("You passed add! " + kroos.cheer());

        // Check add's resizing
        try {
            argentina.add(new Player("Mascherano", "M"));
            argentina.add(new Player("Di Maria", "F"));
            argentina.add(new Player("Aguero", "F"));
            argentina.add(new Player("Tagliafico", "D"));
            // should be full and resize to 20
            argentina.add(new Player("Otamendi", "D"));
            obj = argentina.getBackingArray();
            if (obj.length != 20) {
                throw new Exception("Does not resize correctly, "
                    + "excepted length: 20, received: "
                    + obj.length);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Array does not resize when full.");
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("You passed add's resize! " + kroos.cheer());

        // check clear
        argentina.clear();
        obj = argentina.getBackingArray();
        if (obj.length != 10) {
            throw new Exception("clear doesn't reset length to 10"
                + "excepted: 10, received "
                + argentina.getBackingArray().length);
        }
        if (argentina.size() != 0) {
            throw new Exception("clear doesn't reset size to 0"
                + "excepted: 0, received "
                + argentina.size());
        }
        System.out.println("You passed clear! " + kroos.cheer());

        // check addAll
        TeamSet<Player> brazil = new TeamSet<>();
        ArrayList<Player> players = new ArrayList<>();
        Collections.addAll(players, new Player("Neymar", "F"),
            new Player("Silva", "D"),
            new Player("Marcelo", "D"),
            new Player("Paulinho", "M"),
            new Player("Coutinho", "M"));
        brazil.addAll(players);
        if (brazil.size() != 5) {
            throw new Exception("problem with addAll, expected size: 5"
                + " received: " + brazil.size());
        }
        obj = brazil.getBackingArray();
        for (int i = 0; i < players.size(); i++) {
            if (!obj[i].equals(players.get(i))) {
                throw new Exception("problem with addAll, excpeted: "
                    + players.get(i) + " received: "
                    + brazil.getBackingArray()[i]);
            }
        }
        System.out.println("You passed addAll! " + kroos.cheer());

        // check contains
        if (!brazil.contains(new Player("Silva", "D"))) {
            throw new Exception("Problem with contains, expected: true,"
                + " received: " + brazil.contains(new Player("Silva", "D")));
        }
        System.out.println("You passed contains! " + kroos.cheer());

        // check containsAll
        if (!brazil.containsAll(players)) {
            throw new Exception("Problem with containsAll, expected: "
                + "true, received: " + brazil.containsAll(players));
        }
        System.out.println("You passed containsAll! " + kroos.cheer());

        // check remove
        if (brazil.remove(new Player("Firmino", "M"))) {
            throw new Exception("Problem with remove, expected: "
                + " false, received: "
                + brazil.remove(new Player("Firmino", "M")));
        }
        if (!brazil.remove(new Player("Neymar", "F"))) {
            throw new Exception("Problem with remove, expected: "
                + " true, received: "
                + brazil.remove(new Player("Neymar", "F")));
        }
        obj = brazil.getBackingArray();
        if (!obj[0].equals(new Player("Silva", "D"))) {
            throw new Exception("Remove doesn't shift elements up"
                + "element 0 expected: Silva, received: "
                + ((Player)obj[0]).getName());
        }
        System.out.println("You passed remove! " + kroos.cheer());

        // check equals
        TeamSet<Player> switz = new TeamSet<>();
        ArrayList<Player> sPlayers = new ArrayList<>();
        Collections.addAll(sPlayers, new Player("Lichtsteiner", "D"),
            new Player("Xhaka", "M"),
            new Player("Shaqiri", "M"),
            new Player("Embolo", "F"),
            new Player("Behrami", "M"));
        switz.addAll(sPlayers);
        if (brazil.equals(switz)) {
            throw new Exception("equals method expected: false"
                + "received: " + brazil.equals(switz));
        }
        TeamSet<Player> switz2 = new TeamSet<>();
        ArrayList<Player> sPlayers2 = new ArrayList<>();
        Collections.addAll(sPlayers2, new Player("Lichtsteiner", "D"),
            new Player("Xhaka", "M"),
            new Player("Shaqiri", "M"),
            new Player("Embolo", "F"),
            new Player("Behrami", "M"));
        switz2.addAll(sPlayers2);
        if (!switz.equals(switz2)) {
            throw new Exception("equals method taking order into"
                + " account, expected: true, received: "
                + switz.equals(switz2));
        }
        System.out.println("You passed equals! " + kroos.cheer());

        // test Iterator
        TeamSet<Player> sweden = new TeamSet<>();
        ArrayList<Player> swedPlayers = new ArrayList<>();
        Collections.addAll(swedPlayers, new Player("Olsen", "GK"),
            new Player("Lustig", "D"),
            new Player("Forsberg", "F"),
            new Player("Svensson", "M"),
            new Player("Toivonen", "F"));
        sweden.addAll(swedPlayers);
        Iterator<Player> t = sweden.iterator();
        while (t.hasNext()) {
            t.next();
        }
        try {
            t.next();
            System.out.println("Expected NoSuchElementException "
                + " iterating when hasNext is false, got nothing.");
        } catch (NoSuchElementException e) {
            System.out.println("Correctly threw NoSuchElementException");
        } catch (Exception e) {
            System.out.println("Expected NoSuchElementException, got "
                + e.getClass().getSimpleName() + ".");
        }
        if (sweden.size() > 0) {
            obj = sweden.getBackingArray();
            t = sweden.iterator();
            Player p = null;
            try {
                for (int i = 0; i < sweden.size(); i++) {
                    if ((p = t.next()) != obj[i]) {
                        System.out.println("Expected ref equal "
                            + p + " and " + obj[i]
                            + " but they weren't.");
                        System.out.println("Iteration should be in "
                            + " index-order.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Got " + e.getClass().getSimpleName() + " iterating with size "
                    + sweden.size() + ".");
            }
        } else {
            System.out.println("Note: give set non-zero size (add an"
                + "element) to do more tests.");
        }
        System.out.println("Correctly passed iterator! " + kroos.cheer());
    }
}
