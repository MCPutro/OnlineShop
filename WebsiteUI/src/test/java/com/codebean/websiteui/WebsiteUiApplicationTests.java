package com.codebean.websiteui;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebsiteUiApplicationTests {

    @Autowired
    private Gson gson;

    @Test
    void contextLoads() {
        String data = "[{id=41, username=admin123, email=as112d2@sa5111.1221, phoneNumber=08123456789, role=Admin, isActive=true, profile={firstName=coba12, lastName=lagi1, dateOfBirth=2025-01-03T04:05:06, gender=unknow}, addresses=[{name=000, address=000, country=000, postalCode=000, isActive=false, id=27}, {name=000, address=000, country=000, postalCode=000, isActive=false, id=30}, {name=2222, address=222, country=22, postalCode=222, isActive=false, id=31}, {name=2222, address=222, country=22, postalCode=222, isActive=false, id=32}, {name=333, address=333, country=333, postalCode=333, isActive=true, id=33}, {name=555, address=555, country=555, postalCode=555, isActive=false, id=34}], permissions=[{name=SHOWUSERS, description=Permission to show all user, id=4}, {name=SHOWUSERDETAIL, description=Permission to show other users detail, id=5}, {name=EDITUSERDETAIL, description=Permission to edit other users details, id=3}, {name=PROFILE, description=Permission to show own profile detail, id=2}]}, {id=42, username=customer001, email=admin@baru.donk0211111, phoneNumber=0812345678911, role=Customer, isActive=true, profile={firstName=coba12xxxx3, lastName=lagi12xxx, dateOfBirth=2025-01-03T04:05:06, gender=unknow2xxx}, addresses=[{name=0000, address=0000, country=0000, postalCode=0000, isActive=false, id=24}, {name=lapangan, address=di lapangan bola, country=disono, postalCode=32145ad, isActive=false, id=25}, {name=lapangan1, address=di lapangan bol1a, country=disono1, postalCode=32145ad1, isActive=false, id=26}, {name=lapangan, address=di lapangan bola, country=disono, postalCode=32145ad, isActive=false, id=28}, {name=666, address=666, country=666, postalCode=666, isActive=false, id=35}, {name=666, address=666, country=666, postalCode=666, isActive=false, id=36}], permissions=[{name=PROFILE, description=Permission to show own profile detail, id=2}]}]";

    }

}
