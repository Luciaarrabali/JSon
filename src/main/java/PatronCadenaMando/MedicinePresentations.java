package PatronCadenaMando;

import com.google.gson.stream.JsonReader;

import java.io.*;

public class MedicinePresentations {
    private static final String MEDICINEPRESENTATIONS_TAGNAME = "medicinePresentations";

    private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
    private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
    private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
    private static final String DOSE_FIELD_TAGNAME = "dose";
    private static final String POSREF_FIELD_TAGNAME = "posologyRef";
    private static final String FIELD_SEPARATOR = ";";

    protected String readObjectEntry(String jsonFileName) throws IOException {
        InputStream usersIS = new FileInputStream(new File(jsonFileName));
        JsonReader reader = new JsonReader(new InputStreamReader(usersIS, "UTF-8"));

        String medRef = null;
        String aiRef = null;
        String inhRef = null;
        String dose = null;
        String posRef = null;

        reader.beginObject();
        StringBuffer readData = new StringBuffer();

        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(MEDREF_FIELD_TAGNAME)) {
                medRef = reader.nextString();
            } else if (name.equals(ACTINGREF_FIELD_TAGNAME)) {
                aiRef = reader.nextString();
            } else if (name.equals(INHREF_FIELD_TAGNAME)) {
                StringBuffer res = new StringBuffer();
                reader.beginArray();
                while (reader.hasNext()) {
                    res.append(reader.nextString()).append(",");
                }
                reader.endArray();
                res.deleteCharAt(res.length() - 1);
                inhRef = new String(res);
            } else if (name.equals(DOSE_FIELD_TAGNAME)) {
                StringBuffer res = new StringBuffer();
                reader.beginArray();
                while (reader.hasNext()) {
                    res.append(reader.nextString()).append(",");
                }
                reader.endArray();
                res.deleteCharAt(res.length() - 1);
                dose = new String(res);
            } else if (name.equals(POSREF_FIELD_TAGNAME)) {
                StringBuffer res = new StringBuffer();
                reader.beginArray();
                while (reader.hasNext()) {
                    res.append(reader.nextString()).append(",");
                }
                reader.endArray();
                res.deleteCharAt(res.length() - 1);
                posRef = new String(res);
            } else {
                reader.skipValue();
            }
        }
        return medRef + FIELD_SEPARATOR + aiRef + FIELD_SEPARATOR + inhRef
                + FIELD_SEPARATOR + dose + FIELD_SEPARATOR	+ posRef;
    }
}
