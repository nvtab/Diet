package Data;


import java.util.ArrayList;

public enum Periodicity {
    DAILY, WEEKLY, BIWEEKLY, MONTHLY;


    public static ArrayList<String> toStrings(){
       ArrayList<String> output = new ArrayList<>();

        for(Periodicity p : Periodicity.values()){
            switch (p){
                case DAILY: output.add("Diario"); break;
                case WEEKLY: output.add("Semanal"); break;
                case BIWEEKLY: output.add("Quincenal"); break;
                case MONTHLY: output.add("Mensual"); break;
            }
        }
        return output;
    }
}
