package com.skilldistillery.skillswap.services;

import com.skilldistillery.skillswap.entities.Address;
import com.skilldistillery.skillswap.entities.User;

public interface AddressService {

	//	Address findById(int addressId);
		Address createAddress(Address address);
		Address updateAddress(int addressId, Address address);
}
