package com.aravind.test;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;

public class Test {

    public static void main(String[] args) {
        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        String response = target.path("rest").
                            path("hello").
                            request().
                            accept(MediaType.TEXT_PLAIN).
                            get(Response.class)
                            .toString();


        String plainAnswer =
                target.path("rest").path("hello").request().accept(MediaType.TEXT_PLAIN).get(String.class);
        String xmlAnswer =
                target.path("rest").path("hello").request().accept(MediaType.TEXT_XML).get(String.class);
        String htmlAnswer=
                target.path("rest").path("hello").request().accept(MediaType.TEXT_HTML).get(String.class);

        System.out.println(response);
        System.out.println(plainAnswer);
        System.out.println(xmlAnswer);
        System.out.println(htmlAnswer);
        
        String xmlResponse = target.path("rest").path("todo").request()
                .accept(MediaType.TEXT_XML).get(String.class);
        // Get XML for application
        String xmlAppResponse =target.path("rest").path("todo").request()
                .accept(MediaType.APPLICATION_XML).get(String.class);

        // Get JSON for application
       /* String jsonResponse = target.path("rest").path("todo").request()
                .accept(MediaType.APPLICATION_JSON).get(String.class);*/

        System.out.println(xmlResponse);
        System.out.println(xmlAppResponse);
        /*System.out.println(jsonResponse);*/
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/restful").build();
    }
}