package PatronCadenaMando;

import com.google.gson.stream.JsonReader;

import java.io.*;

public class ActiveIngredients {
    private static final String ACTIVEINGREDIENTS_TAGNAME = "activeIngredients";
    private static final String NAME_FIELD_TAGNAME = "name";

    protected String readObjectEntry(String jsonFileName) throws IOException {
        InputStream usersIS = new FileInputStream(new File(jsonFileName));
        JsonReader reader = new JsonReader(new InputStreamReader(usersIS, "UTF-8"));

        String actIng = null;

        reader.beginObject();
        StringBuffer readData = new StringBuffer();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if(name.equals(NAME_FIELD_TAGNAME)){
                actIng = reader.nextString();
            } else{
                reader.skipValue();
            }
        }
        return actIng;
    }
}