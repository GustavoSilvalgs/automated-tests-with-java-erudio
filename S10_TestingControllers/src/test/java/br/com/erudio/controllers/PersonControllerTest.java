package br.com.erudio.controllers;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.services.PersonServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private PersonServices service;

	private Person updatedPerson;

	@BeforeEach
	public void setup() {
		// Given / Arrange
		updatedPerson = new Person(
				"Gustavo",
				"Silva",
				"gustavo@gustavo.com",
				"Mogi das Cruzes - São Paulo - Brasil",
				"male");
	}

	@Test
	@DisplayName("JUnit test for Given Person Object When Create Person then Return Saved Person")
	void testGivenPersonObject_WhenCreatePerson_thenReturnSavedPerson() throws Exception {
		// Given / Arrange
		given(service.create(any(Person.class)))
				.willAnswer((invocation) -> invocation.getArgument(0));

		// When / Act
		ResultActions response = mockMvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(updatedPerson)));

		// Then / Assert
		response.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", is(updatedPerson.getFirstName())))
				.andExpect(jsonPath("$.lastName", is(updatedPerson.getLastName())))
				.andExpect(jsonPath("$.email", is(updatedPerson.getEmail())));
	}

	@Test
	@DisplayName("JUnit test for Given List Of People When findAll People then Return People List")
	void testGivenListOfPeople_WhenFindAllPeople_thenReturnPeopleList() throws Exception {
		// Given / Arrange
		List<Person> people = new ArrayList<>();
		people.add(updatedPerson);
		people.add(new Person (
				"Luís",
				"Silva",
				"luis@gustavo.com",
				"Mogi das Cruzes - São Paulo - Brasil",
				"male"));

		given(service.findAll()).willReturn(people);

		// When / Act
		ResultActions response = mockMvc.perform(get("/person"));

		// Then / Assert
		response.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.size()", is(people.size())));
	}

	@Test
	@DisplayName("JUnit test for Given PersonId When FindById then Return Person Object")
	void testGivenPersonId_WhenFindById_thenReturnPersonObject() throws Exception {
		// Given / Arrange
		long personId = 1L;
		given(service.findById(personId)).willReturn(updatedPerson);

		// When / Act
		ResultActions response = mockMvc.perform(get("/person/{id}", personId));

		// Then / Assert
		response.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.firstName", is(updatedPerson.getFirstName())))
				.andExpect(jsonPath("$.lastName", is(updatedPerson.getLastName())))
				.andExpect(jsonPath("$.email", is(updatedPerson.getEmail())));
	}

	@Test
	@DisplayName("JUnit test for Given Invalid PersonId When FindById then Return Not Found")
	void testGivenInvalidPersonId_WhenFindById_thenReturnNotFound() throws Exception {
		// Given / Arrange
		long personId = 1L;
		given(service.findById(personId)).willThrow(ResourceNotFoundException.class);

		// When / Act
		ResultActions response = mockMvc.perform(get("/person/{id}", personId));

		// Then / Assert
		response.andExpect(status().isNotFound()).andDo(print());
	}

	@Test
	@DisplayName("JUnit test for Given Update Person When Update then Return Update Person Object")
	void testGivenUpdatePerson_WhenUpdate_thenUpdatePersonObject() throws Exception {
		// Given / Arrange
		long personId = 1L;
		given(service.update(any(Person.class)))
				.willAnswer((invocation) -> invocation.getArgument(0));

		// When / Act
		Person updatedPerson = new Person(
				"Luís",
				"Silva",
				"luis@gustavo.com",
				"Mogi das Cruzes - São Paulo - Brasil",
				"male");

		ResultActions response = mockMvc.perform(put("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(updatedPerson)));

		// Then / Assert
		response.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.firstName", is(updatedPerson.getFirstName())))
				.andExpect(jsonPath("$.lastName", is(updatedPerson.getLastName())))
				.andExpect(jsonPath("$.email", is(updatedPerson.getEmail())));
	}

	@Test
	@DisplayName("JUnit test for Given Nonexistent Person When Update then Return Not Found")
	void testGivenNonexistentPerson_WhenUpdate_thenReturnNotFound() throws Exception {
		// Given / Arrange
		long personId = 1L;
		given(service.findById(personId)).willThrow(ResourceNotFoundException.class);
		given(service.update(any(Person.class)))
				.willAnswer((invocation) -> invocation.getArgument(1));

		// When / Act
		Person updatedPerson = new Person(
				"Luís",
				"Silva",
				"luis@gustavo.com",
				"Mogi das Cruzes - São Paulo - Brasil",
				"male");

		ResultActions response = mockMvc.perform(put("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(updatedPerson)));

		// Then / Assert
		response.andExpect(status().isNotFound())
				.andDo(print());
	}

	@Test
	@DisplayName("JUnit test for Given PersonId When Delete then Return NoContent")
	void testGivenPersonId_WhenDelete_thenReturnNoContent() throws Exception {
		// Given / Arrange
		long personId = 1L;
		willDoNothing().given(service).delete(personId);

		// When / Act
		ResultActions response = mockMvc.perform(delete("/person/{id}", personId));

		// Then / Assert
		response.andExpect(status().isNoContent())
				.andDo(print());
	}
}