package bankDataReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;
import java.util.Objects;
import java.lang.reflect.Type;

public class DataBase implements AutoCloseable{
    private static DataBase instance;

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static Map<String, Object> data;
    private DataBase() {
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/database.json")))) {
            Type type = new TypeToken<Map<String, Object>>(){}.getType();

            DataBase.data = gson.fromJson(reader, type);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            DataBase.data = null;
        }
    }

    public static synchronized DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    /**
     * Function for get Gson exemplar
     * @return Gson
     */
    public static Gson getGson() {
        return gson;
    }

    /**
     * Get data from database
     * @return Map<String, Object>
     */
    public Map<String, Object> getJsonData() {
        return DataBase.data;
    }

    /**
     * Saving data
     * @param data for saving
     * @throws IOException
     */
    public void saveJsonData(Map<String, Object> data) throws IOException {
        String filePath = "src/main/resources/";
        String fileName = "database.json";
        try (FileWriter writer = new FileWriter(filePath+fileName)) {
            gson.toJson(data, writer);
        }
    }

    /**
     * Automatically saving data
     */
    @Override
    public void close() throws Exception {
        this.saveJsonData(data);
    }
}
