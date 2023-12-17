package Spike.JobDescription.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.format.datetime.standard.DateTimeFormatterFactory;

import java.time.LocalDate;

public class LocalDateToStringConverter implements Converter<LocalDate, String> {
    @Override
    public String convert(LocalDate source) {
        DateTimeFormatterFactory formatterFactory = new DateTimeFormatterFactory("yyyy-MM-dd");
        return formatterFactory.createDateTimeFormatter().format(source);
    }
}
