package proxy;

import java.net.URI;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import model.dao.JDBCConnector;
import model.dao.MockupDAO;
import model.dao.ProjectDAO;
import model.vo.MockupVO;
import model.vo.ProjectVO;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONObject;

@Path("/project")
public class Proxy {
	
    
    @GET
    @Path("/{anyThing:.*}")//regex to accepts any resource
    @Produces(MediaType.APPLICATION_JSON)
    public Response catchItAll(@Context UriInfo uriInfo, @PathParam("anyThing") String resource) {
    	
    	System.out.println(uriInfo.getAbsolutePath().toString());
    	System.out.println(uriInfo.getPath());
    	System.out.println(uriInfo.getRequestUri().toString());
    	
    	int project_id = getID(resource); 		
    	JDBCConnector jdbc = new JDBCConnector();
    	ProjectVO project = new ProjectDAO(jdbc.getConnection()).getProject(project_id);
    	MockupVO mockup;

    	String query = uriInfo.getRequestUri().getQuery();
    	
    	if(query==null || query.isEmpty()){
    		query="";
    	}
    	else{
    		query = "?"+query;
    	}
    	
    	
    	String nuevo_resource = getOriginalResource(resource);
    	
    	mockup = new MockupDAO(jdbc.getConnection()).getMockup(nuevo_resource+query,"GET");
    	
    	if(mockup!=null){
    		return Response.status(Response.Status.ACCEPTED).entity(mockup.getData_mock()).build();
    	}
    	else{
    		ClientConfig config = new ClientConfig();
    	    Client client = ClientBuilder.newClient(config);
    		 
    	    URI uri = UriBuilder.fromUri(project.getUri()+nuevo_resource+query).build();
    	    WebTarget target = client.target(uri);

    	    Response response = target.request().
    	    					accept(MediaType.APPLICATION_JSON).
    	    					get(Response.class);
    	    
    	    String data = response.readEntity(String.class);    	    					
    	    return Response.status(response.getStatus()).entity(data).build();

    	}
    	
    	
    }
    
    @POST
	@Path("/{anyThing:.*}")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public String createTrackInJSON(@Context UriInfo uriInfo, String track) {
 
    	
    	JSONObject js_object = new JSONObject(track);
    	js_object.put("code", 10);
    	js_object.put("data","some data");
    	js_object.put("url",uriInfo.getAbsolutePath().toString());

    	MultivaluedMap<String,String> values = uriInfo.getQueryParameters();
    	Set<String> claves = values.keySet();
    	for (String key : claves) {
    		String value = values.getFirst(key);
    		js_object.put(key, value);
		}
    	
    	return js_object.toString();
 
	}
    
    private int getID(String path){
    	//ID/app_perfil.php donde ID es project_id
    	int index_slash = path.indexOf("/");
    	String ID = path.substring(0, index_slash);
    	int project_id = new Integer(ID); 
    	return project_id;
    }
    
    private String getOriginalResource(String path){
    	int index_slash = path.indexOf("/");
    	return path.substring(index_slash+1);
    }

}