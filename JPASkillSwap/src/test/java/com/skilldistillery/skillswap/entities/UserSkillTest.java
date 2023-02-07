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

class UserSkillTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private UserSkill userSkill;

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
		userSkill = em.find(UserSkill.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		userSkill = null;
	}

	@Test
	void test_Address_entity_mapping() {
		assertNotNull(userSkill);
		assertEquals(4, userSkill.getSkillId() );
	}

}
