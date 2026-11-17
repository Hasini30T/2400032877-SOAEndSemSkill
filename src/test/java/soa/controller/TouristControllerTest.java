package soa.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import soa.entity.Tourist;
import soa.repository.TouristRepository;
import soa.service.TouristService;

/** Plain unit tests: no MySQL needed. */
class TouristControllerTest {

    private static final String VALID = "valid-token";
    private static final String MSG = "Unauthorized: Invalid Token Provided";

    private TouristRepository repository;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        repository = mock(TouristRepository.class);
        TouristService service = new TouristService(repository, VALID);
        mockMvc = MockMvcBuilders.standaloneSetup(new TouristController(service)).build();
    }

    @Test
    void validToken_returns200AndJson() throws Exception {
        when(repository.findAll()).thenReturn(List.of(new Tourist("A", "a@x.com", "Paris", "France")));

        mockMvc.perform(get("/api/tourists").header("Token", VALID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].touristName").value("A"));
        verify(repository).findAll();
    }

    @Test
    void missingToken_returns500AndSkipsRepository() throws Exception {
        mockMvc.perform(get("/api/tourists"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(MSG));
        verifyNoInteractions(repository);
    }

    @Test
    void invalidToken_returns500AndSkipsRepository() throws Exception {
        mockMvc.perform(get("/api/tourists").header("Token", "wrong-token"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(MSG));
        verify(repository, never()).findAll();
    }
}
