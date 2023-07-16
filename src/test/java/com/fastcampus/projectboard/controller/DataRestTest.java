package com.fastcampus.projectboard.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled("Spring Data REST 통합테스트는 불필요하므로 제외시킴") // 이 Test Class의 내용들은 실행되지 않도록 한다.
@Transactional
@DisplayName("Data REST - API 테스트")
@AutoConfigureMockMvc
@SpringBootTest
public class DataRestTest {

    private final MockMvc mvc;

    public DataRestTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[api] 게시글 리스트 조회")
    @Test
    public void givenNothing_whenRequestingArticles_thenReturnsArticlesJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(MockMvcRequestBuilders.get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
                //.andDo(print());
    }

    @DisplayName("[api] 게시글 단건 조회")
    @Test
    public void givenNothing_whenRequestingArticle_thenReturnsArticlesJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(MockMvcRequestBuilders.get("/api/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));

    }

    @DisplayName("[api] 게시글 -> 댓글 리스트 조회")
    @Test
    public void givenNothing_whenRequestingArticleCommentsFromArticle_thenReturnsArticleCommentsJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(MockMvcRequestBuilders.get("/api/articles/1/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));

    }

    @DisplayName("[api] 댓글 리스트 조회")
    @Test
    public void givenNothing_whenRequestingArticleComments_thenReturnsArticleCommentsJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(MockMvcRequestBuilders.get("/api/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("[api] 댓글 단건 조회")
    @Test
    public void givenNothing_whenRequestingArticleComment_thenReturnsArticleCommentsJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(MockMvcRequestBuilders.get("/api/articleComments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

}
