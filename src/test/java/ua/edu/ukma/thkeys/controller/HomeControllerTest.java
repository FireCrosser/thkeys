/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.ukma.thkeys.controller;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.servlet.View;

//@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {
    
    @InjectMocks
    private TeacherController hc;
    
    @Mock
    View mockView;
    
//    MockMvc mockMvc;
    
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders
//                .standaloneSetup(hc)
//                .setSingleView(mockView).build();
//    }
    
//    @Test
//    public void testGettingRoot() throws Exception {
//        this.mockMvc.perform(get("/home"))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
}
