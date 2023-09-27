package br.com.erudio;

import br.com.erudio.model.Person;
import br.com.erudio.service.IPersonService;
import br.com.erudio.service.PersonService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonServiceTest {

    @Test
    @DisplayName("When Create a Person with Success Should Return a Person Object")
    void testCreatePerson_WhenSuccess_ShouldReturnPersonObject() {

        // Given / Arrange
        IPersonService service = new PersonService();

        Person person = new Person(
                "Keith",
                "Moon",
                "kmoon@erudio.com.br",
                "Wembley - UK",
                "Male"
        );

        // When / Act
        Person actual = service.createPerson(person);

        // Then / Assert
        assertNotNull(actual, () -> "The createPerson() should not have returned null!");
    }
}
