package br.ufla.dcc.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class DateDeserializer extends JsonDeserializer<Date> {

	public static final String DATE_FORMAT = "dd/MM/yyyy";

	private static SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

	@Override
	public Date deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {

		return parseDate(parser.getText());
	}
	
	public static Date parseDate(String dateText) {
		
		try {

			return formatter.parse(dateText);

		} catch (ParseException e) {

			e.printStackTrace();
			return null;
		}
	}

}
