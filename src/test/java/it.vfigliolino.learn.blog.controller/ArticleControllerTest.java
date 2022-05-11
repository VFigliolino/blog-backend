package it.vfigliolino.learn.blog.controller;

import it.vfigliolino.learn.blog.dto.ArticleDto;
import it.vfigliolino.learn.blog.service.ArticleService;
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
public class ArticleControllerTest {
    private static final String ENDPOINT_URL = "/api/article";
    @InjectMocks
    private ArticleController articleController;
    @Mock
    private ArticleService articleService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(articleController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<ArticleDto> page = new PageImpl<>(Collections.singletonList(ArticleBuilder.getDto()));

        Mockito.when(articleService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(articleService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(articleService);

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(articleService.findById(ArgumentMatchers.anyLong())).thenReturn(ArticleBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(articleService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(articleService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(articleService.save(ArgumentMatchers.any(ArticleDto.class))).thenReturn(ArticleBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(ArticleBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(articleService, Mockito.times(1)).save(ArgumentMatchers.any(ArticleDto.class));
        Mockito.verifyNoMoreInteractions(articleService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(articleService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(ArticleBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(ArticleBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(articleService, Mockito.times(1)).update(ArgumentMatchers.any(ArticleDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(articleService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(articleService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CustomUtils.asJsonString(ArticleBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(articleService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(articleService);
    }
}