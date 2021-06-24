package PatronCadenaMando;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public abstract class Chain {
    protected Chain successor;
    protected String chainName;

    public Chain(Chain suc) {
        successor = suc;
    }

    protected StringBuffer readChain(JsonReader reader, String name) throws IOException {
        if (name.equals(chainName)) {
            StringBuffer objectData = new StringBuffer();
            reader.beginArray();
            while (reader.hasNext()) {
                reader.beginObject();
                objectData.append(readObjectEntry(reader)).append("\n");
                reader.endObject();
            }
            objectData.append("\n");
            reader.endArray();
            return objectData;
        } else {
            return successor.readChain(reader, name);
        }
    }
    protected abstract String readObjectEntry(JsonReader reader) throws IOException;
}
