package PatronCadenaMando;

import com.google.gson.stream.JsonReader;

import java.io.*;

public class Inhalers extends Chain{
    private static final String INHALERS_TAGNAME = "inhalers";
    private static final String NAME_FIELD_TAGNAME = "name";
    private static final String IMAGE_FIELD_TAGNAME = "image";
    private static final String FIELD_SEPARATOR = ";";

    public Inhalers(Chain suc) {
        super(suc);
        chainName = new String(INHALERS_TAGNAME);
    }

    protected String readObjectEntry(JsonReader reader) throws IOException {
        String inhaName = null;
        String inhaImage = null;
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(NAME_FIELD_TAGNAME)) {
                inhaName = reader.nextString();
            } else if (name.equals(IMAGE_FIELD_TAGNAME)) {
                inhaImage = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        return inhaName + FIELD_SEPARATOR + inhaImage;
    }
}
