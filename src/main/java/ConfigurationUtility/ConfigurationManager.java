package ConfigurationUtility;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class ConfigurationManager {

	private static Map<String, String> configInMap=new HashMap<String, String>();
	
	static {
		loadConfigAndStoreInMap();
	};

	private static void loadConfigAndStoreInMap() {
		InputStream input;
		try {
			input= ConfigurationManager.class.getClassLoader().getResourceAsStream("Configuration.properties");
			if(input==null)
			{
				throw new RuntimeException("Unable to read properties file");
			}
			Properties property=new Properties();
			property.load(input);
			for(String key:property.stringPropertyNames())
			{
				configInMap.put(key, property.getProperty(key));
			}
		}catch (Exception e) {
			throw new RuntimeException("Unable to load properties file");
		}
	}
	
	public static String getKeyValue(ConfigEnumUtility enumKey){
		return configInMap.get(enumKey.name());	
	}
}
