package PatronCadenaMando;

import com.google.gson.stream.JsonReader;

import java.io.*;

public class Posologies {
    private static final String POSOLOGIES_TAGNAME = "posologies";

    private static final String DESCRIPTION_FIELD_TAGNAME = "description";

    protected String readObjectEntry(String jsonFileName) throws IOException {
        InputStream usersIS = new FileInputStream(new File(jsonFileName));
        JsonReader reader = new JsonReader(new InputStreamReader(usersIS, "UTF-8"));

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
