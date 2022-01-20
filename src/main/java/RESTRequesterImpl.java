import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RESTRequesterImpl implements RESTRequester {
    private final List<String> unexpectedContents = List.of("<!DOCTYPE html>");
    private String apiURLString;
    private httpResponseReader httpResponseReader;
    private String lastRequestURL;

    @Override
    public httpResponseReader getHttpResponseReader() {
        return httpResponseReader;
    }

    @Override
    public void setHttpResponseReader(httpResponseReader httpResponseReader) {
        this.httpResponseReader = httpResponseReader;
    }

    @Override
    public String getLastRequestURL() {
        return lastRequestURL;
    }


    RESTRequesterImpl(String urlString, httpResponseReader httpReader)
    {
        setApiURL(urlString);
        setHttpResponseReader(httpReader);
    }

    @Override
    public void setApiURL(String urlString) {
        if(urlString.endsWith("/"))
        {
            apiURLString = urlString;
        }
        else
        {
            apiURLString = urlString + "/";
        }
    }

    @Override
    public String getApiURL() {
        return apiURLString;
    }

    @Override
    public String issueRequest(Map<String, String> arguments, String endpoint) throws IOException {
       StringBuilder requestString = new StringBuilder(apiURLString).append(endpoint).append("/");
       Pattern endpointAntiPattern = Pattern.compile("\\W");
       if(endpointAntiPattern.matcher(endpoint).find())
       {
           throw new MalformedURLException("malformed endpoint");
       }
       Pattern pattern = Pattern.compile("\\[[\\w*,\\\"]*\\]|[\\w]*");

       requestString.append("?");
       for(String key : arguments.keySet())
       {
            if(pattern.matcher(key).matches() && pattern.matcher(arguments.get(key)).matches())
            {
                if(!requestString.toString().endsWith("?"))
                {
                    requestString.append("&");
                }
                requestString.append(key).append("=").append(arguments.get(key));
            }
            else
            {
                throw new IOException("invalid argument key or value: " + key + ", " + arguments.get(key));
            }
       }
       lastRequestURL = requestString.toString();
       String response = "[]";
       try {
           response = httpResponseReader.readFromURL(requestString.toString());
       }
       catch (FileNotFoundException ignored) {}
       for(String s : unexpectedContents)
       {
           if(response.contains(s))
           {
               System.out.println("it seems like the response contains " + s + ". You probably don't want that to happen");
           }
       }
       return response;
    }

    @Override
    public String stringToURLArray(List<String> list) {
        StringBuilder stringedArray = new StringBuilder("[");
        for(String s : list)
        {
            stringedArray.append("\"").append(s).append("\"");
            if(!(list.indexOf(s) >= getListMax(list)))
            {
                stringedArray.append(",");
            }
        }
        stringedArray.append("]");
        return stringedArray.toString();
    }

    private int getListMax(List<String> list)
    {
        int max = 0;
        for(Object o : list)
        {
            max++;
        }
        return max;
    }
}
