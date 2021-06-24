package PatronCadenaMando;

import com.google.gson.stream.JsonReader;

import java.io.*;

public class RescueMedPres extends Chain {
    private static final String RESCUEMEDPRES_TAGNAME = "rescueMedicinePresentations";

    private static final String MEDREF_FIELD_TAGNAME = "medicineRef";

    public RescueMedPres(Chain suc) {
        super(suc);
        chainName = new String(RESCUEMEDPRES_TAGNAME);
    }

    protected String readObjectEntry(JsonReader reader) throws IOException {
        String medRef = null;
        while(reader.hasNext()) {
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