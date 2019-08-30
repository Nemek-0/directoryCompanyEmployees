package com.entity;

import org.junit.Assert;
import org.junit.Test;

public class PhoneNumberTest  {
    @Test
    public void testIsPhoneNumber() {
        PhoneNumber phoneNumber = new PhoneNumber("79201203110");
        Assert.assertTrue(phoneNumber.isPhoneNumber(new PhoneNumber("79201203110")));
        Assert.assertFalse(phoneNumber.isPhoneNumber(new PhoneNumber("79201203111")));
    }
}