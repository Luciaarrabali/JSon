package PatronCadenaMando;

import com.google.gson.stream.JsonReader;

import java.io.*;

public class ActiveIngredients extends Chain{
    private static final String ACTIVEINGREDIENTS_TAGNAME = "activeIngredients";
    private static final String NAME_FIELD_TAGNAME = "name";

    public ActiveIngredients(Chain suc) {
        super(suc);
        chainName = new String(ACTIVEINGREDIENTS_TAGNAME);
    }

    @Override
    protected String readObjectEntry(JsonReader reader) throws IOException {
        String actIngName = null;
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(NAME_FIELD_TAGNAME)) {
                actIngName = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        return actIngName;
    }
}