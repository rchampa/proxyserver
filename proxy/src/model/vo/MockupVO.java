package model.vo;

public class MockupVO {

	private int mockup_id;
    private String url_mock;
    private String headers_mock;
    private String method_mock;
    private String data_mock;
    private int project_id;
    
	public MockupVO(int mockup_id, String url_mock, String headers_mock, String method_mock,
			String data_mock, int project_id) {
		this.mockup_id = mockup_id;
		this.url_mock = url_mock;
		this.headers_mock = headers_mock;
		this.data_mock = data_mock;
		this.method_mock = method_mock;
		this.project_id = project_id;
	}
	public MockupVO(String url_mock, String headers_mock, String method_mock,
			String data_mock, int project_id) {
		this.url_mock = url_mock;
		this.headers_mock = headers_mock;
		this.data_mock = data_mock;
		this.method_mock = method_mock;
		this.project_id = project_id;
	}

	public int getMockup_id() {
		return mockup_id;
	}

	public String getUrl_mock() {
		return url_mock;
	}

	public String getHeaders_mock() {
		return headers_mock;
	}

	public String getData_mock() {
		return data_mock;
	}

	public int getProject_id() {
		return project_id;
	}

	public String getMethod_mock() {
		return method_mock;
	}
    
	
    
}
