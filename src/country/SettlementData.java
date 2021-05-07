package country;

public class SettlementData {


        private Settlement[] settlements;

        public SettlementData(Map map_data) {
            this.settlements = map_data.getSettlements();
        }

        public int size() {
            return settlements.length;
        }

        public Settlement at(int index) {
            return settlements[index];
        }

}
