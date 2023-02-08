package com.skilldistillery.skillswap.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;
	
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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	void test_User_entity_mapping() {
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
	}
	@Test
	void test_User_Project_one_to_many_mapping() {
		assertNotNull(user);
		assertTrue(user.getProjects().size() > 0);
	}
	@Test
	void test_User_Comment_one_to_many_mapping() {
		assertNotNull(user);
		assertTrue(user.getComments().size() > 0);
	}
	@Test
	void test_User_Following_many_to_many_mapping() {
		assertNotNull(user);
		assertTrue(user.getFollowing().size() > 0);
	}
//	@Test
//	void test_User_FollowedBy_many_to_many_mapping() {
//		assertNotNull(user);
//		assertTrue(user.getFollowedBy().size() > 0);
//	}
//	@Test
//	void test_User_Address_one_to_one_mapping() {
//		assertNotNull(user);
//		assertNull(user.getAddress().getCity());
//	}
//	@Test
//	void test_User_UserSkill_one_to_many_mapping() {
//		assertNotNull(user);
//		assertEquals(1, user.);
//
//	}
	

}
