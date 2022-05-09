package it.vfigliolino.learn.blog.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import it.vfigliolino.learn.blog.controller.CommentController;
import it.vfigliolino.learn.blog.controller.CustomUtils;
import it.vfigliolino.learn.blog.domain.Comment;
import it.vfigliolino.learn.blog.dto.CommentDto;
import it.vfigliolino.learn.blog.mapper.CommentMapper;
import it.vfigliolino.learn.blog.mapper.EntityMapper;
import it.vfigliolino.learn.blog.service.CommentService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

@Transactional
public class CommentControllerTest {
    private static final String ENDPOINT_URL = "/api/comment";
    @InjectMocks
    private CommentController commentController;
    @Mock
    private CommentService commentService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(commentController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<CommentDto> page = new PageImpl<>(Collections.singletonList(CommentBuilder.getDto()));

        Mockito.when(commentService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(commentService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(commentService);

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(commentService.findById(ArgumentMatchers.anyLong())).thenReturn(CommentBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(commentService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(commentService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(commentService.save(ArgumentMatchers.any(CommentDto.class))).thenReturn(CommentBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(CommentBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(commentService, Mockito.times(1)).save(ArgumentMatchers.any(CommentDto.class));
        Mockito.verifyNoMoreInteractions(commentService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(commentService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(CommentBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(CommentBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(commentService, Mockito.times(1)).update(ArgumentMatchers.any(CommentDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(commentService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(commentService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CustomUtils.asJsonString(CommentBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(commentService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(commentService);
    }
}