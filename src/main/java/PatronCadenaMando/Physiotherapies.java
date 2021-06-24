package PatronCadenaMando;

import com.google.gson.stream.JsonReader;

import java.io.*;

public class Physiotherapies extends Chain{
    private static final String PHYSIOTHERAPIES_TAGNAME = "physiotherapies";
    private static final String NAME_FIELD_TAGNAME = "name";
    private static final String IMAGE_FIELD_TAGNAME = "image";
    private static final String FIELD_SEPARATOR = ";";

    public Physiotherapies(Chain suc) {
        super(suc);
        chainName = new String(PHYSIOTHERAPIES_TAGNAME);
    }

    protected String readObjectEntry(JsonReader reader) throws IOException {
        String physioName = null;
        String physioImage = null;
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(NAME_FIELD_TAGNAME)) {
                physioName = reader.nextString();
            } else if (name.equals(IMAGE_FIELD_TAGNAME)) {
                physioImage = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        return physioName + FIELD_SEPARATOR + physioImage;
    }

}
