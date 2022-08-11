package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.models.Code;
import platform.models.IdResponse;
import platform.services.CodeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class APIInterface {

    private final CodeService service;

    @Autowired
    public APIInterface(CodeService service) {
        this.service = service;
    }

    @GetMapping("/code/latest")
    public ResponseEntity<List<Code>> getLatestCodes() {
        List<Code> codes = service.getSortedLatestCodes();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        return ResponseEntity.ok()
                .headers(headers)
                .body(codes);
    }

    @GetMapping("/code/{uuid}")
    public ResponseEntity<Code> getCode(@PathVariable("uuid") String uuid) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        return ResponseEntity.ok()
                .headers(headers)
                .body(service.getCode(uuid));
    }

    @PostMapping("/code/new")
    public ResponseEntity<IdResponse> postCode(@RequestBody Code code) {
        UUID id = service.postCode(code);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        return ResponseEntity.ok()
                .headers(headers)
                .body(new IdResponse(id.toString()));
    }
}
