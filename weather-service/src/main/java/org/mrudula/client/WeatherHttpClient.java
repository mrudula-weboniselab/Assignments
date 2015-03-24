package org.mrudula.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;

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

            HttpClient client = new DefaultHttpClient();


            switch (requestMethod) {
                case GET:
                    HttpGet requestGet = new HttpGet(url);
                    HttpResponse responseJsonGet = client.execute(requestGet);
                    InputStream inStream =responseJsonGet.getEntity().getContent();
                    response = inputStreamToString(inStream);
                    break;
                case POST:
                    HttpPost requestPost = new HttpPost(url);
                    HttpResponse responseJsonPost = client.execute(requestPost);
                    InputStream inputStream = responseJsonPost.getEntity().getContent();
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
