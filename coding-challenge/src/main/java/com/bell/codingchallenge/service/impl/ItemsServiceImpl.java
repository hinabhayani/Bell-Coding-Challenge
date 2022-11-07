package com.bell.codingchallenge.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bell.codingchallenge.model.MediaDetails;
import com.bell.codingchallenge.model.Medias;
import com.bell.codingchallenge.model.MediaImages;
import com.bell.codingchallenge.payload.ResponseWrapperDTO;
import com.bell.codingchallenge.service.ItemsService;
import com.bell.codingchallenge.utils.BellMethodUtil;
import com.bell.codingchallenge.utils.BellVariableUtil;

@Service
public class ItemsServiceImpl implements ItemsService {

	@Override
	public ResponseWrapperDTO getMediaList(Integer NoOfMedia) {

		ResponseEntity<Medias> listOfMedias = BellMethodUtil.restTemplate
				.getForEntity(BellVariableUtil.BELL_API_FOR_LIST_OF_MEDIA + NoOfMedia, Medias.class);

		if (Objects.isNull(listOfMedias.getBody()))
			return new ResponseWrapperDTO("There Is No Media List Available.", null, HttpStatus.NO_CONTENT);

		return new ResponseWrapperDTO("Sucessfuly Get Media List.", listOfMedias.getBody(), HttpStatus.OK);

	}

	@Override
	public ResponseWrapperDTO getMediaDetailsById(Integer id) {
		ResponseEntity<MediaDetails> responseEntity = BellMethodUtil.restTemplate
				.getForEntity(BellVariableUtil.BELL_API_FOR_MEDIA_DETAILS + id, MediaDetails.class);
		
		if (Objects.isNull(responseEntity.getBody()))
			return new ResponseWrapperDTO("There Is No Media List Available.", null, HttpStatus.OK);
		
		return new ResponseWrapperDTO("Sucessfully Get Media Details.", responseEntity.getBody(), HttpStatus.OK);
	}

	@Override
	public ResponseWrapperDTO getMediaDetailsByIdAndType(Integer id, String imageType) {

		ResponseEntity<MediaDetails> responseEntity = BellMethodUtil.restTemplate
				.getForEntity(BellVariableUtil.BELL_API_FOR_MEDIA_DETAILS + id, MediaDetails.class);

		MediaDetails itemDetails = responseEntity.getBody();

		if (Objects.isNull(itemDetails))
			return new ResponseWrapperDTO("Media Details Not Found.", null, HttpStatus.NO_CONTENT);

		if (Objects.isNull(itemDetails.getImages()))
			return new ResponseWrapperDTO("Images are Not Available Of This Media.", null, HttpStatus.NO_CONTENT);

		List<MediaImages> listOfImages = itemDetails.getImages().stream()
				.filter(data -> data.getType().trim().equals(imageType.trim())).collect(Collectors.toList());

		if (!Objects.isNull(listOfImages)) {
			itemDetails.setImages(listOfImages);
		}

		return new ResponseWrapperDTO("Sucessfuly Get Media Details With Image Type.", itemDetails, HttpStatus.OK);

	}

}
