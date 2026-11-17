package soa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import soa.entity.Tourist;
import soa.service.TouristService;

@RestController
@RequestMapping("/api/tourists")
@Tag(name = "Tourist API", description = "Tourist records protected by a custom Token header")
public class TouristController {

    static final String ERROR_MESSAGE = "Unauthorized: Invalid Token Provided";

    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @Operation(summary = "Retrieve all tourist records",
               description = "Returns every tourist. Requires a valid value in the 'Token' request header.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Token valid - complete tourist list",
            content = @Content(mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = Tourist.class)))),
        @ApiResponse(responseCode = "500", description = "Token missing or invalid",
            content = @Content(mediaType = "text/plain",
                schema = @Schema(type = "string"),
                examples = @ExampleObject(value = ERROR_MESSAGE)))
    })
    @GetMapping
    public ResponseEntity<?> getAllTourists(
            @Parameter(description = "Pre-generated access token", required = false, example = "TOURIST-TEST-TOKEN-12345")
            @RequestHeader(value = "Token", required = false) String token) {

        // Step 1: validate the token BEFORE any database access
        if (!touristService.isTokenValid(token)) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(ERROR_MESSAGE);
        }

        // Step 2: token is valid, so it is now safe to query the database
        List<Tourist> tourists = touristService.getAllTourists();
        return ResponseEntity.ok(tourists);
    }
}
