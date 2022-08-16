package com.example.jpaproject.service;

import com.example.jpaproject.repository.MemberRepository;
import com.example.jpaproject.repository.MileageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MileageService {

    private final MemberRepository memberRepository;
    private final MileageRepository mileageRepository;

}
