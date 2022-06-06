package com.blz.bookstore.service;



import com.blz.bookstore.dto.AddressDTO;
import com.blz.bookstore.model.AddressModel;
import com.blz.bookstore.model.UserRegistrationModel;
import com.blz.bookstore.repository.AddressRepository;
import com.blz.bookstore.repository.BookRepository;
import com.blz.bookstore.repository.UserRegistrationRepository;
import com.blz.bookstore.util.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements IAddressService{

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRegistrationRepository userRepository;

    @Autowired
    BookRepository bookStoreRepository;

    /**
     *
     * @param address
     * @param token
     * @return
     */
    @Override
    public AddressModel addAddress(AddressDTO address, String token) {
        int userId = TokenUtil.decodeToken(token);
        UserRegistrationModel user = userRepository.findById(userId).orElse(null);
        AddressModel addressDetails=new AddressModel();
        addressDetails.setAddress(address.getAddress());
        addressDetails.setCity(address.getCity());
        addressDetails.setState(address.getState());
//        addressDetails.setType(address.getType());
        addressDetails.setUserRegistrationModel(user);
        return addressRepository.save(addressDetails);
    }
}
