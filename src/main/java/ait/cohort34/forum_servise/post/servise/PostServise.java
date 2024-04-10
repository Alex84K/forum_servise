package ait.cohort34.forum_servise.post.servise;

import ait.cohort34.forum_servise.post.dto.DatePeriodDto;
import ait.cohort34.forum_servise.post.dto.NewCommendDto;
import ait.cohort34.forum_servise.post.dto.NewPostDto;
import ait.cohort34.forum_servise.post.dto.PostDto;
import java.util.Set;

public interface PostServise {
    PostDto addNewPost(String author, NewPostDto newPostDto);
    PostDto findPostById(String id);
    PostDto removePost(String id);
    PostDto updatePost(String id, NewPostDto newPostDto);

    PostDto addComment(String id, String author, NewCommendDto newCommendDto);
    void addLike(String postId);
    Iterable<PostDto> findPostByAuthor(String author);
    Iterable<PostDto> findPostByTags(Set<String> tags);
    Iterable<PostDto> findPostByPeriod(DatePeriodDto datePeriodDto);
}
