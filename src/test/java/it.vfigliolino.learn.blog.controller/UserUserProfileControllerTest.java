package it.vfigliolino.learn.blog.controller;

import it.vfigliolino.learn.blog.dto.UserProfileDto;
import it.vfigliolino.learn.blog.service.UserProfileService;
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
public class UserUserProfileControllerTest {
    private static final String ENDPOINT_URL = "/api/profile";
    @InjectMocks
    private UserProfileController userProfileController;
    @Mock
    private UserProfileService userProfileService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userProfileController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<UserProfileDto> page = new PageImpl<>(Collections.singletonList(ProfileBuilder.getDto()));

        Mockito.when(userProfileService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(userProfileService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(userProfileService);

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(userProfileService.findById(ArgumentMatchers.anyLong())).thenReturn(ProfileBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(userProfileService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(userProfileService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(userProfileService.save(ArgumentMatchers.any(UserProfileDto.class))).thenReturn(ProfileBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(ProfileBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(userProfileService, Mockito.times(1)).save(ArgumentMatchers.any(UserProfileDto.class));
        Mockito.verifyNoMoreInteractions(userProfileService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(userProfileService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(ProfileBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(ProfileBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(userProfileService, Mockito.times(1)).update(ArgumentMatchers.any(UserProfileDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(userProfileService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(userProfileService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CustomUtils.asJsonString(ProfileBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(userProfileService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(userProfileService);
    }
}