package com.DeweyDecimate.Omnibus;

import com.DeweyDecimate.Omnibus.models.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({IntegrationTests.class, ApplicationUserTest.class, BookClubTest.class, MembershipTest.class, ClubDiscussionTest.class})
@SpringBootTest
@AutoConfigureMockMvc
public class OmnibusApplicationTests {
	//Runs All Test Classes
}