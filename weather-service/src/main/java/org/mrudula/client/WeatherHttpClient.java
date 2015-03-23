package org.mrudula.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by webonise on 20-03-2015.
 */
@Component
public class WeatherHttpClient {
    private static final Logger LOG = LoggerFactory.getLogger(WeatherHttpClient.class);

    protected enum RequestMethod {GET, POST}

    public String performGet(String url,String json ){
        return performRequest(url, json, RequestMethod.GET);
    }

    public String performPost(String url, String json) {
        return performRequest(url,  json, RequestMethod.POST);
    }

    protected String performRequest(String url, String json, RequestMethod requestMethod) {

        String response = null;
        try {
            URL queryUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) queryUrl.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "UTF-8");
            switch (requestMethod) {
                case GET:
                    connection.setRequestMethod("GET");
                    connection.connect();
                    InputStream inStream = connection.getInputStream();
                    response = inputStreamToString(inStream);
                    break;
                case POST:
                    connection.setRequestMethod("POST");
                    connection.connect();
                    InputStream inputStream = connection.getInputStream();
                    response = inputStreamToString(inputStream);
                    break;

            }

        } catch (MalformedURLException | ProtocolException e) {
           LOG.error("Error In Connection : "+e);
        } catch (IOException e){

        }

        return response;
    }

    public String inputStreamToString(InputStream inputStream) {
        StringBuilder out = new StringBuilder();
        String line;
        try ( BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String text = out.toString();
        return text;
    }

}
