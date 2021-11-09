package com.prova.api.person;

import com.prova.api.exceptions.Exceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public List<Contact> index(){
        return contactRepository.findAll();
    }

    public Contact findById(Long id){
        return contactRepository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    public Contact create(Contact contact){

        if(existsByEmail(contact)){
            throw new Exceptions("This email is already in use");
        } else if (existsByPhone((contact))){
            throw new Exceptions("This phone is already in use");
        }
        return contactRepository.save(contact);
    }

    public Contact update(Contact contact){
        if(!contactRepository.existsById(contact.getId())){
            throw new RuntimeException("Contact not found");
        }
        return contactRepository.save(contact);
    }

    public void delete(Long id){
        if(!contactRepository.existsById(id)){
            throw new RuntimeException("Contact not found");
        }
        contactRepository.deleteById(id);
    }

    public boolean existsByEmail(Contact contact){
        return contactRepository.existsByEmail(contact.getEmail());
    }

    public boolean existsByPhone(Contact contact){
        return contactRepository.existsByPhone(contact.getPhone());
    }
}
