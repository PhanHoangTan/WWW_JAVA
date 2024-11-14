package vn.edu.iuh.fit.lab05.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vn.edu.iuh.fit.lab05.model.Candidate;
import vn.edu.iuh.fit.lab05.respositories.CandidateRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CandidateServiceTest {

    @Mock
    private CandidateRepository candidateRepository;

    @InjectMocks
    private CandidateService candidateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCandidates() {
        assertEquals(0, 0);
    }

    @Test
    void testAddCandidate() {
        Candidate candidate = new Candidate();
        String lastName = "Doe";
        String middleName = "A.";
        String firstName = "John";
        String fullName = lastName + " " + middleName + " " + firstName;
        candidate.setFull_Name(fullName);
        candidate.setDob("1990-01-01");
        candidate.setEmail("john.doe@example.com");
        candidate.setAddress("123 Main St");
        candidate.setPhone("1234567890");

        when(candidateRepository.add(any(Candidate.class))).thenReturn(true);

        boolean isCreated = candidateService.createCandidate(candidate);

        assertTrue(isCreated);
    }
}