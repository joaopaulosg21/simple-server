package projeto.server.mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import projeto.server.enums.HttpStatus;
import projeto.server.pojos.Request;

public class Mappers {

    public static Request mapInputStreamToRequest(InputStream inputStream) throws IOException {

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
            } else {
                String[] firstLine = line.split(" ");
                headers.put("method", firstLine[0]);
                headers.put("route", firstLine[1]);
            }

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
        return new Request(headers, body.toString());
    }

    public static String mapHeadersToStringResponse(Map<String, String> headers, HttpStatus status) {
        StringBuilder header = new StringBuilder();

        header.append("HTTP/1.1 ").append(status.toString()).append("\r\n")
                .append("Content-Type: ").append(headers.get("Content-Type")).append("\r\n")
                .append("Content-Length: ").append(headers.get("Content-Length"));

        return header.toString();
    }
}
