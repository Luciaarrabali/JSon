package PatronCadenaMando;

import com.google.gson.stream.JsonReader;

import java.io.*;

public class UserManualSteps {
    private static final String USERMANUALSTEPS_TAGNAME = "userManualSteps";

    private static final String STEPTITLE_FIELD_TAGNAME = "stepTitle";
    private static final String STEPIMAGE_FIELD_TAGNAME = "stepImage";
    private static final String STEPTEXT_FIELD_TAGNAME = "stepText";
    private static final String INHALERREF_FIELD_TAGNAME = "inhalerRef";
    private static final String FIELD_SEPARATOR = ";";

    protected String readObjectEntry(String jsonFileName) throws IOException {
        InputStream usersIS = new FileInputStream(new File(jsonFileName));
        JsonReader reader = new JsonReader(new InputStreamReader(usersIS, "UTF-8"));

        String umStepTitle = null;
        String umStepImage = null;
        String umStepText = null;
        String umInhalerRef = null;

        reader.beginObject();
        StringBuffer readData = new StringBuffer();

        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(STEPTITLE_FIELD_TAGNAME)) {
                umStepTitle = reader.nextString();
            } else if (name.equals(STEPIMAGE_FIELD_TAGNAME)) {
                umStepImage = reader.nextString();
            } else if (name.equals(STEPTEXT_FIELD_TAGNAME)) {
                umStepText = reader.nextString();
            } else if (name.equals(INHALERREF_FIELD_TAGNAME)) {
                umInhalerRef = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        return umStepTitle + FIELD_SEPARATOR + umStepImage + FIELD_SEPARATOR
                + umStepText + FIELD_SEPARATOR + umInhalerRef;
    }
}
