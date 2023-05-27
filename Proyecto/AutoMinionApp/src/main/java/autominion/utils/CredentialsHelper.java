package autominion.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CredentialsHelper {
	private final static String appSettingsFile = "./src/main/resources/resources/json/appsettings.json";

	/**
	 * Te lee el fichero json y te devuelve el userDB
	 * 
	 * @return userDB
	 */

	public static String userDB() {
		return readGson("userDB");
	}

	/**
	 * Te lee el fichero json y te devuelve el passwordDB
	 * 
	 * @return passwordDB
	 */

	public static String passwordDB() {
		return readGson("passwordDB");
	}

	/**
	 * Te lee el fichero json y te devuelve el urlDB
	 * 
	 * @return urlDB
	 */

	public static String urlDB() {
		return readGson("urlDB");
	}

	/**
	 * Te lee el fichero json y te devuelve el email
	 * 
	 * @return email
	 */

	public static String email() {
		return readGson("email");
	}

	/**
	 * Te lee el fichero json y te devuelve el passwordEmail
	 * 
	 * @return passwordEmail
	 */

	public static String passwordEmail() {
		return readGson("passwordEmail");
	}

	/**
	 * Lee el fichero json que nosostros le pasamos
	 * 
	 * @param string Nombre del campo que queremos que nos lea
	 * @return String con el campo que nosotros hemos querido saber
	 */

	private static String readGson(String string) {

		List<String> list;
		try {
			list = Files.readAllLines(new File(appSettingsFile).toPath());
			String appsettingsContent = "";
			for (String l : list) {
				appsettingsContent += l;
			}
			JsonObject jsonObject = JsonParser.parseString(appsettingsContent).getAsJsonObject();
			return jsonObject.get(string).getAsString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
