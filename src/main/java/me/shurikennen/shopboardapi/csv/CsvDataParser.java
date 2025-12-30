package me.shurikennen.shopboardapi.csv;

import lombok.Builder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Builder
public class CsvDataParser {

    private static final Logger log = LogManager.getLogger(CsvDataParser.class);
    public int columns;
    // if this word is anywhere in the line it will be ignored entirely
    public List<String> illegalKeyWords;
    public Map<Integer, Supplier<String>> columnDefaultSupplier;

    public List<String[]> parse(String data, boolean logBlanks) {
        String[] lines = data.split("\n");


        List<String[]> sheet = new ArrayList<>();

        Outer:
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].replaceFirst(",+\\s*$", "");
            long commas = line.chars().filter(ch -> ch == ',').count();

            // Skip lines with too few columns these could be random strings in the document to provide info
            if (commas < (columns - 1)) {
                log.info("Skipping line, invalid column count: {}", line);
                continue;
            }


            for (String illegalKeyWord : illegalKeyWords) {
                if (line.contains(illegalKeyWord)) {
                    log.info("Skipping line, illegal key word found: {}", line);
                    continue Outer;
                }
            }

            String[] rowData = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            rowData = sanitize(rowData);
            processDefaults(rowData);
            if (logBlanks) logBlanks(rowData);

            sheet.add(rowData);
        }

        return sheet;
    }

    private void logBlanks(String[] raw) {
        for (String s : raw) {
            if (s == null || s.isBlank()) {
                log.info("Blank value found inside: {}", (Object) raw);
            }
        }
    }

    private void processDefaults(String[] raw) {
        columnDefaultSupplier.forEach((index, supplier) -> {
            String currentValue = raw[index];

            if (currentValue == null || currentValue.isBlank()) {
                raw[index] = supplier.get();
            }
        });
    }

    private String[] sanitize(String[] raw) {
        for (int i = 0; i < raw.length; i++) {
            if (raw[i] == null) continue;
            String cleaned = raw[i].trim();
            // Remove leading/trailing quotes and handle escaped double quotes ("")
            if (cleaned.startsWith("\"") && cleaned.endsWith("\"")) {
                cleaned = cleaned.substring(1, cleaned.length() - 1);
            }
            raw[i] = cleaned.replace("\"\"", "\"");
        }

        return Arrays.copyOf(raw, columns);
    }
}
