import java.util.ArrayList;
import java.util.List;

public class Player {
    Teams team;
    int totalTroops;
    List<Territory> playerTerritories;

    static List<Player> playerList = new ArrayList<>();

    Player(Teams t){
        team = t;
        totalTroops = 0;
        playerTerritories = new ArrayList<>();
        playerList.add(this);
    }

}
