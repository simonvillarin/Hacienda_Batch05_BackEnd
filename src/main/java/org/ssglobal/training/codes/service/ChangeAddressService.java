package org.ssglobal.training.codes.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.ssglobal.training.codes.model.ChangeAddress;
import org.ssglobal.training.codes.repository.ChangeAddressRepository;
import org.ssglobal.training.codes.response.ChangeAddressResponse;
import org.ssglobal.training.codes.response.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChangeAddressService {
	private final ChangeAddressRepository changeAddressRepository;
	
	public ChangeAddress getChangeAddressById(Long id) {
		return changeAddressRepository.getChangeAddressById(id);
	}
	
	public List<ChangeAddress> getAllChangeAddress() {
		return changeAddressRepository.getAllChangeAddress();
	}
	
	public ChangeAddressResponse getChangeAddressByTransactionId(Long id) {
		 return changeAddressRepository.getChangeAddressByTransactionId(id);
	}
    
    @Transactional
    public Response addChangeAddress(ChangeAddress changeAddress) {
        return changeAddressRepository.addChangeAddress(changeAddress);
    }
    
    @Transactional
    public Response updateChangeAddress(Long id, ChangeAddress changeAddress) {
        return changeAddressRepository.updateChangeAddress(id, changeAddress);
    }
}
