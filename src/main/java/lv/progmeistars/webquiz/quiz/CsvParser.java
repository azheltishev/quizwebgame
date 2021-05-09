package lv.progmeistars.webquiz.quiz;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvParser {

    public List<WordTranslationTypeData> parseFile(InputStream is) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
            List<String[]> lines = readAll(inputStreamReader);

            var list = new ArrayList<WordTranslationTypeData>();
            for (String[] line : lines) {
                WordTranslationTypeData data = map(line);
                list.add(data);
            }

            return list;
        } catch (IOException e) {
            throw new RuntimeException("Could not read or parse file", e);
        }
    }


    private List<String[]> readAll(Reader reader) throws IOException {
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> list = csvReader.readAll();
        reader.close();
        csvReader.close();
        return list;
    }

    private WordTranslationTypeData map(String[] line) {
        String source = line[0];
        String translated = line[1];
        String type = line[2];

        var to = new WordTranslationTypeData();

        to.setSourceLanguage(source);
        to.setTranslationLanguage(translated);
        to.setWordType(type);

        return to;
    }
}
