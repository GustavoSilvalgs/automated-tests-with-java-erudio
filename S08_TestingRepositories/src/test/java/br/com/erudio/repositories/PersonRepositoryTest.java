package br.com.erudio.repositories;

import br.com.erudio.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

    @DisplayName("Given Person Object When Save then Return Saved Person")
    @Test
    void testGivenPersonObject_WhenSave_thenReturnSavedPerson() {

        // Given / Arrange
        Person person0 = new Person("Gustavo",
                "Silva",
                "gustavo@gustavo.com",
                "Mogi das Cruzes - São Paulo - Brasil",
                "male");

        // When / Act
        Person savedPerson = repository.save(person0);

        // Then / Assert
        assertNotNull(savedPerson);
        assertTrue(savedPerson.getId() > 0);
    }

    @DisplayName("Given Person List When FindAll then Return Person List")
    @Test
    void testGivenPersonList_WhenFindAll_thenReturnPersonList() {

        // Given / Arrange
        Person person0 = new Person("Gustavo",
                "Silva",
                "gustavo@gustavo.com",
                "Mogi das Cruzes - São Paulo - Brasil",
                "male");
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

    @DisplayName("Given Person Object When FindById then Return Person Object")
    @Test
    void testGivenPersonObject_WhenFindById_thenReturnPersonObject() {

        // Given / Arrange
        Person person0 = new Person("Gustavo",
                "Silva",
                "gustavo@gustavo.com",
                "Mogi das Cruzes - São Paulo - Brasil",
                "male");

        repository.save(person0);

        // When / Act
        Person savedPerson = repository.findById(person0.getId()).get();

                // Then / Assert
        assertNotNull(savedPerson);
        assertEquals(person0.getId(), savedPerson.getId());
    }
}
