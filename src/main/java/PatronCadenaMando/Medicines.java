package PatronCadenaMando;

import java.io.*;

import com.google.gson.stream.JsonReader;

public class Medicines {
    private static final String MEDICINES_TAGNAME = "medicines";
    private static final String NAME_FIELD_TAGNAME = "name";

    protected String readObjectEntry(String jsonFileName) throws IOException {
        InputStream usersIS = new FileInputStream(new File(jsonFileName));
        JsonReader reader = new JsonReader(new InputStreamReader(usersIS, "UTF-8"));

        String medName = null;

        reader.beginObject();
        StringBuffer readData = new StringBuffer();
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
