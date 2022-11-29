import java.util.ArrayList;
import java.util.List;

public class Player {
    Teams team;
    int totalTroops;
    List<Territory> playerTerritories;

    static List<Player> playerList = new ArrayList<>();

    Player(Teams t, int tt){
        team = t;
        totalTroops = tt;
        playerTerritories = new ArrayList<>();
        playerList.add(this);
    }

}
