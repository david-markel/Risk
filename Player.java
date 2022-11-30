import java.util.ArrayList;
import java.util.Arrays;
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

    int calcContinentRewards(){
        int result = 0;
        List<Continent> continentList = Arrays.asList(Continent.NA, Continent.SA,
                Continent.AFRICA, Continent.EUROPE, Continent.ASIA, Continent.AUSTRALIA);

        for (int j = 0; j < continentList.size();j++){
            boolean getReward = true;
            Continent continent = continentList.get(j);
            List<Territory> tList = new ArrayList<>();
            if (continent == Continent.NA){
                tList = Territory.naList;
            } else if (continent == Continent.SA){
                tList = Territory.saList;
            } else if (continent == Continent.EUROPE){
                tList = Territory.europeList;
            } else if (continent == Continent.AFRICA){
                tList = Territory.africaList;
            } else if (continent == Continent.ASIA){
                tList = Territory.asiaList;
            } else if (continent == Continent.AUSTRALIA){
                tList = Territory.australiaList;
            }
            for (int i = 0; i < tList.size(); i++){
                if (!playerTerritories.contains(tList.get(i))){
                    getReward = false;
                }
            }
            if (getReward){
                result += continent.getReward();
            }
        }
        return result;
    }
}
