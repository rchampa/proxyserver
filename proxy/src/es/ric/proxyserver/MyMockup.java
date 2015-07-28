package es.ric.proxyserver;

public class MyMockup {
	
	private String resource;
	private String method;
	
	public MyMockup(String resource, String method) {
		this.resource = resource;
		this.method = method;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	

}
