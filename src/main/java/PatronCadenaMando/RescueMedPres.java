package PatronCadenaMando;

import com.google.gson.stream.JsonReader;

import java.io.*;

public class RescueMedPres {
    private static final String RESCUEMEDPRES_TAGNAME = "rescueMedicinePresentations";
    private static final String MEDREF_FIELD_TAGNAME = "medicineRef";

    protected String readObjectEntry(String jsonFileName) throws IOException {
        InputStream usersIS = new FileInputStream(new File(jsonFileName));
        JsonReader reader = new JsonReader(new InputStreamReader(usersIS, "UTF-8"));

        String medRef = null;

        reader.beginObject();
        StringBuffer readData = new StringBuffer();
        while (reader.hasNext()) {
            String medicineRef = reader.nextName();
            if(medicineRef.equals(MEDREF_FIELD_TAGNAME)){
                medRef = reader.nextString();
            } else{
                reader.skipValue();
            }
        }
        return medRef;
    }
}