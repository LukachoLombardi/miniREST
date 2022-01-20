import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestResponse {

    @Test
    void checkResponse()
    {
        RESTRequester requester = new RESTRequesterImpl("https://sponsor.ajay.app/api", new httpResponseReaderImpl());
        Map<String, String> map = new HashMap<>();
        map.put("categories", "[\"sponsor\",\"intro\",\"outro\",\"interaction\",\"selfpromo\",\"music_offtopic\",\"preview\",\"filler\"]");
        map.put("videoID", "9ZBfG3IDTD0");
        try {
            System.out.println(requester.issueRequest(map, "skipSegments"));
            System.out.println(requester.getLastRequestURL());
        } catch (IOException e) {
            assertFalse(e instanceof MalformedURLException, "some network error probably occured");
            e.printStackTrace();
        }
    }
}
