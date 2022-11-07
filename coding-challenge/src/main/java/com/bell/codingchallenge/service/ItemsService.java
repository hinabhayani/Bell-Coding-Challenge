package com.bell.codingchallenge.service;

import com.bell.codingchallenge.payload.ResponseWrapperDTO;

public interface ItemsService {
	ResponseWrapperDTO getMediaList(Integer NoOfMedia);

	ResponseWrapperDTO getMediaDetailsById(Integer id);
	
	ResponseWrapperDTO getMediaDetailsByIdAndType(Integer id,String imageType);
}
