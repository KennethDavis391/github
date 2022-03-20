package com.reverse.userservice.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.reverse.userservice.models.BranchLocation;
import com.reverse.userservice.models.Gender;
import com.reverse.userservice.repositories.BranchLocationRepository;
import com.reverse.userservice.repositories.GenderRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {InHouseVariablesService.class})
@ExtendWith(SpringExtension.class)
class InHouseVariablesServiceTest {
    @MockBean
    private BranchLocationRepository branchLocationRepository;

    @MockBean
    private GenderRepository genderRepository;

    @Autowired
    private InHouseVariablesService inHouseVariablesService;

    @Test
    void testGetAllGenders() {
        ArrayList<Gender> genderList = new ArrayList<Gender>();
        when(this.genderRepository.findAll()).thenReturn(genderList);
        List<Gender> actualAllGenders = this.inHouseVariablesService.getAllGenders();
        assertSame(genderList, actualAllGenders);
        assertTrue(actualAllGenders.isEmpty());
        verify(this.genderRepository).findAll();
        assertTrue(this.inHouseVariablesService.getAllLocations().isEmpty());
    }

    @Test
    void testGetAllLocations() {
        ArrayList<BranchLocation> branchLocationList = new ArrayList<BranchLocation>();
        when(this.branchLocationRepository.findAll()).thenReturn(branchLocationList);
        List<BranchLocation> actualAllLocations = this.inHouseVariablesService.getAllLocations();
        assertSame(branchLocationList, actualAllLocations);
        assertTrue(actualAllLocations.isEmpty());
        verify(this.branchLocationRepository).findAll();
        assertTrue(this.inHouseVariablesService.getAllGenders().isEmpty());
    }
}

