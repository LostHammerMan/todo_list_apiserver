package org.zerock.apiserver.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

// formatter 만든 이후 프로젝트에 사용하기 위해서는 등록 필요
public class LocalDateFormatter implements Formatter<LocalDateTime> {

    // text -> LocalDateTime
    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
        return LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    // localDateTime -> text
    @Override
    public String print(LocalDateTime object, Locale locale) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object);
    }
}
