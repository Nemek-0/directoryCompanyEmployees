package com.servece;

import com.dao.PhoneNumberDAO;
import com.entity.PhoneNumber;
import java.util.ArrayList;
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

    public List<PhoneNumber> getAllPhoneNumber() {
        List<PhoneNumber> list = new ArrayList<>();
        for(Object phoneNumber :phoneNumberDAO.getAllPhoneNumber()){
            list.add((PhoneNumber) phoneNumber);
        }
        return list;
    }

}
