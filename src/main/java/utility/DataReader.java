package utility;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class DataReader {
	private static JSONObject testData;
	private static JSONObject config;

	static {
		// Load the Test data and configuration data from the specified JSON file
		testData = loadJsonFile("testdata/TestData.json");
		config = loadJsonFile("configdata/config.json");
	}

	private static JSONObject loadJsonFile(String filePath) {
		try {
			// Construct the absolute path to the JSON file
			String absolutePath = "src/test/resources/" + filePath;
			FileReader fileReader = new FileReader(absolutePath);
			JSONTokener tokener = new JSONTokener(fileReader);
			// Parse the JSON file and return the resulting JSONObject
			return new JSONObject(tokener);
		} catch (IOException e) {
			e.printStackTrace();
			// Throw a runtime exception if the file cannot be read
			throw new RuntimeException("Unable to read data file: " + filePath);
		}
	}

	public static String getStringValue(String key, String file) {
		// Retrieve the JSON object for the specified file and return the string value
		// for the given key
		JSONObject jsonObject = getFileObject(file);
		return jsonObject.optString(key, "Key not found");
	}

	public static int getIntValue(String key, String file) {
		// Retrieve the JSON object for the specified file and return the integer value
		// for the given key
		JSONObject jsonObject = getFileObject(file);
		return jsonObject.optInt(key, -1);
	}

	public static boolean getBooleanValue(String key, String file) {
		// Retrieve the JSON object for the specified file and return the boolean value
		// for the given key
		JSONObject jsonObject = getFileObject(file);
		return jsonObject.optBoolean(key, false);
	}

	public static double getDoubleValue(String key, String file) {
		// Retrieve the JSON object for the specified file and return the double value
		// for the given key
		JSONObject jsonObject = getFileObject(file);
		return jsonObject.optDouble(key, 0.0);
	}

	public static List<String> getStringList(String key, String file) {
		// Retrieve the JSON object for the specified file and return the list of
		// strings for the given key
		JSONObject jsonObject = getFileObject(file);
		JSONArray jsonArray = jsonObject.optJSONArray(key);
		List<String> stringList = new ArrayList<>();
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.length(); i++) {
				stringList.add(jsonArray.getString(i));
			}
		}
		return stringList;
	}
	
	public static JSONObject getJsonObject(String key, String file) {
		JSONObject jsonObject = getFileObject(file);
		return jsonObject.optJSONObject(key);
	}

	private static JSONObject getFileObject(String file) {
		// Return the appropriate JSON object based on the file name
		switch (file.toLowerCase()) {
		case "testdata":
			return testData;
		case "config":
			return config;
		default:
			throw new IllegalArgumentException("Invalid file name: " + file);
		}
	}

}
