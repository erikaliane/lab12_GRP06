package com.tecsup.petclinic.services;
import java.util.List;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.exception.OwnerNotFoundException;


public interface OwnerService {
	
	/**
	 * 
	 * @param owner
	 * @return
	 */
	Owner create(Owner owner);
	
	/**
	 * 
	 * @param owner
	 * @return
	 */
	Owner update(Owner owner);
	
	/**
	 * 
	 * @param id
	 * @throws OwnerNotFoundException
	 */
	void delete(Long id) throws OwnerNotFoundException;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Owner findById(long id) throws OwnerNotFoundException;
	

	/**
	 * 
	 * @param name
	 * @return
	 */
	List<Owner> findByName(String first_name);
	
	/**
	 * 
	 * @param last_name
	 * @return
	 */
	List<Owner> findOwnerLastName(String last_name);
	

	/**
	 * 
	 * @param ownerId
	 * @return
	 */
	List<Owner> findOwnerTelephone(String telephone);
	
	/**
	 * 
	 * @return
	 */
	Iterable<Owner> findAll();
}
