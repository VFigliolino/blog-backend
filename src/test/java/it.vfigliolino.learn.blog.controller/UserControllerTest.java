package it.vfigliolino.learn.blog.controller;

import it.vfigliolino.learn.blog.dto.UserDto;
import it.vfigliolino.learn.blog.service.UserService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Transactional
public class UserControllerTest {
    private static final String ENDPOINT_URL = "/api/user";
    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<UserDto> page = new PageImpl<>(Collections.singletonList(it.vfigliolino.learn.blog.controller.UserBuilder.getDto()));

        Mockito.when(userService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(userService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(userService);

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(userService.findById(ArgumentMatchers.anyLong())).thenReturn(it.vfigliolino.learn.blog.controller.UserBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(userService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(userService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(userService.save(ArgumentMatchers.any(UserDto.class))).thenReturn(it.vfigliolino.learn.blog.controller.UserBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(it.vfigliolino.learn.blog.controller.UserBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(userService, Mockito.times(1)).save(ArgumentMatchers.any(UserDto.class));
        Mockito.verifyNoMoreInteractions(userService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(userService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(it.vfigliolino.learn.blog.controller.UserBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(it.vfigliolino.learn.blog.controller.UserBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(userService, Mockito.times(1)).update(ArgumentMatchers.any(UserDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(userService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(userService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CustomUtils.asJsonString(it.vfigliolino.learn.blog.controller.UserBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(userService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(userService);
    }
}