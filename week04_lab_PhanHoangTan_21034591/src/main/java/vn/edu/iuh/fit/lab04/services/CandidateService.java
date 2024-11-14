package vn.edu.iuh.fit.lab05.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab05.model.Candidate;
import vn.edu.iuh.fit.lab05.respositories.CandidateRepository;

import java.util.List;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    public boolean createCandidate(Candidate candidate) {
        return candidateRepository.add(candidate);
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }
}