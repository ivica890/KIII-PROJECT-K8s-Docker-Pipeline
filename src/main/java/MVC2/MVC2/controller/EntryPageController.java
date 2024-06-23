package MVC2.MVC2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EntryPageController {

    @GetMapping("/")
    public String returnEntryPage() {

        return "homePage/home_page";
    }
}
