package com.julien.hansab.rest;

import com.julien.hansab.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CarRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetCarsSortedByIdAsc() throws Exception {
        this.mockMvc.perform(get("/cars")
                .params(TestUtil.constructHttpParams(0, 3, "id,asc")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].make").value("Lada"))
                .andExpect(jsonPath("$.[0].model").value("2101"))
                .andExpect(jsonPath("$.[0].numberplate").value("123ASD"))
                .andExpect(jsonPath("$.[1].id").value(2))
                .andExpect(jsonPath("$.[1].make").value("Kia"))
                .andExpect(jsonPath("$.[1].model").value("Sorento"))
                .andExpect(jsonPath("$.[1].numberplate").value("534TTT"))
                .andExpect(jsonPath("$.[2].id").value(3))
                .andExpect(jsonPath("$.[2].make").value("Skoda"))
                .andExpect(jsonPath("$.[2].model").value("Octavia"))
                .andExpect(jsonPath("$.[2].numberplate").value("999GLF"));
    }

    @Test
    public void testGetCarsSortedByIdDesc() throws Exception {
        this.mockMvc.perform(get("/cars")
                .params(TestUtil.constructHttpParams(0, 3, "id,desc")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].id").value(9))
                .andExpect(jsonPath("$.[0].make").value("BMW"))
                .andExpect(jsonPath("$.[0].model").value("740"))
                .andExpect(jsonPath("$.[0].numberplate").value("112YUI"))
                .andExpect(jsonPath("$.[1].id").value(8))
                .andExpect(jsonPath("$.[1].make").value("Audi"))
                .andExpect(jsonPath("$.[1].model").value("A6"))
                .andExpect(jsonPath("$.[1].numberplate").value("876OUI"))
                .andExpect(jsonPath("$.[2].id").value(7))
                .andExpect(jsonPath("$.[2].make").value("BMW"))
                .andExpect(jsonPath("$.[2].model").value("760"))
                .andExpect(jsonPath("$.[2].numberplate").value("444RRR"));
    }

    @Test
    public void testGetCarsSortedByMakeAsc() throws Exception {
        this.mockMvc.perform(get("/cars")
                .params(TestUtil.constructHttpParams(1, 5, "make,asc")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$.[3].make").value("Skoda"));
    }

    @Test
    public void testGetCarsSortedByMakeDesc() throws Exception {
        this.mockMvc.perform(get("/cars")
                .params(TestUtil.constructHttpParams(0, 5, "make,desc")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$.[0].make").value("Skoda"));
    }

    @Test
    public void testGetCarsSortedByModelAsc() throws Exception {
        this.mockMvc.perform(get("/cars")
                .params(TestUtil.constructHttpParams(0, 10, "model,asc")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(9)))
                .andExpect(jsonPath("$.[8].model").value("Sorento"));
    }

    @Test
    public void testGetCarsSortedByModelDesc() throws Exception {
        this.mockMvc.perform(get("/cars")
                .params(TestUtil.constructHttpParams(0, 10, "model,desc")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(9)))
                .andExpect(jsonPath("$.[8].model").value("2101"));
    }

    @Test
    public void testGetCarsSortedByNumberplateAsc() throws Exception {
        this.mockMvc.perform(get("/cars")
                .params(TestUtil.constructHttpParams(1, 5, "numberplate,asc")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$.[1].numberplate").value("876OUI"));
    }

    @Test
    public void testGetCarsSortedByNumberplateDesc() throws Exception {
        this.mockMvc.perform(get("/cars")
                .params(TestUtil.constructHttpParams(1, 5, "numberplate,desc")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$.[1].numberplate").value("444RRR"));
    }

    @Test
    public void testSearchCarsSuccessUpperCase() throws Exception {
        this.mockMvc.perform(get("/cars")
                .params(TestUtil.constructHttpParams("AU",0, 10, "id,asc")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].id").value(6))
                .andExpect(jsonPath("$.[0].make").value("Audi"))
                .andExpect(jsonPath("$.[0].model").value("A6"))
                .andExpect(jsonPath("$.[0].numberplate").value("997HHH"))
                .andExpect(jsonPath("$.[1].id").value(8))
                .andExpect(jsonPath("$.[1].make").value("Audi"))
                .andExpect(jsonPath("$.[1].model").value("A6"))
                .andExpect(jsonPath("$.[1].numberplate").value("876OUI"));
    }

    @Test
    public void testSearchCarsSuccessLowerCase() throws Exception {
        this.mockMvc.perform(get("/cars")
                .params(TestUtil.constructHttpParams("bm",0, 10, "id,desc")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].id").value(9))
                .andExpect(jsonPath("$.[0].make").value("BMW"))
                .andExpect(jsonPath("$.[0].model").value("740"))
                .andExpect(jsonPath("$.[0].numberplate").value("112YUI"))
                .andExpect(jsonPath("$.[1].id").value(7))
                .andExpect(jsonPath("$.[1].make").value("BMW"))
                .andExpect(jsonPath("$.[1].model").value("760"))
                .andExpect(jsonPath("$.[1].numberplate").value("444RRR"));
    }

    @Test
    public void testSearchCarsNonExistent() throws Exception {
        this.mockMvc.perform(get("/cars")
                .params(TestUtil.constructHttpParams("Mercedes",0, 10, "id,asc")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testSearchCarsEmpty() throws Exception {
        this.mockMvc.perform(get("/cars")
                .params(TestUtil.constructHttpParams("",0, 10, "id,desc")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(9)));
    }

    @Test
    public void testSearchCarsNull() throws Exception {
        this.mockMvc.perform(get("/cars")
                .params(TestUtil.constructHttpParams(null,0, 10, "id,asc")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(9)));
    }

    @Test
    public void testGetCarSuccess() throws Exception {
        this.mockMvc.perform(get("/cars/{id}", 3))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.make").value("Skoda"))
                .andExpect(jsonPath("$.model").value("Octavia"))
                .andExpect(jsonPath("$.numberplate").value("999GLF"));
    }

    @Test
    public void testGetCarFailure() throws Exception {
        this.mockMvc.perform(get("/cars/{id}", 10))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("Entity not found!"));
    }
}
