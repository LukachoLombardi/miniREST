import java.io.IOException;

public interface httpResponseReader {
    String readFromURL(String urlString) throws IOException;
}
