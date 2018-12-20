package ch.skunky.skunklaw.controller;

import ch.skunky.skunklaw.model.Client;
import ch.skunky.skunklaw.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

/**
 * https://spring.io/guides/gs/rest-service-cors/
 * https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
 * https://www.baeldung.com/spring-cors
 *
 * @PreAuthorize("#oauth2.hasScope('read')")
 */

@RestController
public class ClientSearchController {

    @Autowired
    private ClientService clientService;

    @PreAuthorize("hasAuthority('read')")
    @CrossOrigin
    @GetMapping("/clients/{id}")
    public Client getClient(@PathVariable long id){
        Optional<Client> client = clientService.getClient(id);
        if(client.isPresent()) return client.get();
        throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }


    @PreAuthorize("hasAuthority('read')")
    @CrossOrigin
    @GetMapping(value="/clients")
    public List<Client> searchClient(
            @RequestParam(value="name", required=false) String name)
    {
        if(name!=null){
            return clientService.findClientLike(name);
        }
        else{
            return clientService.findAll();
        }
    }

}