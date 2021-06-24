package PatronCadenaMando;

import com.google.gson.stream.JsonReader;

import java.io.*;

public class UserManualSteps extends Chain{
    private static final String USERMANUALSTEPS_TAGNAME = "userManualSteps";

    private static final String STEPTITLE_FIELD_TAGNAME = "stepTitle";
    private static final String STEPIMAGE_FIELD_TAGNAME = "stepImage";
    private static final String STEPTEXT_FIELD_TAGNAME = "stepText";
    private static final String INHALERREF_FIELD_TAGNAME = "inhalerRef";
    private static final String FIELD_SEPARATOR = ";";

    public UserManualSteps(Chain suc) {
        super(suc);
        chainName = new String(USERMANUALSTEPS_TAGNAME);
    }

    protected String readObjectEntry(JsonReader reader) throws IOException {
        String umStepTitle = null;
        String umStepImage = null;
        String umStepText = null;
        String umInhalerRef = null;
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
