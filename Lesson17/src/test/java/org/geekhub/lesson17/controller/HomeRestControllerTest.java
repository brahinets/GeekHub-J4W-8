package org.geekhub.lesson17.controller;

import com.google.gson.Gson;
import org.geekhub.lesson17.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;

public class HomeRestControllerTest {
    private MockMvc mockMvc;
    private Gson gson;

    @BeforeMethod
    public void setUp() {
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(new HomeRestController())
                .setMessageConverters(new GsonHttpMessageConverter())
                .build();
    }

    @Test
    public void listAsJsonReturned_when_callRestEndpoint() throws Exception {
        final String expected = gson.toJson(List.of("str1", "str2", "str3", "qwerty"));
        mockMvc.perform(get("/rest"))
                .andExpect(status().isOk())
                .andExpect(content().json(expected, true));
    }

    @Test
    public void acceptedResponseStatus_when_sendUserViaForm() throws Exception {
        mockMvc.perform(get("/user-from-form"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void formsUserReturned_when_sendUserViaForm() throws Exception {
        final UserDto dto = new UserDto();
        dto.setFirstName("first");
        dto.setAge(25);
        mockMvc.perform(get("/user-from-form")
                .param("firstName", "first")
                .param("age", "25")
        )
                .andExpect(content().json(gson.toJson(dto), true));
    }

    @Test
    public void requestBodyUserReturned_when_sendUserViaBody() throws Exception {
        final UserDto dto = new UserDto();
        dto.setFirstName("first");
        dto.setAge(25);
        mockMvc.perform(post("/user-from-body")
                .content(gson.toJson(dto))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(content().json(gson.toJson(dto), true));
    }

    @Test
    public void dataPutIntoSession_when_useHttpSessionAsMethodParam() throws Exception {
        final MockHttpSession session = new MockHttpSession();
        mockMvc.perform(get("/put-to-session")
                .param("data", "some-data")
                .session(session)
        );

        assertEquals(session.getAttribute("data"), "some-data");
    }

    @Test
    public void dataFromSessionReturned_when_requestDataViaSessionAttribute() throws Exception {
        final MockHttpSession session = new MockHttpSession();
        session.setAttribute("data", "new-data");
        mockMvc.perform(get("/get-from-session")
                .session(session)
        ).andExpect(content().string("\"new-data\""));
    }
}