package com.bell.codingchallenge.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bell.codingchallenge.payload.ResponseWrapperDTO;
import com.bell.codingchallenge.service.ItemsService;
import com.bell.codingchallenge.utils.MediaRequestBody;

@ExtendWith(MockitoExtension.class)
public class ItemsControllerTest {

	private static final Integer TOP_VALUE = 1;
	private static final Integer MEDIA_ID = 1;
	private static final String IMAGE_TYPE = "someImageType";

	@Mock
	private ItemsService itemsService;

	@InjectMocks
	private ItemsController itemsController;

	@Test
	public void testGetMediaList() {
		ResponseWrapperDTO responseMock = mock(ResponseWrapperDTO.class);
		when(responseMock.getStatus()).thenReturn(HttpStatus.OK);
		when(itemsService.getMediaList(TOP_VALUE)).thenReturn(responseMock);
		ResponseEntity<ResponseWrapperDTO> responseActual = itemsController.getMediaList(TOP_VALUE);
		assertEquals(HttpStatus.OK, responseActual.getStatusCode());
		verify(itemsService).getMediaList(TOP_VALUE);
	}

	@Test
	public void testGetMediaListException() {
		when(itemsService.getMediaList(TOP_VALUE)).thenThrow(new RuntimeException("someExceptionMessage"));
		ResponseEntity<ResponseWrapperDTO> responseActual = itemsController.getMediaList(TOP_VALUE);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseActual.getStatusCode());
		assertEquals("someExceptionMessage", responseActual.getBody().getMessage());
		assertNotNull(responseActual.getBody().getTimeStamp());
		assertNull(responseActual.getBody().getData());
		verify(itemsService).getMediaList(TOP_VALUE);
	}

	@Test
	@DisplayName("Scenario when Top value is 0")
	public void testGetMediaListTop0() {
		ResponseEntity<ResponseWrapperDTO> responseActual = itemsController.getMediaList(0);
		assertEquals(HttpStatus.BAD_REQUEST, responseActual.getStatusCode());
		assertEquals("Incomplete Data", responseActual.getBody().getMessage());
		assertNotNull(responseActual.getBody().getTimeStamp());
		assertNull(responseActual.getBody().getData());
		verify(itemsService, times(0)).getMediaList(anyInt());
	}

	@Test
	@DisplayName("Scenario when Top value is null")
	public void testGetMediaListTopNull() {
		ResponseEntity<ResponseWrapperDTO> responseActual = itemsController.getMediaList(null);
		assertEquals(HttpStatus.BAD_REQUEST, responseActual.getStatusCode());
		assertEquals("Incomplete Data", responseActual.getBody().getMessage());
		assertNotNull(responseActual.getBody().getTimeStamp());
		assertNull(responseActual.getBody().getData());
		verify(itemsService, times(0)).getMediaList(anyInt());
	}
	
	@Test
	public void testGetMediaDetailsById() {
		ResponseWrapperDTO responseMock = mock(ResponseWrapperDTO.class);
		when(responseMock.getStatus()).thenReturn(HttpStatus.OK);
		when(itemsService.getMediaDetailsById(MEDIA_ID)).thenReturn(responseMock);
		ResponseEntity<ResponseWrapperDTO> responseActual = itemsController.getMediaDetailsById(MEDIA_ID);
		assertEquals(HttpStatus.OK, responseActual.getStatusCode());
		verify(itemsService).getMediaDetailsById(MEDIA_ID);
	}
	@Test
	public void testGetMediaDetailsByIdException() {
		when(itemsService.getMediaDetailsById(MEDIA_ID)).thenThrow(new RuntimeException("someExceptionMessage"));
		ResponseEntity<ResponseWrapperDTO> responseActual = itemsController.getMediaDetailsById(MEDIA_ID);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseActual.getStatusCode());
		assertEquals("someExceptionMessage", responseActual.getBody().getMessage());
		assertNotNull(responseActual.getBody().getTimeStamp());
		assertNull(responseActual.getBody().getData());
		verify(itemsService).getMediaDetailsById(MEDIA_ID);
	}
	
	@Test
	@DisplayName("Scenario when Id value is null")
	public void testGetMediaDetailsByIdIsNull() {
		ResponseEntity<ResponseWrapperDTO> responseActual = itemsController.getMediaDetailsById(null);
		assertEquals(HttpStatus.BAD_REQUEST, responseActual.getStatusCode());
		assertEquals("Incomplete Data", responseActual.getBody().getMessage());
		assertNotNull(responseActual.getBody().getTimeStamp());
		assertNull(responseActual.getBody().getData());
		verify(itemsService, times(0)).getMediaDetailsById(anyInt());
	}

	@Test
	public void getMediaDetailsByIdAndType() {
		MediaRequestBody requestBody = mock(MediaRequestBody.class);
		when(requestBody.getId()).thenReturn(MEDIA_ID);
		when(requestBody.getImageType()).thenReturn(IMAGE_TYPE);
		ResponseWrapperDTO responseMock = mock(ResponseWrapperDTO.class);
		when(responseMock.getStatus()).thenReturn(HttpStatus.OK);
		when(itemsService.getMediaDetailsByIdAndType(MEDIA_ID, IMAGE_TYPE)).thenReturn(responseMock);

		ResponseEntity<ResponseWrapperDTO> responseActual = itemsController.getMediaDetailsByIdAndType(requestBody);

		assertEquals(HttpStatus.OK, responseActual.getStatusCode());
		assertEquals(responseMock, responseActual.getBody());
		verify(itemsService).getMediaDetailsByIdAndType(MEDIA_ID, IMAGE_TYPE);
	}

	@Test
	public void getMediaDetailsByIdAndTypeException() {
		MediaRequestBody requestBody = mock(MediaRequestBody.class);
		when(requestBody.getId()).thenReturn(MEDIA_ID);
		when(requestBody.getImageType()).thenReturn(IMAGE_TYPE);
		when(itemsService.getMediaDetailsByIdAndType(MEDIA_ID, IMAGE_TYPE))
				.thenThrow(new RuntimeException("someExceptionMessage"));

		ResponseEntity<ResponseWrapperDTO> responseActual = itemsController.getMediaDetailsByIdAndType(requestBody);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseActual.getStatusCode());
		verify(itemsService).getMediaDetailsByIdAndType(MEDIA_ID, IMAGE_TYPE);
	}

	@Test
	@DisplayName("Scenario when Id value is null")
	public void getMediaDetailsByIdAndTypeIdNull() {
		MediaRequestBody requestBody = mock(MediaRequestBody.class);
		when(requestBody.getId()).thenReturn(null);
		ResponseEntity<ResponseWrapperDTO> responseActual = itemsController.getMediaDetailsByIdAndType(requestBody);
		assertEquals(HttpStatus.BAD_REQUEST, responseActual.getStatusCode());
		assertEquals("Incomplete Data", responseActual.getBody().getMessage());
		assertNull(responseActual.getBody().getData());
		assertNotNull(responseActual.getBody().getTimeStamp());
		verify(itemsService, times(0)).getMediaDetailsByIdAndType(null, IMAGE_TYPE);
	}

	@Test
	@DisplayName("Scenario when ImageType value is null")
	public void getMediaDetailsByIdAndTypeTypeNull() {
		MediaRequestBody requestBody = mock(MediaRequestBody.class);
		when(requestBody.getId()).thenReturn(MEDIA_ID);
		when(requestBody.getImageType()).thenReturn(null);
		ResponseEntity<ResponseWrapperDTO> responseActual = itemsController.getMediaDetailsByIdAndType(requestBody);
		assertEquals(HttpStatus.BAD_REQUEST, responseActual.getStatusCode());
		assertEquals("Incomplete Data", responseActual.getBody().getMessage());
		assertNull(responseActual.getBody().getData());
		assertNotNull(responseActual.getBody().getTimeStamp());
		verify(itemsService, times(0)).getMediaDetailsByIdAndType(MEDIA_ID, null);
	}
}
