package test.testAPI;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.jersey.api.client.ClientResponse;


import APIClient.httpBinClient;
import Requests.httpbinRequest;


public class testHttpBin {
	
	@Test
	public void testGet() throws Exception {

		httpBinClient request = new httpBinClient();
		ClientResponse response = request.getCall();
		Assert.assertTrue(response.getStatus()==200);

		
	}
	
	@Test
	public void testPost() throws IOException, JSONException {
		String id = "test-access-1";
		String recordName = "eminem";
        String location = "package.yaml";
		httpbinRequest requestBody = new httpbinRequest();
		requestBody.setId(id);
		requestBody.setRecordName(recordName);
		requestBody.setLocation(location);
		httpBinClient request = new httpBinClient();
		ClientResponse response = request.postCall(getRequest(requestBody));
		InputStream res = response.getEntityInputStream();
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(res));
		String st = br.readLine();
		while(st!=null) {
			st.trim();
			sb.append(st);
			st= br.readLine();
		}
		System.out.println(sb.toString());
		JSONObject jsres = new JSONObject(sb.toString());
		String temp = jsres.getString("data");
		JSONObject js = new JSONObject(temp);
		String ids = js.getString("id");
		boolean getId = sb.toString().contains(id);
		Assert.assertTrue(getId);		
		Assert.assertTrue(response.getStatus()==200);
		
	}
	
	
	@Test
	public void testUpdate() throws IOException, JSONException {
		String requestBody = "{\n" + 
				"            'id': 'test-access-1',\n" + 
				"            'record_name': 'eminem',\n" + 
				"            'location': 'package.yaml',\n" + 
				"            'songs': [\n" + 
				"                {\n" + 
				"                        'name': 'disco',\n" + 
				"                        'duration’': 29900,\n" + 
				"                        'genre': 'country',\n" + 
				"                        'language': 'spanish',\n" + 
				"                },\n" + 
				"                {\n" + 
				"                    'name': 'theatre',\n" + 
				"                    'duration’': 43234,\n" + 
				"                    'genre': 'rock',\n" + 
				"                    'language': 'french',\n" + 
				"                }\n" + 
				"            ],\n" + 
				"            'country': 'US',\n" + 
				"            'regions': ['portland','sfo','atlanta'],\n" + 
				"        }";
		httpBinClient request = new httpBinClient();

		ClientResponse response = request.updateCall(requestBody);
		InputStream res = response.getEntityInputStream();
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(res));
		String st = br.readLine();
		while(st!=null) {
			sb.append(st);
			st= br.readLine();
		}
		System.out.println(sb.toString());

		boolean getId = sb.toString().contains("spanish");
		JSONObject jsres = new JSONObject(sb.toString());

		String temp = jsres.getString("data");
		JSONObject js = new JSONObject(temp);
		JSONObject songs = (JSONObject) js.getJSONArray("songs").get(0);
		String language = songs.getString("language");
		Assert.assertTrue(language.contentEquals("spanish"));		
		Assert.assertTrue(response.getStatus()==200);
		
	}
	
	@Test
	public void testDelete() throws IOException, JSONException {
		
		httpBinClient request = new httpBinClient();

		ClientResponse response = request.deleteCall();
		InputStream res = response.getEntityInputStream();
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(res));
		String st = br.readLine();
		while(st!=null) {
			sb.append(st);
			st= br.readLine();
		}
		System.out.println(sb.toString());

		boolean getId = sb.toString().contains("spanish");
		Assert.assertTrue(response.getStatus()==200,"status code is not correct");
		Assert.assertFalse(getId,"id is not correct");		
		
	}
	
	
	
	
	public String getRequest(Object object) {
		ObjectMapper Obj = new ObjectMapper(); 
		String jsonStr = "";
        try { 
            jsonStr = Obj.writeValueAsString(object); 
            System.out.println(jsonStr); 
        } 
        catch (IOException e) { 
            e.printStackTrace(); 
        } 
		return jsonStr;
		
	}

}
