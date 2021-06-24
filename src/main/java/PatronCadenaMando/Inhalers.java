package PatronCadenaMando;

import com.google.gson.stream.JsonReader;

import java.io.*;

public class Inhalers {
    private static final String INHALERS_TAGNAME = "inhalers";
    private static final String NAME_FIELD_TAGNAME = "name";
    private static final String IMAGE_FIELD_TAGNAME = "image";
    private static final String FIELD_SEPARATOR = ";";

    protected String readObjectEntry(String jsonFileName) throws IOException {
        InputStream usersIS = new FileInputStream(new File(jsonFileName));
        JsonReader reader = new JsonReader(new InputStreamReader(usersIS, "UTF-8"));

        String inhaName = null;
        String inhaImage = null;

        reader.beginObject();
        StringBuffer readData = new StringBuffer();

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
