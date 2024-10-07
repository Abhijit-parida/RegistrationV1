package com.api.service;

import com.api.entity.Registration;
import com.api.exception.ResourceNotFoundException;
import com.api.payload.RegistrationDto;
import com.api.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;

    public RegistrationService(RegistrationRepository registrationRepository,
                               ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;
    }

    Registration mapToRegEntity(RegistrationDto registrationDto) {
        Registration registration = modelMapper.map(registrationDto, Registration.class);
        return registration;
    }

    RegistrationDto mapToDto(Registration registration) {
        RegistrationDto dto = modelMapper.map(registration, RegistrationDto.class);
        return dto;
    }

    public List<RegistrationDto> getRegistration() {
        List<Registration> registrations = registrationRepository.findAll();
        List<RegistrationDto> dtos = registrations.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return dtos;
    }

    public RegistrationDto createRegistration(RegistrationDto registrationDto) {
        //Copy Dto to RegEntity
        Registration registration= mapToRegEntity(registrationDto);
        Registration savedEntity = registrationRepository.save(registration);
        //Copy regEntity to Dto
        RegistrationDto dto = mapToDto(savedEntity);
        return dto;
    }

    public void deleteRegistration(long id) {
        registrationRepository.deleteById(id);
    }

    public Registration updateRegistration(long id,
                                           Registration registration) {
        Registration reg = registrationRepository.findById(id).get();
        reg.setName(registration.getName());
        reg.setEmail(registration.getEmail());
        reg.setMobile(registration.getMobile());
        Registration savedEntity = registrationRepository.save(reg);
        return savedEntity;
    }

    // Through exception
    public RegistrationDto getRegistrationById(long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Record not found"));
        return mapToDto(registration);
    }
}
