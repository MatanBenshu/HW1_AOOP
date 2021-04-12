package country;

public class map {
    private Settlement[] settlements;

    public map(Settlement[] settlements) {
        this.settlements = settlements;
    }

    public Settlement[] getSettlements() {
        return settlements;
    }

    public void setSettlements(Settlement[] settlements) {
        this.settlements = settlements;
    }
}
