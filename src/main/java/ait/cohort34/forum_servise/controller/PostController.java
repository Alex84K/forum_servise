package ait.cohort34.forum_servise.controller;

import ait.cohort34.forum_servise.post.dto.DatePeriodDto;
import ait.cohort34.forum_servise.post.dto.NewCommendDto;
import ait.cohort34.forum_servise.post.dto.NewPostDto;
import ait.cohort34.forum_servise.post.dto.PostDto;
import ait.cohort34.forum_servise.post.servise.PostServise;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor // конструктор для final полей
@RequestMapping("/forum")
public class PostController {

    final PostServise postServise;

    @PostMapping("/post/{author}")
    public PostDto addNewPost(@PathVariable String author, @RequestBody NewPostDto newPostDto) {
        return postServise.addNewPost(author, newPostDto);
    }

    @GetMapping("/post/{id}")
    public PostDto findPostById(@PathVariable String id) {
        return postServise.findPostById(id);
    }

    @DeleteMapping("/post/{id}")
    public PostDto removePost(@PathVariable String id) {
        return postServise.removePost(id);
    }

    @PutMapping("/post/{id}")
    public PostDto updatePost(@PathVariable String id,@RequestBody NewPostDto newPostDto) {
        return postServise.updatePost(id, newPostDto);
    }

    @PutMapping("/post/{id}/comment/{author}")
    public PostDto addComment(@PathVariable String id, @PathVariable String author, @RequestBody NewCommendDto newCommentDto) {
        return postServise.addComment(id, author, newCommentDto);
    }

    @PutMapping("/post/{id}/like")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addLike(@PathVariable String id) {
    postServise.addLike(id);
    }

    @GetMapping("/posts/author/{author}")
    public Iterable<PostDto> findPostByAuthor(@PathVariable String author) {
        return postServise.findPostByAuthor(author);
    }

    @PostMapping("/posts/tags")
    public Iterable<PostDto> findPostByTags(@RequestBody Set<String> tags) {
        return postServise.findPostByTags(tags);
    }

    @PostMapping("/posts/period")
    public Iterable<PostDto> findPostByPeriod(@RequestBody DatePeriodDto datePeriodDto) {
        return postServise.findPostByPeriod(datePeriodDto);
    }
}
