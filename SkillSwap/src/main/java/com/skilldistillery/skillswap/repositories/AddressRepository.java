package com.skilldistillery.skillswap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.skillswap.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

		Address findByUsers_UsernameLike(String username);
}
