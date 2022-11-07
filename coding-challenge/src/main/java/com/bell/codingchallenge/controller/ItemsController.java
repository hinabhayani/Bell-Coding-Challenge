package com.bell.codingchallenge.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bell.codingchallenge.payload.ResponseWrapperDTO;
import com.bell.codingchallenge.service.ItemsService;
import com.bell.codingchallenge.utils.BellMethodUtil;
import com.bell.codingchallenge.utils.MediaRequestBody;

@RestController
@RequestMapping("/api/media")
public class ItemsController {

	@Autowired
	private ItemsService itemService;

	@GetMapping("getMediaList")
	public ResponseEntity<ResponseWrapperDTO> getMediaList(@RequestParam(required = false) Integer top) {
		try {
			if (Objects.isNull(top) || top == 0)
				return BellMethodUtil.incompleteDataResponseEntityForController();

			ResponseWrapperDTO response = itemService.getMediaList(top);

			return new ResponseEntity<>(response, response.getStatus());
		} catch (Exception e) {
			return BellMethodUtil.exceptionResponseEntity(e.getMessage());
		}
	}

	@GetMapping("getMediaDetailsById/{id}")
	public ResponseEntity<ResponseWrapperDTO> getMediaDetailsById(@PathVariable Integer id) {
		try {
			if (Objects.isNull(id))
				return BellMethodUtil.incompleteDataResponseEntityForController();

			ResponseWrapperDTO response = itemService.getMediaDetailsById(id);

			return new ResponseEntity<>(response, response.getStatus());
		} catch (Exception e) {
			return BellMethodUtil.exceptionResponseEntity(e.getMessage());
		}
	}

	@PostMapping("getMediaDetailsByIdAndType")
	public ResponseEntity<ResponseWrapperDTO> getMediaDetailsByIdAndType(@RequestBody MediaRequestBody requestBody) {
		try {
			if (Objects.isNull(requestBody.getId()) || !StringUtils.hasLength(requestBody.getImageType()))
				return BellMethodUtil.incompleteDataResponseEntityForController();

			ResponseWrapperDTO response = itemService.getMediaDetailsByIdAndType(requestBody.getId(),
					requestBody.getImageType());

			return new ResponseEntity<>(response, response.getStatus());
		} catch (Exception e) {
			return BellMethodUtil.exceptionResponseEntity(e.getMessage());
		}
	}

}
