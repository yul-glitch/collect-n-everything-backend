package com.diy.api;

import com.diy.generated.api.StoreApi;
import com.diy.generated.model.AddressDto;
import com.diy.generated.model.StoreDto;
import com.diy.mapper.AddressModelMapper;
import com.diy.mapper.StoreModelMapper;
import com.diy.model.AddressModel;
import com.diy.model.StoreModel;
import com.diy.service.AddressService;
import com.diy.service.StoreService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StoreApiImpl implements StoreApi {

    StoreService storeService;
    AddressService addressService;
    StoreModelMapper storeModelMapper;
    AddressModelMapper addressModelMapper;

    @Override
    public ResponseEntity<StoreDto> findStoreById(Long storeId) {
        return ResponseEntity.ok(storeModelMapper.modelToDto(storeService.findStoreById(storeId)));
    }

    @Override
    public ResponseEntity<StoreDto> findStoreByStoreName(String storename) {
        return ResponseEntity.ok(storeModelMapper.modelToDto(storeService.findStoreByStoreName(storename)));
    }

    @Override
    public ResponseEntity<List<StoreDto>> findStores() {
        return ResponseEntity.ok(storeModelMapper.modelsToDtos(storeService.findAllStores()));
    }

    @Override
    public ResponseEntity<AddressDto> saveAddress(Long storeid, AddressDto addressDto) {
        AddressModel addressModel = addressModelMapper.dtoToModel(addressDto);
        return ResponseEntity.ok(addressModelMapper.modelToDto(addressService.saveAddress(addressModel, storeid)));
    }

    @Override
    public ResponseEntity<StoreDto> saveStore(StoreDto storeDto) {
        StoreModel store = storeModelMapper.dtoToModel(storeDto);
        return ResponseEntity.ok(storeModelMapper.modelToDto(storeService.save(store)));
    }

    @Override
    public ResponseEntity<String> deleteStoreById(Long storeId) {
        return ResponseEntity.ok(storeService.deleteStore(storeId));
    }

    @Override
    public ResponseEntity<String> deleteAddressById(Long addressid) {
        return ResponseEntity.ok(addressService.deleteAddress(addressid));
    }

    @Override
    public ResponseEntity<AddressDto> findAddressById(Long addressid) {
        return ResponseEntity.ok(addressModelMapper.modelToDto(addressService.getAddressById(addressid)));
    }

    @Override
    public ResponseEntity<List<AddressDto>> findAddressesByStoreId(Long storeid) {
        return ResponseEntity.ok(addressModelMapper.modelsToDtos(addressService.getAddressesFromStoreId(storeid)));
    }

}
