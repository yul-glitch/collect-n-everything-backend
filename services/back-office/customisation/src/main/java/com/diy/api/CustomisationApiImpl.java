package com.diy.api;

import com.diy.generated.api.CustomisationApi;
import com.diy.generated.model.CustomisationDto;
import com.diy.mapper.CustomisationModelMapper;
import com.diy.model.CustomisationModel;
import com.diy.repository.CustomisationRepository;
import com.diy.service.CustomisationService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomisationApiImpl implements CustomisationApi {

    CustomisationService customisationService;
    CustomisationModelMapper modelMapper;

    @Override
    public ResponseEntity<CustomisationDto> createCustomisation(CustomisationDto customisationDto) {
        return ResponseEntity.ok(modelMapper.modelToDto(customisationService.createCustomisation(modelMapper.dtoToModel(customisationDto))));
    }

    @Override
    public ResponseEntity<String> deleteCustomisationById(Long customisationId) {
        return ResponseEntity.ok(customisationService.deleteCustomisationById(customisationId));
    }

    @Override
    public ResponseEntity<CustomisationDto> findCustomisationById(Long customisationId) {
        return ResponseEntity.ok(modelMapper.modelToDto(customisationService.findCustomisationById(customisationId)));
    }

    @Override
    public ResponseEntity<List<CustomisationDto>> findCustomisationByStoreId(Long storeId) {
        return ResponseEntity.ok(modelMapper.modelsToDtos(customisationService.findCustomisationByStoreId(storeId)));
    }

    @Override
    public ResponseEntity<CustomisationDto> updateCustomisation(CustomisationDto customisationDto) {
        return ResponseEntity.ok(modelMapper.modelToDto(customisationService.updateCustomisation(modelMapper.dtoToModel(customisationDto))));
    }
}
