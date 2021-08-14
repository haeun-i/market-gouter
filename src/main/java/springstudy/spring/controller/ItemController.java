package springstudy.spring.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springstudy.spring.service.ItemService;

import java.util.logging.Logger;

@Controller
public class ItemController {
    private static final Logger logger = LoggerFactory.getLogger("ItemController.class");
    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public String test(){
        return "index"; // index.html로 이동
    }


}
