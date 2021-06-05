package wsclient;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class BankService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "https://wsrestfulbank.herokuapp.com/api/";
    public BankService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    public List<Banque> getAllBanks(){
        ResponseEntity<Banque[]> responseEntity =
                restTemplate.getForEntity(BASE_URL+"getAllBanks", Banque[].class);
        Banque[] userArray = responseEntity.getBody();
        return Arrays.asList(userArray);
    }

    public Banque consultBanque(Long nBanque){
        try {
            return restTemplate.getForObject(BASE_URL+nBanque, Banque.class);
        }catch (HttpClientErrorException e){
            if(e.getStatusCode() == HttpStatus.NOT_FOUND)return null;
            else throw e;
        }
    }

    public void deleteBank(long nBanque){
        try {
            restTemplate.delete(BASE_URL + nBanque);
        }catch(HttpClientErrorException e){
            if(e.getStatusCode() == HttpStatus.NOT_FOUND);
        }
    }
    public void addBanque(Banque banque){
        try {
            HttpEntity<Banque> request = new HttpEntity<>(banque);
            System.out.println(restTemplate.postForObject(BASE_URL + "addBank", request, Banque.class));
        }catch(Exception e){e.printStackTrace();}
    }
    public void modifierBanque(Banque banque){
        try {
            HttpEntity<Banque> request = new HttpEntity<>(banque);
            System.out.println(restTemplate.patchForObject(BASE_URL+"editBank" , request, Banque.class));
        }catch(Exception e){e.printStackTrace();}
    }
}