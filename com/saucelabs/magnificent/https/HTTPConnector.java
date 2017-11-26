package com.saucelabs.magnificent.https;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.HttpClientBuilder;
import com.saucelabs.magnificent.exceptions.ExceptionApp;
import com.saucelabs.magnificent.exceptions.MsgException;

public class HTTPConnector {
    
	private final static String url = "http://localhost:12345";
	private static HTTPConnector instance;	
	private HTTPConnector() {}
	
	public int connect() throws ExceptionApp {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		HttpResponse response;		
		try {
			response = client.execute(request);
		} catch (IOException e) {
			throw new ExceptionApp(MsgException.FAILED_TO_OPEN_CONNECTION);
		}
		int code = response.getStatusLine().getStatusCode();
		return code;		
	}
    
    public static HTTPConnector getInstance() {
        if(instance == null) {
            instance = new HTTPConnector();
        }
        return instance;
    }
}
