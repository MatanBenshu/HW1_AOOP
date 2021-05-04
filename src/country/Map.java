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

    public String[] Get_settlementsNameArry(){
        String[] names = new String[settlements.length];
        for (int i = 0; i < settlements.length; i++) {
            names[i]=settlements[i].getName();
        }
        return names;

    }
}
