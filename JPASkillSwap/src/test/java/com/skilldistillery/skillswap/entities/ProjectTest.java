package com.skilldistillery.skillswap.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProjectTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Project project;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPASkillSwap");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		project = em.find(Project.class, 2);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		project = null;
	}

	@Test
	void test_project_entity_mapping() {
		assertNotNull(project);
		assertEquals("Want to get better at italian", project.getName());

	}

	@Test
	void test_Project_User_many_to_many_mapping() {
		assertNotNull(project);
//		assertTrue(project.getUsers().size() > 0);
		assertEquals(2, project.getUser().getId());
	}

	@Test
	void test_Project_Comment_One_to_many_mapping() {
		assertNotNull(project);
		assertTrue(project.getComments().size() > 0);
	}

	@Test
	void test_mapping_OneToMany_Mapping_To_Images() {
	assertNotNull(project);
	assertTrue(project.getProjectImages().size() > 0);
	}

}
