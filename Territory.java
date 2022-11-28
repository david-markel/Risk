import java.util.ArrayList;
import java.util.List;

public class Territory {
    String name;
    Teams controlledBy;
    Continent continent;
    int troops;
    int[] adjacentTerritories;
    static List<Territory> naList = new ArrayList();
    static List<Territory> saList = new ArrayList();
    static List<Territory> europeList = new ArrayList();
    static List<Territory> africaList = new ArrayList();
    static List<Territory> asiaList = new ArrayList();
    static List<Territory> australiaList = new ArrayList();


    ClickableText area;

    Territory(String n, Continent c, int i, int[] at, int x, int y){
        name = n;
        continent = c;
        controlledBy = Teams.UNSET;
        troops = i;
        name = n;
        adjacentTerritories = new int[at.length];
        for (int j = 0; j < at.length; j++){
            adjacentTerritories[j] = at[j];
        }
        area = new ClickableText(0, name, x, y);
        if (continent == Continent.NA){
            naList.add(this);
        }
        if (continent == Continent.SA){
            saList.add(this);
        }
        if (continent == Continent.EUROPE){
            europeList.add(this);
        }
        if (continent == Continent.ASIA){
            asiaList.add(this);
        }
        if (continent == Continent.AFRICA){
            africaList.add(this);
        }
        if (continent == Continent.AUSTRALIA){
            australiaList.add(this);
        }


    }

}
