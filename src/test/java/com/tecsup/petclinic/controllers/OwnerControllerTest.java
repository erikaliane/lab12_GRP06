package com.tecsup.petclinic.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.dto.OwnerDTO;


/**
 * 
 */
@AutoConfigureMockMvc
@SpringBootTest
class OwnerControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(OwnerControllerTest.class);

private static final ObjectMapper om = new ObjectMapper();

@Autowired
private MockMvc mockMvc;

@Test
public void testFindAllOwners() throws Exception {
	int ID_FIRST = 1;


	this.mockMvc.perform(get("/owners"))
				.andExpect(status().isOk()) // HTTP 200
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].id", is(ID_FIRST)));
}

/**
 * 
 * @throws Exception
 * 
 */
@Test
public void testFindOwnerOK() throws Exception {

	int ID_SEARCH = 1;
	String NAME_OWNER = "George";
	String last_name = "Franklin";
	String address= "110 W. Liberty St.";
	String city = "Madison";
	String telephone ="6085551023";

	mockMvc.perform(get("/owners/" + ID_SEARCH))  // Finding object with ID = 1
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.first_name", is(NAME_OWNER)))
			.andExpect(jsonPath("$.last_name", is(last_name)))
			.andExpect(jsonPath("$.address", is(address)))
			.andExpect(jsonPath("$.city", is(city)))
			.andExpect(jsonPath("$.telephone", is(telephone)));
}

/**
 * 
 * @throws Exception
 */
@Test
public void testFindOwnerKO() throws Exception {

	int ID_SEARCH = 666;

	mockMvc.perform(get("/owners/" + ID_SEARCH))
			.andExpect(status().isNotFound());
}

/**
 * @throws Exception
 */

@Test
public void testCreateOwner() throws Exception {
	String NAME_OWNER = "Geor";
	String last_name = "Frank";
	String address= "110 W. Liberty";
	String city = "Mad";
	String telephone ="6085551745";
	
	OwnerDTO newOwner = new OwnerDTO(NAME_OWNER, last_name, address, city,telephone);
    
	logger.info(newOwner.toString());
	logger.info(om.writeValueAsString(newOwner));
    
    mockMvc.perform(post("/pets")
            .content(om.writeValueAsString(newOwner))
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isCreated())
			.andExpect(jsonPath("$.first_name", is(NAME_OWNER)))
			.andExpect(jsonPath("$.last_name", is(last_name)))
			.andExpect(jsonPath("$.address", is(address)))
			.andExpect(jsonPath("$.city", is(city)))
			.andExpect(jsonPath("$.telephone", is(telephone)));
}


/**
 * 
 * @throws Exception
 */
@Test
public void testDeleteOwner() throws Exception {
	String NAME_OWNER = "Geo";
	String last_name = "Fran";
	String address= "110 W. Libert";
	String city = "Madh";
	String telephone ="6085551748";
	
	OwnerDTO newOwner = new OwnerDTO(NAME_OWNER, last_name, address, city,telephone);
	
	ResultActions mvcActions = mockMvc.perform(post("/owners")
            .content(om.writeValueAsString(newOwner))
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isCreated());
            
	String response = mvcActions.andReturn().getResponse().getContentAsString();

	Integer id = JsonPath.parse(response).read("$.id");

    mockMvc.perform(delete("/owners/" + id ))
             /*.andDo(print())*/
            .andExpect(status().isOk());
}
}













