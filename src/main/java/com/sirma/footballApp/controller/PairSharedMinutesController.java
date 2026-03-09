package com.sirma.footballApp.controller;
import com.sirma.footballApp.dto.PairSharedMinutesDto;
import com.sirma.footballApp.service.PairSharedMinutesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/")
public class PairSharedMinutesController {

    private final PairSharedMinutesService pairService;
    public PairSharedMinutesController(PairSharedMinutesService _pairService) {
        pairService = _pairService;
    }

    @GetMapping
    public ResponseEntity<?> getLongestPlayingPairs() {
        List<PairSharedMinutesDto> result = pairService.longestSharedMinutesPair();

        if (result.isEmpty()) {
            return ResponseEntity.ok("No player pairs found with shared minutes");
        }
        return ResponseEntity.ok(result);
    }
}