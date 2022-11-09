package com.tecsup.petclinic.services;

import java.util.List;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsup.petclinic.entities.Owner;

import com.tecsup.petclinic.exception.OwnerNotFoundException;

import com.tecsup.petclinic.repositories.OwnerRepository;

@Service
public class OwnerServiceImpl implements OwnerService{
	
	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceImpl.class);

	@Autowired
	OwnerRepository ownerRepository;
	/**
	 * 
	 * @param owner
	 * @return
	 */
	@Override
	public Owner create(Owner owner) {
		return ownerRepository.save(owner);
	}
	/**
	 * 
	 * @param pet
	 * @return
	 */
	@Override
	public Owner update(Owner owner) {
		return ownerRepository.save(owner);
	}
	/**
	 * 
	 * @param id
	 * @throws OwnerNotFoundException
	 */
	@Override
	public void delete(Long id) throws OwnerNotFoundException {
		
		Owner owner = findById(id);
		ownerRepository.delete(owner);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Owner findById(long id) throws OwnerNotFoundException {

		Optional<Owner> owner = ownerRepository.findById(id);

		if ( !owner.isPresent())
			throw new OwnerNotFoundException("Record not found...!");
			
		return owner.get();
	}


	/**
	 * 
	 * @param first_name
	 * @return
	 */
	@Override
	public List<Owner> findByName(String first_name) {
		List<Owner> owners = ownerRepository.findByName(first_name);

		owners.stream().forEach(owner -> logger.info("" + owner));

		return owners;
	}
	/**
	 * 
	 * @param last_name
	 * @return
	 */
	@Override
	public List<Owner> findOwnerLastName(String last_name) {
		List<Owner> owners = ownerRepository.findOwnerLastName(last_name);

		owners.stream().forEach(owner -> logger.info("" + owner));

		return owners;
	}
	/**
	 * 
	 * @param telephone
	 * @return
	 */
	@Override
	public List<Owner> findOwnerTelephone(String telephone) {
		List<Owner> owners = ownerRepository.findOwnerTelephone(telephone);

		owners.stream().forEach(owner -> logger.info("" + owner));

		return owners;
	}

	@Override
	public Iterable<Owner> findAll() {
		// TODO Auto-generated 
	return ownerRepository.findAll();
	}

}
