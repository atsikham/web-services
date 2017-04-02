/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tikhoa.rest.client;

import com.sun.istack.NotNull;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import java.util.List;
import javax.ws.rs.core.MediaType;

public class App {

    private static final String URL = "http://localhost:8080/rest/persons";

    public static void main(String[] args) {
        Client client = Client.create();
        printList(getAllPersons(client, null, null, null, null, null));
        System.out.println();
        printList(getAllPersons(client, "Владимир", null, null, null, null));
        //updatePerson(client, "Vasiliy", "Pupkin", 22, Boolean.TRUE, "2017-04-02", 21);
        deletePerson(client, 21);
        //insertPerson(client, "Владимир", null, null, null, null);
    }

    private static List<Person> getAllPersons(Client client,
            String name,
            String surname,
            Integer age,
            Boolean isEmployee,
            String contactDate) {
        WebResource webResource = client.resource(URL);
        if (name != null) {
            webResource = webResource.queryParam("name", name);
        }
        if (surname != null) {
            webResource = webResource.queryParam("surname", surname);
        }
        if (age != null) {
            webResource = webResource.queryParam("age", age.toString());
        }
        if (isEmployee != null) {
            webResource = webResource.queryParam("isEmployee", isEmployee.toString());
        }
        if (contactDate != null) {
            webResource = webResource.queryParam("contactDate", contactDate);
        }
        ClientResponse response
                = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<List<Person>> type = new GenericType<List<Person>>() {
        };
        return response.getEntity(type);
    }
    
    private static String updatePerson(Client client,
            String name,
            String surname,
            Integer age,
            Boolean isEmployee,
            String contactDate,
            @NotNull Integer id) {
        WebResource webResource = client.resource(URL);
        if (name != null) {
            webResource = webResource.queryParam("name", name);
        }
        if (surname != null) {
            webResource = webResource.queryParam("surname", surname);
        }
        if (age != null) {
            webResource = webResource.queryParam("age", age.toString());
        }
        if (isEmployee != null) {
            webResource = webResource.queryParam("isEmployee", isEmployee.toString());
        }
        if (contactDate != null) {
            webResource = webResource.queryParam("contactDate", contactDate);
        }

        webResource = webResource.queryParam("id", id.toString());

        ClientResponse response
                = webResource.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<String> type = new GenericType<String>() {
        };
        return response.getEntity(type);
    }

    private static String insertPerson(Client client,
            @NotNull String name,
            @NotNull String surname,
            @NotNull Integer age,
            @NotNull Boolean isEmployee,
            @NotNull String contactDate) {
        WebResource webResource = client.resource(URL);
        if (name != null) {
            webResource = webResource.queryParam("name", name);
        }
        if (surname != null) {
            webResource = webResource.queryParam("surname", surname);
        }
        if (age != null) {
            webResource = webResource.queryParam("age", age.toString());
        }
        if (isEmployee != null) {
            webResource = webResource.queryParam("isEmployee", isEmployee.toString());
        }
        if (contactDate != null) {
            webResource = webResource.queryParam("contactDate", contactDate);
        }

        ClientResponse response
                = webResource.accept(MediaType.APPLICATION_JSON).put(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<String> type = new GenericType<String>() {
        };
        return response.getEntity(type);
    }
    
    private static String deletePerson(Client client, @NotNull Integer id) {
        WebResource webResource = client.resource(URL);
        
        webResource = webResource.queryParam("id", id.toString());

        ClientResponse response
                = webResource.accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<String> type = new GenericType<String>() {
        };
        return response.getEntity(type);
    }

    private static void printList(List<Person> persons) {
        for (Person person : persons) {
            System.out.println(person);
        }
    }
}