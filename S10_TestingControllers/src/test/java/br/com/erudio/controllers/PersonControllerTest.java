package br.com.erudio.controllers;

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
import static org.mockito.BDDMockito.given;
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

	private Person person;

	@BeforeEach
	public void setup() {
		// Given / Arrange
		person = new Person(
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
				.content(mapper.writeValueAsString(person)));

		// Then / Assert
		response.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", is(person.getFirstName())))
				.andExpect(jsonPath("$.lastName", is(person.getLastName())))
				.andExpect(jsonPath("$.email", is(person.getEmail())));
	}

	@Test
	@DisplayName("JUnit test for Given List Of People When findAll People then Return People List")
	void testGivenListOfPeople_WhenFindAllPeople_thenReturnPeopleList() throws Exception {
		// Given / Arrange
		List<Person> people = new ArrayList<>();
		people.add(person);
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
		given(service.findById(personId)).willReturn(person);

		// When / Act
		ResultActions response = mockMvc.perform(get("/person/{id}", personId));

		// Then / Assert
		response.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.firstName", is(person.getFirstName())))
				.andExpect(jsonPath("$.lastName", is(person.getLastName())))
				.andExpect(jsonPath("$.email", is(person.getEmail())));
	}
}