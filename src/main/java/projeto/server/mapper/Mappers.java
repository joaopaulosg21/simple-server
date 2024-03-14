package projeto.server.mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Mappers {

    public static String mapInputStreamToString(InputStream inputStream) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder headers = new StringBuilder();
        StringBuilder body = new StringBuilder();

        int contentLength = 0;
        String line;
        // Pega os headers e o tamanho do body
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            headers.append(line).append("\n");

            if (line.startsWith("Content-Length:")) {
                contentLength = Integer.valueOf(line.split(":")[1].trim());
            }
        }

        // Usa o read para ler o conteudo do body
        if (contentLength > 0) {
            char[] ch = new char[contentLength];
            reader.read(ch, 0, contentLength);
            body.append(ch);
        }

        return body.toString();
    }
}
