package br.com.erudio;

import br.com.erudio.service.IPersonService;
import br.com.erudio.service.PersonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonServiceTest {

    @Test
    @DisplayName("When Create a Person with Success Should Return a Person Object")
    void testCreatePerson_WhenSuccess_ShouldReturnPersonObject() {

        // Given / Arrange
        IPersonService service = new PersonService();

        // When / Act

        // Then / Assert
    }
}
