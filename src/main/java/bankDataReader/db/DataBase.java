package bankDataReader.db;

import bankDataReader.dto.UsersDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class DataBase extends User implements AutoCloseable{

    private static DataBase instance;
    private static int initialHash;
    private static String fileName = "database.json";

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    protected static Map<Integer, UsersDTO> data;
    private DataBase() {
        String path = new File("").getAbsolutePath();
        fileName = path+"/"+fileName;
        File newFile = new File(fileName);

        if (!newFile.exists()) {
            try {
                if (newFile.createNewFile()) {

                    try (FileWriter writer = new FileWriter(newFile)) {
                        Map<Integer, UsersDTO> initialData = new HashMap<>();
                        gson.toJson(initialData, writer);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }

        try (Reader reader = new FileReader(fileName)) {
            Type type = new TypeToken<Map<Integer, UsersDTO>>(){}.getType();

            DataBase.data = gson.fromJson(reader, type);
            initialHash = DataBase.data.hashCode();
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
    public Map<Integer, UsersDTO> getJsonData() {
        return DataBase.data;
    }

    /**
     * Saving data
     * @param data for saving
     * @throws IOException
     */
    public void saveJsonData(Map<Integer, UsersDTO> data) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(data, writer);
        }
    }

    /**
     * Automatically saving data
     */
    @Override
    public void close() throws IOException {
        if (data.hashCode() != initialHash) {
            this.saveJsonData(data);
            initialHash = data.hashCode();
        }
    }
}
