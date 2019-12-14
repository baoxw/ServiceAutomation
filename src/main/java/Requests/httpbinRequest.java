package Requests;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class httpbinRequest {

	private String id;
	private String recordName;
	private String location;

	public String getId() {
	return id;
	}

	public void setId(String id) {
	this.id = id;
	}

	public String getRecordName() {
	return recordName;
	}

	public void setRecordName(String recordName) {
	this.recordName = recordName;
	}

	public String getLocation() {
	return location;
	}

	public void setLocation(String location) {
	this.location = location;
	}

}
