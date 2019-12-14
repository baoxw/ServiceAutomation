package APIClient;

import java.security.GeneralSecurityException;
import java.security.KeyManagementException;


import com.sun.jersey.api.client.ClientResponse;

import Clients.Clients;

public class httpBinClient {
	
	public ClientResponse getCall() throws KeyManagementException, GeneralSecurityException {
		String url = "https://httpbin.org/get";
		Clients client = new Clients();
		
		
	
		ClientResponse res = client.getHttpsCall(url, null);
		return res;
	}
	
	public ClientResponse postCall(String requestBody) {
		String url = "https://httpbin.org/post";
		Clients client = new Clients();
		ClientResponse res = client.postCall(url, requestBody, null);
		return res;
		
	}
	
	public ClientResponse updateCall(String requestBody) {
		String url = "https://httpbin.org/put";
		Clients client = new Clients();
		ClientResponse res = client.updateCall(url, requestBody, null);
		return res;
		
	}
	
	public ClientResponse deleteCall() {
		String url = "https://httpbin.org/delete";
		Clients client = new Clients();
		ClientResponse res = client.deleteCall(url, null);
		return res;
		
	}

}
