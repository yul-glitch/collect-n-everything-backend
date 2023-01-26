package com.diy.service;

import com.diy.entity.AddressEntity;
import com.diy.entity.StoreEntity;
import com.diy.exception.ExceptionHandler;
import com.diy.mapper.AddressModelMapper;
import com.diy.mapper.CycleAvoidingMappingContext;
import com.diy.model.AddressModel;
import com.diy.repository.AddressRepository;
import com.diy.repository.StoreRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
public class AddressService {

    AddressRepository addressRepository;
    StoreRepository storeRepository;
    AddressModelMapper addressModelMapper;

    public List<AddressModel> getAddressesFromStoreId(Long storeId) {
        StoreEntity storeEntity = storeRepository.findById(storeId).orElseThrow(() -> new ExceptionHandler("Store not found"));
        List<AddressEntity> addressEntities = addressRepository.findByStoreAndDeletedAt(storeEntity, null);
        return addressModelMapper.entitiesToModels(addressEntities, new CycleAvoidingMappingContext());
    }

    public AddressModel getAddressById(Long addressId) {
        AddressEntity addressEntity = addressRepository.findById(addressId).orElseThrow(() -> new ExceptionHandler("Address not found"));
        return addressModelMapper.entityToModel(addressEntity, new CycleAvoidingMappingContext());
    }

    public String deleteAddress(Long addressId) {
        try {
            AddressEntity addressEntity = addressRepository.findById(addressId).orElseThrow(() -> new ExceptionHandler("Address not found"));
            addressEntity.setUpdatedAt(LocalDateTime.now());
            addressEntity.setDeletedAt(LocalDateTime.now());
            addressRepository.save(addressEntity);
            return "Your address has been successfully deleted";
        } catch (Exception e) {
            log.error("Error while deleting an address: " + e.getMessage());
            throw new ExceptionHandler("We could not delete your address");
        }
    }

    @Transactional
    public AddressModel saveAddress(AddressModel addressModel, Long storeId) {
        if (addressModel.getAddressId() == null)
            return createAddress(addressModel, storeId);
        else
            return updateAddress(addressModel);
    }

    private AddressModel createAddress(AddressModel addressModel, Long storeId) {
        try {
            StoreEntity storeEntity = storeRepository.findById(storeId).orElseThrow(() -> new ExceptionHandler("Store not found"));
            AddressEntity addressEntity = addressModelMapper.modelToEntity(addressModel);
            addressEntity.setStore(storeEntity);
            addressEntity.setCreatedAt(LocalDateTime.now());
            addressRepository.save(addressEntity);
            return addressModelMapper.entityToModel(addressEntity, new CycleAvoidingMappingContext());

        } catch (Exception e) {
            log.error("Error while creating address: " + e.getMessage());
            log.error("Address model: " + addressModel.toString());
            throw new ExceptionHandler("We could not create your address");
        }
    }

    private AddressModel updateAddress(AddressModel addressModel) {
        try {
            AddressEntity addressEntity = addressRepository.findById(addressModel.getAddressId()).orElseThrow(() -> new ExceptionHandler("Address not found"));
            addressModelMapper.updateStoreFromModel(addressModel, addressEntity, new CycleAvoidingMappingContext());
            addressEntity.setUpdatedAt(LocalDateTime.now());
            addressRepository.save(addressEntity);
            return addressModelMapper.entityToModel(addressEntity, new CycleAvoidingMappingContext());
        } catch (Exception e) {
            log.error("Error while updating address: " + e.getMessage());
            log.error("Address model: " + addressModel.toString());
            throw new ExceptionHandler("We could not update your address");
        }
    }

}
