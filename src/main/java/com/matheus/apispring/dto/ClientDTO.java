package com.matheus.apispring.dto;

import com.matheus.apispring.domain.Client;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "The name must be fulfilled.")
    @Length(min = 5, max = 80, message = "The name must have between 5 and 80 chars.")
    private String name;

    private Integer id;

    @NotEmpty(message = "The name must be fulfilled.")
    @Email(message = "Invalid email")
    private String email;

    private ClientDTO(){}

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
    }

    public static List<ClientDTO> parseListIntoDTOList(List<Client> clientList){
        List<ClientDTO> clientDTOList = new ArrayList<>();
        for(Client client: clientList){
            ClientDTO clientDTO = new ClientDTO(client);
            clientDTOList.add(clientDTO);
        }
        return clientDTOList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}