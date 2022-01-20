import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

public interface RESTRequester {
    void setApiURL(String urlString);
    String getApiURL();

    String issueRequest(Map<String, String> arguments, String endpoint) throws IOException;
    String stringToURLArray(List<String> list);

    httpResponseReader getHttpResponseReader();
    void setHttpResponseReader(httpResponseReader reader);

    String getLastRequestURL();
}
