package com.DeweyDecimate.Omnibus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRootRoute_Status200() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testRootRoute_containsLoginLink() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("<li class=\"nav-item\">\n" +
                        "            <a class=\"nav-link text-light\" href=\"/login\">Log In</a>\n" +
                        "        </li>")));
    }

    @Test
    public void testLoginRoute_Status200() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/login"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testLoginRoute_containsForm() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/login"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("<form method=\"POST\" action=\"/login\">\n" +
                        "                        <ul class=\"list-unstyled\">\n" +
                        "                            <li class=\"m-3\">\n" +
                        "                                <label for=\"username\"> Username: </label><input type=\"text\" name=\"username\" id=\"username\" />\n" +
                        "                            </li>\n" +
                        "                            <li class=\"m-3\">\n" +
                        "                                <label for=\"password\">Password: </label><input type=\"password\" name=\"password\" id=\"password\" />\n" +
                        "                            </li>\n" +
                        "                            <li class=\"m-3\">\n" +
                        "                                <input class=\"btn btn-dark btn-lg\" type=\"submit\" />\n" +
                        "                            </li>\n" +
                        "                        </ul>\n" +
                        "                    </form>")));
    }
    @Test
    public void testSignUpRoute_Status200() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/signup"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testSignUpRoute_containsForm() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/signup"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("<form method=\"POST\" action=\"/users\">")));
    }

    @Test
    public void testError_Status() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/error"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

    @Test
    public void testError_ContainsString() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/error"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("\"status\":999,\"error\":\"None\",\"message\":\"No message available\"")));
    }
}
