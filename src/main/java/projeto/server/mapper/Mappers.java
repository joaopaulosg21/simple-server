package projeto.server.mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Mappers {

    public static String mapInputStreamToString(InputStream inputStream) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Map<String, String> headers = new HashMap<>();
        StringBuilder body = new StringBuilder();

        int contentLength = 0;
        String line;
        // Pega os headers e o tamanho do body
        while ((line = reader.readLine()) != null && !line.isEmpty()) {

            if (line.contains(":")) {
                String key = line.split(":")[0].trim();
                String value = line.split(":")[1].trim();
                headers.put(key, value);
            }

            if (line.startsWith("Content-Length:")) {
                contentLength = Integer.valueOf(line.split(":")[1].trim());
            }
        }

        System.out.println(headers);
        // Usa o read para ler o conteudo do body
        if (contentLength > 0) {
            char[] ch = new char[contentLength];
            reader.read(ch, 0, contentLength);
            body.append(ch);
        }

        return body.toString();
    }
}
