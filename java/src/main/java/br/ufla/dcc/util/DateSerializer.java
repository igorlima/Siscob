package br.ufla.dcc.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class DateSerializer extends JsonSerializer<Date> {

	public static final String DATE_FORMAT = "dd/MM/yyyy";

	private static SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

	@Override
	public void serialize(Date date, JsonGenerator generator,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {

		generator.writeString(formatter.format(date));
	}
	 
	public static String formatDate(Date date) {
		
		return formatter.format(date);
	}

}
