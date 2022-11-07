package com.bell.codingchallenge.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.bell.codingchallenge.model.Genres;
import com.bell.codingchallenge.model.Items;
import com.bell.codingchallenge.model.ItemsDetails;
import com.bell.codingchallenge.model.ItemsType;
import com.bell.codingchallenge.model.MediaImages;
import com.bell.codingchallenge.service.impl.ItemsServiceImpl;
import com.bell.codingchallenge.utils.BellVariableUtil;

@ExtendWith(MockitoExtension.class)
public class ItemsServiceImplTest {
	private static final Integer TOP_VALUE = 1;
	private static final Integer MEDIA_ID = 1;

	@InjectMocks
	private ItemsServiceImpl itemsServiceImpl;

	@Test
	public void testGetMediaList() {
		Items items = new Items(MEDIA_ID, "Bosch");
		List<Items> listOfItems = new ArrayList<Items>();
		listOfItems.add(items);
		ItemsType itemsType = new ItemsType(listOfItems, "Media");

		ResponseEntity<ItemsType> responseMock = new ResponseEntity<ItemsType>(itemsType, HttpStatus.OK);
		RestTemplate restTemplate = mock(RestTemplate.class);

		when(restTemplate.getForEntity(BellVariableUtil.BELL_API_FOR_LIST_OF_MEDIA + TOP_VALUE, ItemsType.class))
				.thenReturn(responseMock);

		ResponseEntity<ItemsType> listOfMediaList = restTemplate
				.getForEntity(BellVariableUtil.BELL_API_FOR_LIST_OF_MEDIA + TOP_VALUE, ItemsType.class);

		assertEquals(HttpStatus.OK, listOfMediaList.getStatusCode());
		assertNotNull(listOfMediaList.getBody());

	}

	@Test
	@DisplayName("Scenario When API return empty list")
	public void testGetMediaListIsEmpty() {

		ResponseEntity<ItemsType> responseMock = new ResponseEntity<ItemsType>(new ItemsType(), HttpStatus.NO_CONTENT);
		RestTemplate restTemplate = mock(RestTemplate.class);

		when(restTemplate.getForEntity(BellVariableUtil.BELL_API_FOR_LIST_OF_MEDIA + TOP_VALUE, ItemsType.class))
				.thenReturn(responseMock);
		ResponseEntity<ItemsType> listOfMediaList = restTemplate
				.getForEntity(BellVariableUtil.BELL_API_FOR_LIST_OF_MEDIA + TOP_VALUE, ItemsType.class);
		assertEquals(HttpStatus.NO_CONTENT, listOfMediaList.getStatusCode());
		assertNull(listOfMediaList.getBody().getItems());
	}

	@Test
	public void testGetMediaDetailsById() {
		ItemsDetails itemsDetails = new ItemsDetails(MEDIA_ID, "Robert", "RB", "Robert Bosh",
				new ArrayList<MediaImages>(), new ArrayList<Genres>());

		ResponseEntity<ItemsDetails> responseMock = new ResponseEntity<ItemsDetails>(itemsDetails, HttpStatus.OK);
		RestTemplate restTemplate = mock(RestTemplate.class);

		when(restTemplate.getForEntity(BellVariableUtil.BELL_API_FOR_MEDIA_DETAILS + MEDIA_ID, ItemsDetails.class))
				.thenReturn(responseMock);

		ResponseEntity<ItemsDetails> listOfMediaList = restTemplate
				.getForEntity(BellVariableUtil.BELL_API_FOR_MEDIA_DETAILS + MEDIA_ID, ItemsDetails.class);

		assertEquals(HttpStatus.OK, listOfMediaList.getStatusCode());
		assertNotNull(listOfMediaList.getBody());

	}

	@Test
	@DisplayName("Scenario When Media Details API return empty list")
	public void testGetMediaDetailsByIdIsEmpty() {

		ResponseEntity<ItemsDetails> responseMock = new ResponseEntity<ItemsDetails>(new ItemsDetails(),
				HttpStatus.NO_CONTENT);
		RestTemplate restTemplate = mock(RestTemplate.class);

		when(restTemplate.getForEntity(BellVariableUtil.BELL_API_FOR_MEDIA_DETAILS + MEDIA_ID, ItemsDetails.class))
				.thenReturn(responseMock);

		ResponseEntity<ItemsDetails> listOfMediaList = restTemplate
				.getForEntity(BellVariableUtil.BELL_API_FOR_MEDIA_DETAILS + MEDIA_ID, ItemsDetails.class);

		assertEquals(HttpStatus.NO_CONTENT, listOfMediaList.getStatusCode());
		assertNull(listOfMediaList.getBody().getId());
	}

	@Test
	public void testGetMediaDetailsByIdAndType() {
		ItemsDetails itemsDetails = new ItemsDetails(MEDIA_ID, "Robert", "RB", "Robert Bosh",
				new ArrayList<MediaImages>(), new ArrayList<Genres>());

		ResponseEntity<ItemsDetails> responseMock = new ResponseEntity<ItemsDetails>(itemsDetails, HttpStatus.OK);
		RestTemplate restTemplate = mock(RestTemplate.class);

		when(restTemplate.getForEntity(BellVariableUtil.BELL_API_FOR_MEDIA_DETAILS + MEDIA_ID, ItemsDetails.class))
				.thenReturn(responseMock);

		ResponseEntity<ItemsDetails> listOfMediaList = restTemplate
				.getForEntity(BellVariableUtil.BELL_API_FOR_MEDIA_DETAILS + MEDIA_ID, ItemsDetails.class);
		assertEquals(HttpStatus.OK, listOfMediaList.getStatusCode());
		assertNotNull(listOfMediaList.getBody());
		assertNotNull(listOfMediaList.getBody().getImages());

	}

	@Test
	@DisplayName("Scenario When Media Images is empty")
	public void testGetMediaDetailsByIdAndTypeImageIsEmpty() {
		ItemsDetails itemsDetails = new ItemsDetails(MEDIA_ID, "Robert", "RB", "Robert Bosh",
				new ArrayList<MediaImages>(), new ArrayList<Genres>());

		ResponseEntity<ItemsDetails> responseMock = new ResponseEntity<ItemsDetails>(itemsDetails, HttpStatus.NO_CONTENT);
		RestTemplate restTemplate = mock(RestTemplate.class);

		when(restTemplate.getForEntity(BellVariableUtil.BELL_API_FOR_MEDIA_DETAILS + MEDIA_ID, ItemsDetails.class))
				.thenReturn(responseMock);

		ResponseEntity<ItemsDetails> listOfMediaList = restTemplate
				.getForEntity(BellVariableUtil.BELL_API_FOR_MEDIA_DETAILS + MEDIA_ID, ItemsDetails.class);

		assertEquals(HttpStatus.NO_CONTENT, listOfMediaList.getStatusCode());
		assertNotNull(listOfMediaList.getBody());
		assertTrue(listOfMediaList.getBody().getImages().isEmpty());

	}
}
