import java.util.Random;

/**
 * Player class to fill teamset
 * @author Sara
 * @version 1
 */
public class Player {
    private String name;
    private String pos;

    /**
     * creates a player with a name and a position
     * @param name player's name
     * @param pos  player's position
     */
    public Player(String name, String pos) {
        this.name = name;
        this.pos = pos;
    }

    /**
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the player's position
     */
    public String getPos() {
        return pos;
    }

    /**
     * @return a randomly chosen positive comment as stated
     * by various announcers.
     */
    public String cheer() {
        String[] arbPhrase = {"Great first touch!",
            "RIGHT OFF THE CROSSBAR",
            "A cheeky chip!",
            "E equals MC squared and M stands for Messi",
            "more like Napoleon Dynamite with that right leg",
            "this is just something you cannot teach",
            "textbook finish",
            "is it one of those days? it is one of those days!!",
            "GOOOOOOOOOOLLLLLLL!!!!!",
            "superb goalkeeping",
            "THE YOUNGSTER HAS DONE IT AGAIN",
            "Today is Uruguay's day because today is Kavani's day",
            "Ronaldo with the hattrick",
            "South Korea is invited to the Carne Asada",
            "An inspirational moment from KROOS"};
        Random ran = new Random();
        return arbPhrase[ran.nextInt(arbPhrase.length)];
    }

    /**
     * @param o object to compare to this instance
     * @return boolean whether the objects have the same name/position
     */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Player)) {
            return false;
        }
        return this.name.equals(((Player) o).getName())
            && this.pos.equals(((Player) o).getPos());
    }

    /**
     * @return int of the length of their name
     */
    public int hashCode() {
        return name.length();
    }
}
