package PatronCadenaMando;

import java.io.*;

import com.google.gson.stream.JsonReader;

public class Medicines extends Chain{
    private static final String MEDICINES_TAGNAME = "medicines";
    private static final String NAME_FIELD_TAGNAME = "name";

    public Medicines(Chain suc) {
        super(suc);
        chainName = new String(MEDICINES_TAGNAME);
    }

    protected String readObjectEntry(JsonReader reader) throws IOException {
        String medName = null;
        while (reader.hasNext()) {
            String name = reader.nextName();
            if(name.equals(NAME_FIELD_TAGNAME)){
                medName = reader.nextString();
            } else{
                reader.skipValue();
            }
        }
        return medName;
    }
}