package PatronCadenaMando;

import com.google.gson.stream.JsonReader;

import java.io.*;

public class UserManualPhysioSteps {
    private static final String USERMANUALPHYSIOSTEPS_TAGNAME = "userManualPhysioSteps";

    private static final String STEPTITLE_FIELD_TAGNAME = "stepTitle";
    private static final String STEPIMAGE_FIELD_TAGNAME = "stepImage";
    private static final String STEPTEXT_FIELD_TAGNAME = "stepText";
    private static final String PHYSIOREF_FIELD_TAGNAME = "physioRef";
    private static final String FIELD_SEPARATOR = ";";

    protected String readObjectEntry(String jsonFileName) throws IOException {
        InputStream usersIS = new FileInputStream(new File(jsonFileName));
        JsonReader reader = new JsonReader(new InputStreamReader(usersIS, "UTF-8"));

        String umPhyStepTitle = null;
        String umPhyStepImage = null;
        String umPhyStepText = null;
        String umPhysioRef = null;

        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(STEPTITLE_FIELD_TAGNAME)) {
                umPhyStepTitle = reader.nextString();
            } else if (name.equals(STEPIMAGE_FIELD_TAGNAME)) {
                umPhyStepImage = reader.nextString();
            } else if (name.equals(STEPTEXT_FIELD_TAGNAME)) {
                umPhyStepText = reader.nextString();
            } else if (name.equals(PHYSIOREF_FIELD_TAGNAME)) {
                umPhysioRef = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        return umPhyStepTitle + FIELD_SEPARATOR + umPhyStepImage + FIELD_SEPARATOR
                + umPhyStepText + FIELD_SEPARATOR + umPhysioRef;
    }
}
