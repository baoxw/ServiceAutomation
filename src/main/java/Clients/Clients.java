package Clients;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

public class Clients {
	
	public ClientResponse getCall(String requestURL,HashMap<String, String> headers) {
		Client client = Client.create();
		WebResource webResource = client.resource(requestURL);
		WebResource.Builder builder = webResource.accept(MediaType.APPLICATION_JSON);
		if(headers!=null) {
			for(Map.Entry<String,String> entry: headers.entrySet()) {
				builder.header(entry.getKey(), entry.getValue());
			}
		}
		
		ClientResponse response = builder.get(ClientResponse.class);
		
		return response;
	}
	
	public ClientResponse getHttpsCall(String requestURL,HashMap<String, String> headers) throws KeyManagementException, NoSuchAlgorithmException {
		HostnameVerifier hostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
		ClientConfig config = new DefaultClientConfig();
		SSLContext ctx = SSLContext.getInstance("SSL");
        TrustManager[] trustManager = {new NoOpTrustManager()};

		ctx.init(null, trustManager, null);
		config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(hostnameVerifier, ctx));
		Client client = Client.create(config);

		WebResource webResource = client.resource(requestURL);
        WebResource.Builder builder = webResource.accept(MediaType.APPLICATION_JSON);
		if(headers!=null) {
			for(Map.Entry<String,String> entry: headers.entrySet()) {
				builder.header(entry.getKey(), entry.getValue());
			}
		}
		
		ClientResponse response = builder.get(ClientResponse.class);
		
		return response;

	}
	
	
	
	public ClientResponse postCall(String requestURL, String requestBody, HashMap<String, String> headers) {
		Client client = Client.create();
		WebResource webResource = client.resource(requestURL);
		WebResource.Builder builder = webResource.accept(MediaType.APPLICATION_JSON);
		if(headers != null) {
			for(Map.Entry<String,String> entry:headers.entrySet()) {
				builder.header(entry.getKey(), entry.getValue());
			}
		}
		ClientResponse response = builder.post(ClientResponse.class,requestBody);
		
		return response;
	}
	
	public ClientResponse deleteCall(String requestURL, HashMap<String, String> headers) {
		Client client = Client.create();
		WebResource webResource = client.resource(requestURL);
		WebResource.Builder builder = webResource.accept(MediaType.APPLICATION_JSON);
		if(headers != null) {
			for(Map.Entry<String,String> entry:headers.entrySet()) {
				builder.header(entry.getKey(), entry.getValue());
			}
		}
		ClientResponse response = builder.delete(ClientResponse.class);
		
		return response;
	}
	

	public ClientResponse updateCall(String requestURL, String requestBody, HashMap<String, String> headers) {
		Client client = Client.create();
		WebResource webResource = client.resource(requestURL);
		WebResource.Builder builder = webResource.accept(MediaType.APPLICATION_JSON);
		if(headers != null) {
			for(Map.Entry<String,String> entry:headers.entrySet()) {
				builder.header(entry.getKey(), entry.getValue());
			}
		}
		ClientResponse response = builder.put(ClientResponse.class,requestBody);
		
		return response;
	}
	
	public ClientResponse postHttpsCall(String requestURL, Object requestBody, HashMap<String, String> headers) throws KeyManagementException, NoSuchAlgorithmException {
		HostnameVerifier hostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
		ClientConfig config = new DefaultClientConfig();
		SSLContext ctx = SSLContext.getInstance("SSL");
        TrustManager[] trustManager = {new NoOpTrustManager()};

		ctx.init(null, trustManager, null);
		config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(hostnameVerifier, ctx));
		Client client = Client.create(config);

		WebResource webResource = client.resource(requestURL);
        WebResource.Builder builder = webResource.accept(MediaType.APPLICATION_JSON);
		if(headers!=null) {
			for(Map.Entry<String,String> entry: headers.entrySet()) {
				builder.header(entry.getKey(), entry.getValue());
			}
		}
		
		ClientResponse response = builder.post(ClientResponse.class,requestBody);
		
		return response;

	}
	
	public class NoOpHostnameVerifier implements HostnameVerifier {
	    @Override
	    public boolean verify(String s, SSLSession sslSession) {
	        return true;
	    }
	}
	
	public class NoOpTrustManager implements X509TrustManager {
	    @Override
	    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
	    }

	    @Override
	    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
	    }

	    @Override
	    public X509Certificate[] getAcceptedIssuers() {
	        return new X509Certificate[0];
	    }
	}

}
