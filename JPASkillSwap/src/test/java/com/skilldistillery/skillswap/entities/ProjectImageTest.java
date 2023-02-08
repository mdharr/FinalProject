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

class ProjectImageTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private ProjectImage projectImage;
	
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
		projectImage = em.find(ProjectImage.class, 2);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		projectImage = null;
	}

	@Test
	void test_project_entity_mapping() {
		assertNotNull(projectImage);
		assertEquals("caption", projectImage.getCaption());
		
	}
	
	@Test
	void test_mapping_ManyToOne_Mapping_To_Projects() {
	assertNotNull(projectImage);
	assertEquals(2, projectImage.getProject().getId());
}
}
