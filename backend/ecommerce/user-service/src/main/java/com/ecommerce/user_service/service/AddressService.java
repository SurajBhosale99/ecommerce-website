package com.ecommerce.user_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.user_service.dao.AddressRepository;
import com.ecommerce.user_service.dao.UserRepository;
import com.ecommerce.user_service.entity.Address;
import com.ecommerce.user_service.entity.User;



@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    public Address addAddress(Long userId, Address address) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        address.setUser(user);;

        // If it's default, mark others as non-default
        if (address.isDefault()) {
            List<Address> userAddresses = addressRepository.findByUserId(userId);
            userAddresses.forEach(a -> {
                a.setDefault(false);
                addressRepository.save(a);
            });
        }

        return addressRepository.save(address);
    }

    public Address updateAddress(Long addressId, Address newAddress) {
        Address address = addressRepository.findById(addressId)
            .orElseThrow(() -> new RuntimeException("Address not found"));

        address.setStreet(newAddress.getStreet());
        address.setCity(newAddress.getCity());
        address.setState(newAddress.getState());
        address.setZipCode(newAddress.getZipCode());
        address.setCountry(newAddress.getCountry());

        // Handle default
        if (newAddress.isDefault()) {
            List<Address> userAddresses = addressRepository.findByUserId(address.getUser().getId());
            userAddresses.forEach(a -> {
                a.setDefault(false);
                addressRepository.save(a);
            });
            address.setDefault(true);
        }

        return addressRepository.save(address);
    }

    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }

    public List<Address> getUserAddresses(Long userId) {
        return addressRepository.findByUserId(userId);
    }
}
