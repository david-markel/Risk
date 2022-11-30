public enum Continent {
    NA(5),
    SA(2),
    EUROPE(5),
    AFRICA(3),
    ASIA(7),
    AUSTRALIA(2);
    private int reward;
    Continent(int reward) {
        this.reward = reward;
    }
    public int getReward() {
        return reward;
    }

}
