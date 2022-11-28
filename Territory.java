public class Territory {
    String name;
    Teams controlledBy;
    Continent continent;
    int troops;
    int[] adjacentTerritories;

    ClickableText area;

    Territory(String n, Teams cb, Continent c, int i, int[] at, ClickableText a){
        name = n;
        continent = c;
        controlledBy = cb;
        troops = i;
        name = n;
        adjacentTerritories = new int[at.length];
        for (int j = 0; j < at.length; j++){
            adjacentTerritories[j] = at[j];
        }
        area = a;
    }

}
