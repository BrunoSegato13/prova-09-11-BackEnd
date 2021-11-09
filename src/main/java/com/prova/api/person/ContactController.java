package com.prova.api.person;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public ResponseEntity<List<Contact>> index(){
        return ResponseEntity.ok(contactService.index());
    }

    @GetMapping("{id}")
    public ResponseEntity<Contact> findById(@PathVariable Long id){
        return ResponseEntity.ok(contactService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Contact> create(@RequestBody Contact contact){
        Contact savedContact = contactService.create(contact);
        return ResponseEntity.created(URI.create("/contact/" + contact.getId())).body(savedContact);
    }

    @PutMapping
    public ResponseEntity<Contact> update(@RequestBody Contact contact){
        return ResponseEntity.ok(contactService.update(contact));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        contactService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
