import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class httpResponseReaderImpl implements httpResponseReader {
    @Override
    public String readFromURL(String urlString) throws IOException {
        StringBuilder response = new StringBuilder();

        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        BufferedReader reader = new BufferedReader(

                new InputStreamReader(
                        connection.getInputStream()));
        String inputLine;
        while ((inputLine = reader.readLine()) != null)
            response.append(inputLine);
        reader.close();
        return response.toString();
    }
}
