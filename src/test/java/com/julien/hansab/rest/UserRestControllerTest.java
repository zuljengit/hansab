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
public class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllUsersSortedByIdAsc() throws Exception {
        this.mockMvc.perform(get("/users")
                .params(TestUtil.constructHttpParams(0, 5, "id,asc")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].name").value("Teet Järveküla"))
                .andExpect(jsonPath("$.[1].id").value(2))
                .andExpect(jsonPath("$.[1].name").value("Pille Purk"))
                .andExpect(jsonPath("$.[2].id").value(3))
                .andExpect(jsonPath("$.[2].name").value("Mati Kaal"))
                .andExpect(jsonPath("$.[3].id").value(4))
                .andExpect(jsonPath("$.[3].name").value("Külli Kukk"))
                .andExpect(jsonPath("$.[4].id").value(5))
                .andExpect(jsonPath("$.[4].name").value("Teet Kruus"));
    }

    @Test
    public void testGetAllUsersSortedByIdDesc() throws Exception {
        this.mockMvc.perform(get("/users")
                .params(TestUtil.constructHttpParams(0, 5, "id,desc")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$.[0].id").value(5))
                .andExpect(jsonPath("$.[0].name").value("Teet Kruus"))
                .andExpect(jsonPath("$.[1].id").value(4))
                .andExpect(jsonPath("$.[1].name").value("Külli Kukk"))
                .andExpect(jsonPath("$.[2].id").value(3))
                .andExpect(jsonPath("$.[2].name").value("Mati Kaal"))
                .andExpect(jsonPath("$.[3].id").value(2))
                .andExpect(jsonPath("$.[3].name").value("Pille Purk"))
                .andExpect(jsonPath("$.[4].id").value(1))
                .andExpect(jsonPath("$.[4].name").value("Teet Järveküla"));
    }

    @Test
    public void testGetSomeUsersSortedByIdAsc() throws Exception {
        this.mockMvc.perform(get("/users")
                .params(TestUtil.constructHttpParams(1, 2, "id,asc")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].id").value(3))
                .andExpect(jsonPath("$.[0].name").value("Mati Kaal"))
                .andExpect(jsonPath("$.[1].id").value(4))
                .andExpect(jsonPath("$.[1].name").value("Külli Kukk"));
    }

    @Test
    public void testGetSomeUsersSortedByIdDesc() throws Exception {
        this.mockMvc.perform(get("/users")
                .params(TestUtil.constructHttpParams(2, 2, "id,desc")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].name").value("Teet Järveküla"));
    }

    @Test
    public void testGetSomeUsersSortedByNameAsc() throws Exception {
        this.mockMvc.perform(get("/users")
                .params(TestUtil.constructHttpParams(0, 2, "name,asc")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].id").value(4))
                .andExpect(jsonPath("$.[0].name").value("Külli Kukk"))
                .andExpect(jsonPath("$.[1].id").value(3))
                .andExpect(jsonPath("$.[1].name").value("Mati Kaal"));
    }

    @Test
    public void testGetSomeUsersSortedByNameDesc() throws Exception {
        this.mockMvc.perform(get("/users")
                .params(TestUtil.constructHttpParams(1, 2, "name,desc")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].id").value(2))
                .andExpect(jsonPath("$.[0].name").value("Pille Purk"))
                .andExpect(jsonPath("$.[1].id").value(3))
                .andExpect(jsonPath("$.[1].name").value("Mati Kaal"));
    }

    @Test
    public void testSearchUsersSuccessUpperCase() throws Exception {
        this.mockMvc.perform(get("/users")
                .params(TestUtil.constructHttpParams("TEET",0, 5, "id,asc")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].name").value("Teet Järveküla"))
                .andExpect(jsonPath("$.[1].id").value(5))
                .andExpect(jsonPath("$.[1].name").value("Teet Kruus"));
    }

    @Test
    public void testSearchUsersSuccessLowerCase() throws Exception {
        this.mockMvc.perform(get("/users")
                .params(TestUtil.constructHttpParams("teet",0, 5, "id,desc")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].id").value(5))
                .andExpect(jsonPath("$.[0].name").value("Teet Kruus"))
                .andExpect(jsonPath("$.[1].id").value(1))
                .andExpect(jsonPath("$.[1].name").value("Teet Järveküla"));
    }

    @Test
    public void testSearchUsersNonExistent() throws Exception {
        this.mockMvc.perform(get("/users")
                .params(TestUtil.constructHttpParams("zxc",0, 5, "id,asc")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testSearchUsersEmpty() throws Exception {
        this.mockMvc.perform(get("/users")
                .params(TestUtil.constructHttpParams("",0, 5, "id,desc")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    public void testSearchUsersNull() throws Exception {
        this.mockMvc.perform(get("/users")
                .params(TestUtil.constructHttpParams(null,0, 5, "id,asc")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    public void testGetUserSuccess() throws Exception {
        this.mockMvc.perform(get("/users/{id}", 3))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.name").value("Mati Kaal"));
    }

    @Test
    public void testGetUserFailure() throws Exception {
        this.mockMvc.perform(get("/users/{id}", 6))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("Entity not found!"));
    }

    @Test
    public void testGetUserCarsSuccess() throws Exception {
        this.mockMvc.perform(get("/users/{id}/cars", 2))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[0].id").value(3))
                .andExpect(jsonPath("$.[0].make").value("Skoda"))
                .andExpect(jsonPath("$.[0].model").value("Octavia"))
                .andExpect(jsonPath("$.[0].numberplate").value("999GLF"))
                .andExpect(jsonPath("$.[1].id").value(4))
                .andExpect(jsonPath("$.[1].make").value("Kia"))
                .andExpect(jsonPath("$.[1].model").value("Sorento"))
                .andExpect(jsonPath("$.[1].numberplate").value("555TFF"));
    }

    @Test
    public void testGetUserCarsFailure() throws Exception {
        this.mockMvc.perform(get("/users/{id}/cars", 0))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("Entity not found!"));
    }

}
