package com.actstrady.autofilldata;

import com.actstrady.autofilldata.controller.FileFullController;
import com.actstrady.autofilldata.service.FileFullService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.security.RunAs;
import javax.servlet.http.HttpServletRequest;

@SpringBootTest
class AutofillDataApplicationTests {
    @Autowired
    FileFullService fileFullService;

    @Test
    void contextLoads() {
       // fileFullService.full();
    }

}
