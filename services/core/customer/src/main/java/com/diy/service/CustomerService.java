package com.diy.service;

import com.diy.entity.CustomerEntity;
import com.diy.exception.ExceptionHandler;
import com.diy.generated.model.CustomerDto;
import com.diy.mapper.CustomerModelMapper;
import com.diy.mapper.CycleAvoidingMappingContext;
import com.diy.model.CustomerModel;
import com.diy.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
public class CustomerService {

    CustomerModelMapper modelMapper;
    CustomerRepository customerRepository;

    public String deleteCustomerById(Long customerId) {
        try {
            CustomerEntity entity = customerRepository.findById(customerId).orElseThrow(
                    () -> new ExceptionHandler("We could not find your account"));
            entity.setDeletedAt(LocalDateTime.now());
            entity.setUpdatedAt(LocalDateTime.now());
            customerRepository.save(entity);
            return "Your account has been deleted";
        } catch (Exception e) {
            log.error("Error while deleting a customer: " +  e.getMessage());
            throw new ExceptionHandler("We could not delete your account");
        }
    }
    @Transactional
    public String deleteCustomersByStoresId(Long storeId) {
        try {
            List<CustomerEntity> entities = customerRepository.findByStoreId(storeId);
            entities.forEach(entity -> {
                entity.setDeletedAt(LocalDateTime.now());
                entity.setUpdatedAt(LocalDateTime.now());
                customerRepository.save(entity);
            });
            return "Your account has been deleted";
        } catch (Exception e) {
            log.error("Error while deleting a customer: " +  e.getMessage());
            throw new ExceptionHandler("We could not delete your account");
        }
    }

    public CustomerModel findCustomerById(Long customerId) {
        try {
            CustomerEntity entity = customerRepository.findById(customerId).orElseThrow(
                    () -> new ExceptionHandler("We could not find your account"));
            return modelMapper.entityToModel(entity, new CycleAvoidingMappingContext());
        } catch (Exception e) {
            log.error("Error while finding a customer by id: " +  e.getMessage());
            throw new ExceptionHandler("We could not find your account");
        }
    }

    public List<CustomerModel> findCustomersByStoreId(Long storeId) {
        try {
            List<CustomerEntity> entities = customerRepository.findByStoreId(storeId);
            return modelMapper.entitiesToModels(entities, new CycleAvoidingMappingContext());
        } catch (Exception e) {
            log.error("Error while finding all customers by storeId: " +  e.getMessage());
            throw new ExceptionHandler("We could not find customers accounts");
        }
    }

    public CustomerModel updateCustomer(CustomerModel model) {
        try {
            CustomerEntity entity = customerRepository.findById(model.getCustomerId()).orElseThrow(
                    () -> new ExceptionHandler("We could not find your account"));
            modelMapper.updateCustomerFromModel(model, entity, new CycleAvoidingMappingContext());
            entity.setUpdatedAt(LocalDateTime.now());
            customerRepository.save(entity);
            return modelMapper.entityToModel(entity, new CycleAvoidingMappingContext());
        } catch (Exception e) {
            log.error("Error while updating a customer: " + e.getMessage());
            log.error("Customer model: " + model.toString());
            throw new ExceptionHandler("We could not update your information");
        }
    }

    public CustomerModel createCustomer(CustomerModel model) {
        try {
            CustomerEntity entity = modelMapper.modelToEntity(model);
            entity.setCreatedAt(LocalDateTime.now());
            customerRepository.save(entity);
            return modelMapper.entityToModel(entity, new CycleAvoidingMappingContext());
        } catch (Exception e) {
            log.error("We could not create a customer: " + e.getMessage());
            log.error("Customer model: " + model.toString());
            throw new ExceptionHandler("We could not create your account");
        }
    }
    
}
