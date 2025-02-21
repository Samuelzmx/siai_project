package com.example.siai.controller;

import com.example.siai.entity.SecFiling;
import com.example.siai.service.SecFilingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/api/secfiling")
@CrossOrigin(origins = "http://localhost:3000")
public class SecFilingController {

    @Autowired
    private SecFilingService secFilingService;

    // ====== Existing Endpoints remain as is (CRUD, etc.) ======
    // e.g. getAllSecFilings(), getSecFilingById(...), etc.

    /**
     * Example new endpoint:
     * GET /api/secfiling/search?company=Apple&filingType=10-K&date=2025-01-31
     */
    @GetMapping("/search")
    public ResponseEntity<?> searchFiling(
            @RequestParam String company,
            @RequestParam String filingType,
            @RequestParam String date // e.g. "2025-01-31"
    ) {
        try {
            // parse the date string into LocalDate
            LocalDate localDate = LocalDate.parse(date);
            SecFiling filing = secFilingService.getFilingByCompanyTypeAndDate(company, filingType, localDate);

            if (filing == null) {
                // Not found
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No filing found for (company=" + company
                                + ", type=" + filingType
                                + ", date=" + date + ")");
            }
            // Found => Return the filing in JSON
            return ResponseEntity.ok(filing);

        } catch (DateTimeParseException ex) {
            // If the user typed an invalid date
            return ResponseEntity.badRequest()
                    .body("Invalid date format: " + date + " (expected yyyy-MM-dd)");
        } catch (Exception ex) {
            // Catch any unexpected errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + ex.getMessage());
        }
    }
}
