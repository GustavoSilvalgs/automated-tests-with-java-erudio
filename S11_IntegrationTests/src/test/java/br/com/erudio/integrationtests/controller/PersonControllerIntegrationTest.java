package br.com.erudio.integrationtests.controller;

import br.com.erudio.configs.TestConfigs;
import br.com.erudio.integrationtests.testcontainers.AbstractIntegrationTest;
import br.com.erudio.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PersonControllerIntegrationTest extends AbstractIntegrationTest {

	private static RequestSpecification specification;
	private static ObjectMapper objectMapper;
	private static Person person;

	@BeforeAll
	public static void setup() {
		// Given / Arrange
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		specification = new RequestSpecBuilder()
				.setBasePath("/person")
				.setPort(TestConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();

		person = new Person(
				"Gustavo",
				"Silva",
				"gustavo@gustavo.com",
				"Mogi das Cruzes - São Paulo - Brasil",
				"male");
	}

	@Test
	@Order(1)
	@DisplayName("JUnit integration Given Person Object When Create One Person Should Return A Person Object")
	void integrationTestGiverPersonObject_When_CreateOnePerson_ShouldReturnAPersonObject() throws JsonProcessingException {

		var content = given().spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
				.body(person)
				.when()
				.post()
				.then()
				.statusCode(200)
				.extract()
				.body()
				.asString();

		Person createdPerson = objectMapper.readValue(content, Person.class);

		person = createdPerson;

		assertNotNull(createdPerson);

		assertNotNull(createdPerson.getId());
		assertNotNull(createdPerson.getFirstName());
		assertNotNull(createdPerson.getLastName());
		assertNotNull(createdPerson.getAddress());
		assertNotNull(createdPerson.getGender());
		assertNotNull(createdPerson.getEmail());

		assertTrue(createdPerson.getId() > 0);
		assertEquals("Gustavo" ,createdPerson.getFirstName());
		assertEquals("Silva", createdPerson.getLastName());
		assertEquals("Mogi das Cruzes - São Paulo - Brasil", createdPerson.getAddress());
		assertEquals("male", createdPerson.getGender());
		assertEquals("gustavo@gustavo.com", createdPerson.getEmail());
	}

	@Test
	@Order(2)
	@DisplayName("JUnit integration Given Person Object When Update One Person Should Return A Updated Person Object")
	void integrationTestGiverPersonObject_When_UpdateOnePerson_ShouldReturnAUpdatedPersonObject() throws JsonProcessingException {

		person.setFirstName("Luís");
		person.setEmail("luis@gustavo.com");

		var content = given().spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
				.body(person)
				.when()
				.put()
				.then()
				.statusCode(200)
				.extract()
				.body()
				.asString();

		Person updatedPerson = objectMapper.readValue(content, Person.class);

		person = updatedPerson;

		assertNotNull(updatedPerson);

		assertNotNull(updatedPerson.getId());
		assertNotNull(updatedPerson.getFirstName());
		assertNotNull(updatedPerson.getLastName());
		assertNotNull(updatedPerson.getAddress());
		assertNotNull(updatedPerson.getGender());
		assertNotNull(updatedPerson.getEmail());

		assertTrue(updatedPerson.getId() > 0);
		assertEquals("Luís" ,updatedPerson.getFirstName());
		assertEquals("Silva", updatedPerson.getLastName());
		assertEquals("Mogi das Cruzes - São Paulo - Brasil", updatedPerson.getAddress());
		assertEquals("male", updatedPerson.getGender());
		assertEquals("luis@gustavo.com", updatedPerson.getEmail());
	}

	@Test
	@Order(3)
	@DisplayName("JUnit integration Given Person Object When findById Should Return A Person Object")
	void integrationTestGiverPersonObject_When_findById_ShouldReturnAPersonObject() throws JsonProcessingException {

		var content = given().spec(specification)
				.pathParam("id", person.getId())
				.when()
				.get("{id}")
				.then()
				.statusCode(200)
				.extract()
				.body()
				.asString();

		Person foundPerson = objectMapper.readValue(content, Person.class);

		assertNotNull(foundPerson);

		assertNotNull(foundPerson.getId());
		assertNotNull(foundPerson.getFirstName());
		assertNotNull(foundPerson.getLastName());
		assertNotNull(foundPerson.getAddress());
		assertNotNull(foundPerson.getGender());
		assertNotNull(foundPerson.getEmail());

		assertTrue(foundPerson.getId() > 0);
		assertEquals("Luís" ,foundPerson.getFirstName());
		assertEquals("Silva", foundPerson.getLastName());
		assertEquals("Mogi das Cruzes - São Paulo - Brasil", foundPerson.getAddress());
		assertEquals("male", foundPerson.getGender());
		assertEquals("luis@gustavo.com", foundPerson.getEmail());
	}
}