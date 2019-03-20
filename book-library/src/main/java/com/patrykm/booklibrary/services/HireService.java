package com.patrykm.booklibrary.services;

import com.patrykm.booklibrary.domain.Hire;
import com.patrykm.booklibrary.repository.HireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HireService {

    @Autowired
    HireRepository hireRepository;

    public List<Hire> getHiresByBookId(Integer id){
        return hireRepository.findByHiredBook_Id(id);
    }
}
