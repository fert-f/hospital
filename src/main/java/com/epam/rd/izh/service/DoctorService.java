package com.epam.rd.izh.service;

import com.epam.rd.izh.repository.DoctorRepository;
import com.epam.rd.izh.repository.TextContentRepository;
import com.epam.rd.izh.repository.TimeTableRepository;
import com.epam.rd.izh.repository.UserRepository;
import com.epam.rd.izh.util.TimeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    TextContentRepository textContentRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TimeTableRepository timeTableRepository;

    @Autowired
    TimeHolder timeHolder;
}
