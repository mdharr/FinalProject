package com.skilldistillery.skillswap.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.skillswap.entities.Address;
import com.skilldistillery.skillswap.repositories.AddressRepository;


@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepo;
	
	
	@Override
	public Address createAddress(Address address) {
		return addressRepo.saveAndFlush(address);
	}

	@Override
	public Address updateAddress(int addressId, Address address) {
		Optional<Address> addressOpt = addressRepo.findById(addressId);
		if (addressOpt.isPresent()) {
			address = addressOpt.get();
			address.setStreet(address.getStreet());
			address.setCity(address.getState());
			address.setState(address.getState());
			address.setZip(address.getZip());
		}
		return address;
	}



}
