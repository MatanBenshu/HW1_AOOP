//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package country;

public class Map {
    private Settlement[] settlements;

    public Map(Settlement[] settlements) {
        this.settlements = settlements;
    }

    public Settlement[] getSettlements() {
        return settlements;
    }

    public void setSettlements(Settlement[] settlements) {
        this.settlements = settlements;
    }
}
