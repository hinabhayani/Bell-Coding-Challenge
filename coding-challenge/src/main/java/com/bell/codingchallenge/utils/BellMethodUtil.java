package com.bell.codingchallenge.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.bell.codingchallenge.payload.ResponseWrapperDTO;

public class BellMethodUtil {
	public static final RestTemplate restTemplate = new RestTemplate();

	public static ResponseEntity<ResponseWrapperDTO> incompleteDataResponseEntityForController() {
		return new ResponseEntity<ResponseWrapperDTO>(
				new ResponseWrapperDTO(BellVariableUtil.INCOMPLETE_DATA_FROM_UI, null), HttpStatus.BAD_REQUEST);
	}

	public static ResponseEntity<ResponseWrapperDTO> exceptionResponseEntity(String error) {
		return new ResponseEntity<ResponseWrapperDTO>(new ResponseWrapperDTO(error, null),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
