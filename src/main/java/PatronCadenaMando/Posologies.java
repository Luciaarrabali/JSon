package PatronCadenaMando;

import com.google.gson.stream.JsonReader;

import java.io.*;

public class Posologies extends Chain{
    private static final String POSOLOGIES_TAGNAME = "posologies";
    private static final String DESCRIPTION_FIELD_TAGNAME = "description";

    public Posologies(Chain suc) {
        super(suc);
        chainName = new String(POSOLOGIES_TAGNAME);
    }

    protected String readObjectEntry(JsonReader reader) throws IOException {
        String posoDescription = null;
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(DESCRIPTION_FIELD_TAGNAME)) {
                posoDescription = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        return posoDescription;
    }
}
