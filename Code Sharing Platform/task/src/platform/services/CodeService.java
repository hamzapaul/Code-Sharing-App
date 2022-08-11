package platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import platform.models.Code;
import platform.repositories.CodeRepo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class CodeService {
    private final CodeRepo codeRepo;

    @Autowired
    public CodeService(CodeRepo codeRepo) {
        this.codeRepo = codeRepo;
    }

    public Code getCode(String id) {
        Optional<Code> code = codeRepo.findById(UUID.fromString(id));

        if (code.isPresent()) {
            return restrictedCode(code.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private Code restrictedCode(Code code) {

        long spendSeconds;

        if (code.isTimeRestricted()) {
            LocalDateTime now = LocalDateTime.now();
            spendSeconds = ChronoUnit.SECONDS.between(code.getTimestamp(), now);

            if (spendSeconds > code.getOriginalTime()) {
                codeRepo.delete(code);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            } else {
                //update time
                code.setTime(code.getOriginalTime() - spendSeconds);
                codeRepo.save(code);
            }
        }

        if (code.isViewsRestricted()) {
            if (code.getViews() > 0) {
                //update views
                code.setViews(code.getViews() - 1);
                codeRepo.save(code);

            } else {
                codeRepo.delete(code);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }

        return code;
    }

    public UUID postCode(Code code) {
        Code newCode = new Code(code.getCode(), code.getTime(), code.getViews());
        Code savedEntity = codeRepo.save(newCode);

        return savedEntity.getId();
    }

    public List<Code> getSortedLatestCodes() {
       return codeRepo.findLatest();
    }
}
