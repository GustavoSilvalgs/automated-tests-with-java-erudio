package br.com.erudio.repositories;

import br.com.erudio.integrationtests.testcontainers.AbstractIntegrationTest;
import br.com.erudio.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PersonRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private PersonRepository repository;

    private Person person0;

    @BeforeEach
    public void setup() {
        // Given / Arrange
        person0 = new Person(
                "Gustavo",
                "Silva",
                "gustavo@gustavo.com",
                "Mogi das Cruzes - São Paulo - Brasil",
                "male");
    }

    @DisplayName("JUnit test for Given Person Object When Save then Return Saved Person")
    @Test
    void testGivenPersonObject_WhenSave_thenReturnSavedPerson() {

        // When / Act
        Person savedPerson = repository.save(person0);

        // Then / Assert
        assertNotNull(savedPerson);
        assertTrue(savedPerson.getId() > 0);
    }

    @DisplayName("JUnit test for Given Person List When FindAll then Return Person List")
    @Test
    void testGivenPersonList_WhenFindAll_thenReturnPersonList() {

        // Given / Arrange
        Person person1 = new Person("Luís",
                "Silva",
                "luis@gustavo.com",
                "Mogi das Cruzes - São Paulo - Brasil",
                "male");

        repository.save(person0);
        repository.save(person1);

        // When / Act
        List<Person> personList = repository.findAll();

        // Then / Assert
        assertNotNull(personList);
        assertEquals(2, personList.size());
    }

    @DisplayName("JUnit test for Given Person Object When FindById then Return Person Object")
    @Test
    void testGivenPersonObject_WhenFindById_thenReturnPersonObject() {

        // Given / Arrange
        repository.save(person0);

        // When / Act
        Person savedPerson = repository.findById(person0.getId()).get();

                // Then / Assert
        assertNotNull(savedPerson);
        assertEquals(person0.getId(), savedPerson.getId());
    }

    @DisplayName("JUnit test for Given Person Object When FindByEmail then Return Person Object")
    @Test
    void testGivenPersonObject_WhenFindByEmail_thenReturnPersonObject() {

        // Given / Arrange
        Person person0 = new Person("Gustavo",
                "Silva",
                "gustavo@gustavo.com",
                "Mogi das Cruzes - São Paulo - Brasil",
                "male");

        repository.save(person0);

        // When / Act
        Person savedPerson = repository.findByEmail(person0.getEmail()).get();

                // Then / Assert
        assertNotNull(savedPerson);
        assertEquals(person0.getId(), savedPerson.getId());
    }

    @DisplayName("JUnit test for Given Person Object When Update Person then Return Updated Person Object")
    @Test
    void testGivenPersonObject_WhenUpdatePerson_thenReturnUpdatedPersonObject() {

        // Given / Arrange
        repository.save(person0);

        // When / Act
        Person savedPerson = repository.findById(person0.getId()).get();
        savedPerson.setFirstName("Luís");
        savedPerson.setEmail("luis@gustavo.com");

        Person updatedPerson = repository.save(savedPerson);

        // Then / Assert
        assertNotNull(updatedPerson);
        assertEquals("Luís", updatedPerson.getFirstName());
        assertEquals("luis@gustavo.com", updatedPerson.getEmail());
    }

    @DisplayName("JUnit test for Given Person Object When Delete then Remove Person")
    @Test
    void testGivenPersonObject_WhenDelete_thenRemovePerson() {

        // Given / Arrange
        repository.save(person0);

        // When / Act
        repository.deleteById(person0.getId());

        Optional<Person> personOptional = repository.findById(person0.getId());

        // Then / Assert
        assertTrue(personOptional.isEmpty());
    }

    @DisplayName("JUnit test for Given FirstName and LastName When FindByJPQL then Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_WhenFindByJPQL_thenReturnPersonObject() {

        // Given / Arrange
        repository.save(person0);

        String firstName = "Gustavo";
        String lastName = "Silva";

        // When / Act
        Person savedPerson = repository.findByJPQL(firstName, lastName);

        // Then / Assert
        assertNotNull(savedPerson);
        assertEquals(firstName, savedPerson.getFirstName());
        assertEquals(lastName, savedPerson.getLastName());
    }

    @DisplayName("JUnit test for Given FirstName and LastName When FindByJPQLNamedParameters then Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_WhenFindByJPQLNamedParameters_thenReturnPersonObject() {

        // Given / Arrange
        repository.save(person0);

        String firstName = "Gustavo";
        String lastName = "Silva";

        // When / Act
        Person savedPerson = repository.findByJPQLNamedParameters(firstName, lastName);

        // Then / Assert
        assertNotNull(savedPerson);
        assertEquals(firstName, savedPerson.getFirstName());
        assertEquals(lastName, savedPerson.getLastName());
    }

    @DisplayName("JUnit test for Given FirstName and LastName When FindByNativeSQL then Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_WhenFindByJPQLNativeSQL_thenReturnPersonObject() {

        // Given / Arrange
        repository.save(person0);

        String firstName = "Gustavo";
        String lastName = "Silva";

        // When / Act
        Person savedPerson = repository.findByNativeSQL(firstName, lastName);

        // Then / Assert
        assertNotNull(savedPerson);
        assertEquals(firstName, savedPerson.getFirstName());
        assertEquals(lastName, savedPerson.getLastName());
    }

    @DisplayName("JUnit test for Given FirstName and LastName When FindByNativeSQLWithNamedParameters then Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_WhenFindByJPQLNativeSQLWithNamedParameters_thenReturnPersonObject() {

        // Given / Arrange
        repository.save(person0);

        String firstName = "Gustavo";
        String lastName = "Silva";

        // When / Act
        Person savedPerson = repository.findByNativeSQLWithNamedParameters(firstName, lastName);

        // Then / Assert
        assertNotNull(savedPerson);
        assertEquals(firstName, savedPerson.getFirstName());
        assertEquals(lastName, savedPerson.getLastName());
    }
}
