package com.reverse.userservice.controllers;

import com.reverse.userservice.models.BranchLocation;
import com.reverse.userservice.models.Gender;
import com.reverse.userservice.services.InHouseVariablesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {GetInHouseVariablesController.class})
@ExtendWith(SpringExtension.class)
class GetInHouseVariablesControllerTest {
    @Autowired
    private GetInHouseVariablesController getInHouseVariablesController;

    @MockBean
    private InHouseVariablesService inHouseVariablesService;

//    @Test
//    void testGetAllGendersRequest() throws Exception {
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/genders");
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.getInHouseVariablesController)
//                .build()
//                .perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
//    }
//
//    @Test
//    void testGetAllLocationsRequest() throws Exception {
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/locations");
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.getInHouseVariablesController)
//                .build()
//                .perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
//    }

    @Test
    void testGetAllGenders() {
        List<Gender> mockList = mock(List.class);

        when(inHouseVariablesService.getAllGenders()).thenReturn(mockList);

        ResponseEntity<List<Gender>> testResponseEntity = getInHouseVariablesController.getAllGenders();

        assertSame(mockList, testResponseEntity.getBody(), "mockList not returned!");
        assertEquals(HttpStatus.OK, testResponseEntity.getStatusCode(), "Response not OK!");
    }

    @Test
    void testGetAllLocations() {
        List<BranchLocation> mockList = mock(List.class);

        when(inHouseVariablesService.getAllLocations()).thenReturn(mockList);

        ResponseEntity<List<BranchLocation>> testResponseEntity = getInHouseVariablesController.getAllLocations();

        assertSame(mockList, testResponseEntity.getBody(), "mockList not returned!");
        assertEquals(HttpStatus.OK, testResponseEntity.getStatusCode(), "Response not OK!");
    }
}

