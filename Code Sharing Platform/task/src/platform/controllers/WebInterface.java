package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import platform.services.CodeService;

@Controller
@RequestMapping("/")
public class WebInterface {
    private final CodeService service;

    @Autowired
    public WebInterface(CodeService service) {
        this.service = service;
    }

    @GetMapping("/code/{uuid}")
    public String getCode(@PathVariable("uuid") String uuid, Model model) {
        model.addAttribute("codeObj", service.getCode(uuid));
        return "index";
    }

    @GetMapping("/code/new")
    public String insertNewCode() {
        return "codeForm";
    }

    @GetMapping("/code/latest")
    public String getLatestCode(Model model) {
        model.addAttribute("list", service.getSortedLatestCodes());
        return "latest";
    }
}
