package com.prova.api.contact;

import com.prova.api.exceptions.Exceptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @InjectMocks
    private ContactService contactService;
    @Mock
    private ContactRepository contactRepository;

    private Contact contact() {
        return Contact.builder()
                .id(1L)
                .name("Teste")
                .email("teste@teste.com")
                .phone("4500000000")
                .build();
    }

    @Test
    void given_EmailThatAlreadExists_ShouldThrowsException() throws Exception {
        when(contactRepository.existsByEmail(contact().getEmail())).thenReturn(true);

        assertThrows(Exceptions.class, () -> contactService.create(contact()));
    }

    @Test
    void given_PhoneThatAlreadExists_ShouldThrowsException() throws Exception {
        when(contactRepository.existsByPhone(contact().getPhone())).thenReturn(true);

        assertThrows(Exceptions.class, () -> contactService.create(contact()));
    }

    @Test
    void given_ValidEmailAndPhone_ShouldSaveContact() throws Exception {
        when(contactRepository.existsByEmail(contact().getEmail())).thenReturn(false);
        when(contactRepository.existsByPhone(contact().getPhone())).thenReturn(false);

        contactService.create(contact());
        verify(contactRepository, times(1)).save(contact());
    }

}