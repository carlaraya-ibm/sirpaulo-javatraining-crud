package crudjson;
import java.io.File;

import tools.jackson.core.exc.JacksonIOException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

public class JsonFileManager<T> {
	private final File file;
	private final TypeReference<T> objectType;
	private final ObjectMapper jsonMapper;

	JsonFileManager(String filename, TypeReference<T> objectType) {
		this.file = new File(filename);
		this.objectType = objectType;
		this.jsonMapper = JsonMapper.builder()
				.enable(SerializationFeature.INDENT_OUTPUT)
				.build();
	}
	
	public T read() {
		try {
			T object = this.jsonMapper.readValue(file, objectType);
			return object;
		} catch (JacksonIOException e) {
			// file not found
			return null;
		}
	}
	
	public void write(T object) {
		this.jsonMapper.writeValue(file, object);
	}
}
