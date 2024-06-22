package com.tecsup.restclient;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class ProgramaCliente {

    final static String REST_URL = "http://localhost:8080/colegio/listaEstudiante";
    final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiaXZhbiIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE3MTkwMjIzMDYsImV4cCI6MTcxOTAyNTkwNn0.wGT0wSulqVbiRApCYr06ZQeGkJmboHKFgum4pw3Gp0LzHjqtg0pgEqD_lgvy7RoznIQci14OymV4eVPG0VPbAA";

	public static void main(String[] args) {
		ProgramaCliente p = new ProgramaCliente();
		p.list();
	}
    
    private HttpHeaders createHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + TOKEN);
		return headers;
	}

    public void list() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(createHeaders());

        try {
            ResponseEntity<String> response = restTemplate.exchange(REST_URL, HttpMethod.GET, entity, String.class);
            System.out.println("Response: " + response.getStatusCode());
            System.out.println("Response: " + response.getBody());
        } catch (HttpClientErrorException e) {
            System.err.println("Error: " + e.getStatusCode() + " " + e.getResponseBodyAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void find(Long id) {
        RestTemplate rest = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> httpEntity = new HttpEntity("parameters", headers);

        String url = ProgramaCliente.REST_URL + "/" + id;

        ResponseEntity<String> response = rest.exchange(url, HttpMethod.GET, httpEntity, String.class);
        System.out.println(response.getBody());
    }

    public void delete(Long id) {
        RestTemplate rest = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> httpEntity = new HttpEntity("parameters", headers);

        String url = ProgramaCliente.REST_URL + "/" + id;

        ResponseEntity<String> response = rest.exchange(url, HttpMethod.DELETE, httpEntity, String.class);
        System.out.println(response.getBody());
    }

    public void save() {
        RestTemplate rest = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

        map.add("nombre", "Programa REST");
        map.add("codigo", "999");

        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        String url = ProgramaCliente.REST_URL;

        ResponseEntity<String> response = rest.exchange(url, HttpMethod.POST, req, String.class);
        System.out.println(response.getBody());
    }
    
    
      public void update() {
        RestTemplate rest = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("id", "851");
        map.add("nombre", "Programa REST");
        map.add("codigo", "999");

        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        String url = ProgramaCliente.REST_URL;

        ResponseEntity<String> response = rest.exchange(url, HttpMethod.PUT, req, String.class);
        System.out.println(response.getBody());
    }

}
