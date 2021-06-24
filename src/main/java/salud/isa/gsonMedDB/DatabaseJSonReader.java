package salud.isa.gsonMedDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.stream.JsonReader;

/**
 * Created by jmalvarez on 11/5/16.
 * http://developer.android.com/intl/es/training/basics/network-ops/xml.html
 */
public class DatabaseJSonReader {

	private static final String MEDICINES_TAGNAME = "medicines";
	private static final String RESCUEMEDPRES_TAGNAME = "rescueMedicinePresentations";
	private static final String ACTIVEINGREDIENTS_TAGNAME = "activeIngredients";
	private static final String PHYSIOTHERAPIES_TAGNAME = "physiotherapies";
	private static final String INHALERS_TAGNAME = "inhalers";
	private static final String POSOLOGIES_TAGNAME = "posologies";
	private static final String MEDICINEPRESENTATIONS_TAGNAME = "medicinePresentations";
	private static final String USERMANUALPHYSIOSTEPS_TAGNAME = "userManualPhysioSteps";
	private static final String USERMANUALSTEPS_TAGNAME = "userManualSteps";


	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String IMAGE_FIELD_TAGNAME = "image";
	private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
	private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
	private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
	private static final String DOSE_FIELD_TAGNAME = "dose";
	private static final String POSREF_FIELD_TAGNAME = "posologyRef";
	private static final String STEPTITLE_FIELD_TAGNAME = "stepTitle";
	private static final String STEPIMAGE_FIELD_TAGNAME = "stepImage";
	private static final String STEPTEXT_FIELD_TAGNAME = "stepText";
	private static final String PHYSIOREF_FIELD_TAGNAME = "physioRef";
	private static final String INHALERREF_FIELD_TAGNAME = "inhalerRef";
	private static final String DESCRIPTION_FIELD_TAGNAME = "description";

	private static final String FIELD_SEPARATOR = ";";

	public DatabaseJSonReader() {
	}

	public String parse(String jsonFileName) throws IOException {

		InputStream usersIS = new FileInputStream(new File(jsonFileName));
		JsonReader reader = new JsonReader(new InputStreamReader(usersIS, "UTF-8"));

		reader.beginObject();
		StringBuffer readData = new StringBuffer();
		while (reader.hasNext()) {
			String name = reader.nextName();

			if (name.equals(MEDICINES_TAGNAME)) {
				readData.append(readMedicines(reader)).append("\n");
			} else if (name.equals(RESCUEMEDPRES_TAGNAME)) {
				readData.append(readRescueMedicinePresentations(reader)).append("\n");
			} else if (name.equals(ACTIVEINGREDIENTS_TAGNAME)) {
				readData.append(readActiveIngredients(reader)).append("\n");
			} else if (name.equals(PHYSIOTHERAPIES_TAGNAME)) {
				readData.append(readPhysiotherapies(reader)).append("\n");
			} else if (name.equals(INHALERS_TAGNAME)) {
				readData.append(readInhalers(reader)).append("\n");
			} else if (name.equals(POSOLOGIES_TAGNAME)) {
				readData.append(readPosologies(reader)).append("\n");
			} else if (name.equals(MEDICINEPRESENTATIONS_TAGNAME)) {
				readData.append(readMedicinePresentations(reader)).append("\n");
			} else if (name.equals(USERMANUALPHYSIOSTEPS_TAGNAME)) {
				readData.append(readUserManualPhysioSteps(reader)).append("\n");
			} else if (name.equals(USERMANUALSTEPS_TAGNAME)) {
				readData.append(readUserManualSteps(reader)).append("\n");
			} else {
				reader.skipValue();
				System.err.println("Category " + name + " not processed.");
			}
		}

		reader.endObject();
		reader.close();
		usersIS.close();

		return new String(readData);
	}

	// Parses the contents of a medicine list.
	private StringBuffer readMedicines(JsonReader reader) throws IOException {
		StringBuffer medicineData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			medicineData.append(readMedicineEntry(reader)).append("\n");
			reader.endObject();
		}
		medicineData.append("\n");
		reader.endArray();
		return medicineData;
	}

	// Parses the contents of a medicine.
	private String readMedicineEntry(JsonReader reader) throws IOException {
		// reader.require(XmlPullParser.START_TAG, ns, SINGLE_ELEMENT_TAGNAME);

		String medName = null;

		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME)) {
				medName = reader.nextString();
			} else {
				reader.skipValue();
			}
		}
		return medName;
	}

	// Parses the contents of a medicine list.
	private StringBuffer readRescueMedicinePresentations(JsonReader reader) throws IOException {
		StringBuffer rescueMedicinePresentationData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			rescueMedicinePresentationData.append(readRescueMedicinePresentationEntry(reader)).append("\n");
			reader.endObject();
		}
		rescueMedicinePresentationData.append("\n");
		reader.endArray();
		return rescueMedicinePresentationData;
	}

	// Parses the contents of a rescue medicine presentation entry
	private String readRescueMedicinePresentationEntry(JsonReader reader) throws IOException {

		String medRef = null;
		String aiRef = null;
		String inhRef = null;
		String dose = null;

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
			} else {
				reader.skipValue();
			}
		}
		return medRef + FIELD_SEPARATOR + aiRef + FIELD_SEPARATOR + inhRef + FIELD_SEPARATOR + dose;
	}

	private StringBuffer readActiveIngredients(JsonReader reader) throws IOException {
		StringBuffer actIngData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			actIngData.append(readActiveIngredientEntry(reader)).append("\n");
			reader.endObject();
		}
		actIngData.append("\n");
		reader.endArray();
		return actIngData;
	}

	private String readActiveIngredientEntry(JsonReader reader) throws IOException {
		String actIng = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME)) {
				actIng = reader.nextString();
			} else {
				reader.skipValue();
			}
		}
		return actIng;
	}

	private StringBuffer readPhysiotherapies(JsonReader reader) throws IOException {
		StringBuffer physioData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			physioData.append(readPhysiotherapyEntry(reader)).append("\n");
			reader.endObject();
		}
		physioData.append("\n");
		reader.endArray();
		return physioData;
	}

	private String readPhysiotherapyEntry(JsonReader reader) throws IOException {

		String physioName = null;
		String physioImage = null;

		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME)) {
				physioName = reader.nextString();
			} else if (name.equals(IMAGE_FIELD_TAGNAME)) {
				physioImage = reader.nextString();
			} else {
				reader.skipValue();
			}
		}
		return physioName + FIELD_SEPARATOR + physioImage;
	}

	private StringBuffer readInhalers(JsonReader reader) throws IOException {
		StringBuffer inhaData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			inhaData.append(readInhalerEntry(reader)).append("\n");
			reader.endObject();
		}
		inhaData.append("\n");
		reader.endArray();
		return inhaData;
	}

	private String readInhalerEntry(JsonReader reader) throws IOException {
		String inhaName = null;
		String inhaImage = null;
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

	private StringBuffer readPosologies(JsonReader reader) throws IOException {
		StringBuffer posoData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			posoData.append(readPosologyEntry(reader)).append("\n");
			reader.endObject();
		}
		posoData.append("\n");
		reader.endArray();
		return posoData;
	}

	private String readPosologyEntry(JsonReader reader) throws IOException {
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

	private StringBuffer readMedicinePresentations(JsonReader reader) throws IOException {
		StringBuffer medPresData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			medPresData.append(readMedicinePresentationsEntry(reader)).append("\n");
			reader.endObject();
		}
		medPresData.append("\n");
		reader.endArray();
		return medPresData;
	}

	private String readMedicinePresentationsEntry(JsonReader reader) throws IOException {
		String medRef = null;
		String aiRef = null;
		String inhRef = null;
		String dose = null;
		String posRef = null;
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
		return medRef + FIELD_SEPARATOR + aiRef + FIELD_SEPARATOR + inhRef + FIELD_SEPARATOR + dose + FIELD_SEPARATOR
				+ posRef;
	}

	private StringBuffer readUserManualPhysioSteps(JsonReader reader) throws IOException {
		StringBuffer userManPhyStepData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			userManPhyStepData.append(readUserManualPhysioStepEntry(reader)).append("\n");
			reader.endObject();
		}
		userManPhyStepData.append("\n");
		reader.endArray();
		return userManPhyStepData;
	}

	private String readUserManualPhysioStepEntry(JsonReader reader) throws IOException {
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
		return umPhyStepTitle + FIELD_SEPARATOR + umPhyStepImage + FIELD_SEPARATOR + umPhyStepText + FIELD_SEPARATOR + umPhysioRef;
	}

	private StringBuffer readUserManualSteps(JsonReader reader) throws IOException {
		StringBuffer userManStepData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			userManStepData.append(readUserManualStepEntry(reader)).append("\n");
			reader.endObject();
		}
		userManStepData.append("\n");
		reader.endArray();
		return userManStepData;
	}

	private String readUserManualStepEntry(JsonReader reader) throws IOException {
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
		return umStepTitle + FIELD_SEPARATOR + umStepImage + FIELD_SEPARATOR + umStepText + FIELD_SEPARATOR + umInhalerRef;
	}
}
