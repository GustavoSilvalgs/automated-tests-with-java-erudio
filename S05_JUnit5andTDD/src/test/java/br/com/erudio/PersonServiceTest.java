package br.com.erudio;

import br.com.erudio.model.Person;
import br.com.erudio.service.IPersonService;
import br.com.erudio.service.PersonService;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonServiceTest {

    Person person;
    IPersonService service;

    @BeforeEach
    void setup() {
        service = new PersonService();
        person = new Person(
                "Keith",
                "Moon",
                "Wembley - UK",
                "Male",
                "kmoon@erudio.com.br"
                );
    }

    @Test
    @DisplayName("When Create a Person with Success Should Return a Person Object")
    void testCreatePerson_WhenSuccess_ShouldReturnPersonObject() {

        // Given / Arrange

        // When / Act
        Person actual = service.createPerson(person);

        // Then / Assert
        assertNotNull(actual, () -> "The createPerson() should not have returned null!");
    }

    @Test
    @DisplayName("When Create a Person with Success Should Contains Valid Fields in Returned Person Object")
    void testCreatePerson_WhenSuccess_ShouldContainsValidFieldsInReturnedPersonObject() {

        // Given / Arrange

        // When / Act
        Person actual = service.createPerson(person);

        // Then / Assert
        assertNotNull(person.getId(), () -> "Person ID is Missing");
        assertEquals(person.getFirstName(), actual.getFirstName(), () -> "The FirstName is Incorrect!");
        assertEquals(person.getLastName(), actual.getLastName(), () -> "The LastName is Incorrect!");
        assertEquals(person.getAddress(), actual.getAddress(), () -> "The Address is Incorrect!");
        assertEquals(person.getGender(), actual.getGender(), () -> "The Gender is Incorrect!");
        assertEquals(person.getEmail(), actual.getEmail(), () -> "The Email is Incorrect!");
    }

    @Test
    @DisplayName("When Create a Person with null e-mail Should throw exception")
    void testCreatePerson_WithNullEmail_ShouldThrowIllegalArgumentException() {

        // Given / Arrange
        person.setEmail(null);

        var expectedMessage = "The Person e-mail is null or empty!";

        // When / Act & Then / Assert
        IllegalArgumentException actual = assertThrows(
                IllegalArgumentException.class, () ->
                service.createPerson(person),
                () -> "Empty e-mail should have cause an IllegalArgumentException");

        // Then / Assert
        assertEquals(expectedMessage, actual.getMessage(), () -> "Exception error message is incorrect!");
    }
}
