package PatronCadenaMando;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GsonDatabaseClient {
    public static void main(String[] args) {
        try {
            Chain umStepsReader = new UserManualSteps(null);
            Chain umPhyStepsReader = new UserManualPhysioSteps(umStepsReader);
            Chain resMedPresReader = new RescueMedPres(umPhyStepsReader);
            Chain medPresReader = new MedicinePresentations(resMedPresReader);
            Chain posoReader = new Posologies(medPresReader);
            Chain inhaReader = new Inhalers(posoReader);
            Chain physioReader = new Physiotherapies(inhaReader);
            Chain actIngReader = new ActiveIngredients(physioReader);
            Chain medReader = new Medicines(actIngReader);

            DatabaseJSonReader dbjp = new DatabaseJSonReader();

            try {
                System.out.println(dbjp.parse(medReader, "./src/main/resources/datos.json"));
            } finally {
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
