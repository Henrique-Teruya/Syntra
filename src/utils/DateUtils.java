package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {

    private static final DateTimeFormatter UI_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DB_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String uiToDb(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return "";
        }
        try {
            LocalDate date = LocalDate.parse(dateStr, UI_FORMATTER);
            return date.format(DB_FORMATTER);
        } catch (DateTimeParseException e) {
            return dateStr; // Retorna original se falhar
        }
    }

    public static String dbToUi(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return "";
        }
        try {
            LocalDate date = LocalDate.parse(dateStr, DB_FORMATTER);
            return date.format(UI_FORMATTER);
        } catch (DateTimeParseException e) {
            return dateStr; // Retorna original se falhar
        }
    }
}
