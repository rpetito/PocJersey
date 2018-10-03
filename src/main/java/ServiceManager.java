import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

/**
 * Created by rodrigo on 30/09/18.
 */
public class ServiceManager<T> {

    private static final String BASE_URL = "http://notitas.herokuapp.com";

    private Client client;
    private Class responseClass;


    public ServiceManager(Class responseClass) {
        this.client = Client.create();
        this.responseClass = responseClass;
    }


    public T get(String token, String path) throws ServiceErrorException {
        WebResource.Builder webResource = prepareClient(token, path);
        ClientResponse response = webResource.get(ClientResponse.class);
        if(response.getStatus() != 200) {
            throw new ServiceErrorException(response.getEntity(String.class));
        } else {
            response.bufferEntity();
            String stringResponse = response.getEntity(String.class);
            return (T) new Gson().fromJson(stringResponse, responseClass);
        }
    }



    public boolean put(String token, String path, T objectoActualizado) {
        WebResource.Builder webResource = prepareClient(token, path);
        ClientResponse response = webResource.put(ClientResponse.class, new Gson().toJson(objectoActualizado));
        if(response.getStatus() != 201) {
            throw new ServiceErrorException(response.getEntity(String.class));
        } else {
//            response.bufferEntity();
//            String stringResponse = response.getEntity(String.class);
            return true;
        }
    }



    private WebResource.Builder prepareClient(String token, String path) {
        return this.client.resource(BASE_URL + path)
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON);
    }


}
