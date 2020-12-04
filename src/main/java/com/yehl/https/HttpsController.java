package com.yehl.https;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpsController {

    @RequestMapping("/https")
    public String https() {
        return "Https Request................";
    }
}
