package com.servece;

import com.dao.PhoneNumberDAO;
import com.entity.PhoneNumber;

import java.util.List;

public class PhoneNumberService {
    private PhoneNumberDAO phoneNumberDAO = new PhoneNumberDAO();
    public PhoneNumberService() {
    }
    public PhoneNumber findPhoneNumber(int id) {
        return phoneNumberDAO.findById(id);
    }

    public void savePhoneNumber(PhoneNumber phoneNumber) {
        phoneNumberDAO.save( phoneNumber);
    }

    public void deletePhoneNumber(PhoneNumber  phoneNumber) {
        phoneNumberDAO.delete(phoneNumber);
    }

    public void updatePhoneNumber(PhoneNumber  phoneNumber) {
        phoneNumberDAO.update( phoneNumber);
    }

    public List getAllPhoneNumber() {
        return  phoneNumberDAO.getAllPhoneNumber();
    }

}
