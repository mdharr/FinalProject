package com.skilldistillery.skillswap.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SkillTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Skill skill;
	private Skill skill2;
	
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
		skill = em.find(Skill.class, 1);
		skill2 = em.find(Skill.class, 2);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		skill = null;
	}

	@Test
	void test_Skill_entity_mapping() {
		assertNotNull(skill);
		assertEquals("Woodworking", skill.getName());
	}
	@Test
	void test_Skill_description() {
		assertNotNull(skill);
		assertTrue(skill.getUsers().size() > 0);
	}
	
	@Test
	void test_Skill_Mapping_ManyToMany_Project() {
		assertNotNull(skill);
		assertTrue(skill2.getProjects().size() > 0);
	}

}
