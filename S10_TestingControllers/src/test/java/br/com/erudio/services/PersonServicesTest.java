package br.com.erudio.services;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServicesTest {

	@Mock
	private PersonRepository repository;

	@InjectMocks
	private PersonServices service;

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

	@DisplayName("JUnit test for Given Person Object When Save Person then Return Person Object")
	@Test
	void testGivenPersonObject_WhenSavePerson_thenReturnPersonObject() {
	    // Given / Arrange
		given(repository.findByEmail(anyString())).willReturn(Optional.empty());
		given(repository.save(person0)).willReturn(person0);

	    // When / Act
		Person savedPerson = service.create(person0);

	    // Then / Assert
		assertNotNull(savedPerson);
		assertEquals("Gustavo", savedPerson.getFirstName());

	}

	@DisplayName("JUnit test for Given Existing Email When Save Person then Throws Exception")
	@Test
	void testGivenExistingEmail_WhenSavePerson_thenThrowsException() {
	    // Given / Arrange
		given(repository.findByEmail(anyString())).willReturn(Optional.of(person0));

	    // When / Act
		assertThrows(ResourceNotFoundException.class, () -> {
			service.create(person0);
		});

	    // Then / Assert
		verify(repository, never()).save(any(Person.class));
	}

	@DisplayName("JUnit test for Given People List When FindAll People then Return People List")
	@Test
	void testGivenPeopleList_WhenFindAllPeople_thenReturnPeopleList() {
	    // Given / Arrange
		Person person1 = new Person("Luís",
				"Silva",
				"luis@gustavo.com",
				"Mogi das Cruzes - São Paulo - Brasil",
				"male");

		given(repository.findAll()).willReturn(List.of(person0, person1));

	    // When / Act
		List<Person> peopleList = service.findAll();

	    // Then / Assert
		assertNotNull(peopleList);
		assertEquals(2, peopleList.size());
	}

	@DisplayName("JUnit test for Given Empty People List When FindAll People then Return People List")
	@Test
	void testGivenEmptyPeopleList_WhenFindAllPeople_thenReturnEmptyPeopleList() {
	    // Given / Arrange
		given(repository.findAll()).willReturn(Collections.emptyList());

	    // When / Act
		List<Person> peopleList = service.findAll();

	    // Then / Assert
		assertTrue(peopleList.isEmpty());
		assertEquals(0, peopleList.size());
	}

	@DisplayName("JUnit test for Given PersonId When FindById When Save Person then Return Person Object")
	@Test
	void testGivenPersonId_WhenFindById_thenReturnPersonObject() {
		// Given / Arrange
		given(repository.findById(anyLong())).willReturn(Optional.of(person0));

		// When / Act
		Person savedPerson = service.findById(1L);

		// Then / Assert
		assertNotNull(savedPerson);
		assertEquals("Gustavo", savedPerson.getFirstName());
	}

	@DisplayName("JUnit test for Given Person Object When Update Person then Return Updated Person Object")
	@Test
	void testGivenPersonObject_WhenUpdatePerson_thenReturnUpdatedPersonObject() {
		// Given / Arrange
		person0.setId(1L);
		given(repository.findById(anyLong())).willReturn(Optional.of(person0));

		person0.setFirstName("Luís");
		person0.setEmail("luis@gustavo.com");

		given(repository.save(person0)).willReturn(person0);

		// When / Act
		Person updatedPerson = service.update(person0);

		// Then / Assert
		assertNotNull(updatedPerson);
		assertEquals("Luís", updatedPerson.getFirstName());
		assertEquals("luis@gustavo.com", updatedPerson.getEmail());
	}

	@DisplayName("JUnit test for Given PersonId When Delete Person then Do Nothing")
	@Test
	void testGivenPersonId_WhenDeletePerson_thenDoNothing() {
		// Given / Arrange
		person0.setId(1L);
		given(repository.findById(anyLong())).willReturn(Optional.of(person0));
		willDoNothing().given(repository).delete(person0);

		// When / Act
		service.delete(person0.getId());

		// Then / Assert
		verify(repository, times(1)).delete(person0);
	}

	@DisplayName("JUnit test for Given Non Existing Id When Delete Person then Throws Exception")
	@Test
	void testGivenNonExistingId_WhenDeletePerson_thenThrowsException() {
		// Given / Arrange
		given(repository.findById(anyLong())).willReturn(Optional.empty());

		// When / Act
		assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(5L);
		});

		// Then / Assert
		verify(repository, never()).deleteById(anyLong());
	}

}
