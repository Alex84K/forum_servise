package ait.cohort34.forum_servise.post.servise;

import ait.cohort34.forum_servise.post.dao.PostRepository;
import ait.cohort34.forum_servise.post.dto.DatePeriodDto;
import ait.cohort34.forum_servise.post.dto.NewCommendDto;
import ait.cohort34.forum_servise.post.dto.NewPostDto;
import ait.cohort34.forum_servise.post.dto.PostDto;
import ait.cohort34.forum_servise.post.dto.exeptions.PostNotFoundExeption;
import ait.cohort34.forum_servise.post.model.Comment;
import ait.cohort34.forum_servise.post.model.Post;
import com.fasterxml.jackson.databind.util.ArrayIterator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostServiseImpl implements PostServise{

    final PostRepository postRepository;
    final ModelMapper modelMapper;
    @Override
    public PostDto addNewPost(String author, NewPostDto newPostDto) {
        Post post = modelMapper.map(newPostDto, Post.class);
        post.setAuthor(author);
        post = postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto findPostById(String id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundExeption::new);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto removePost(String id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundExeption::new);
        postRepository.delete(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto updatePost(String id, NewPostDto newPostDto) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundExeption::new);
        String content = newPostDto.getContent();
        if(content != null) {
            post.setContent(content);
        }
        String title = newPostDto.getTitle();
        if(title != null) {
            post.setTitle(title);
        }
        Set<String> tags = newPostDto.getTags();
        if(tags != null) {
            tags.forEach(post::addTag);
        }
        post = postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto addComment(String id, String author, NewCommendDto newCommendDto) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundExeption::new);
        Comment comment = new Comment(author, newCommendDto.getMessage());
        post.addComment(comment);
        post = postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public void addLike(String id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundExeption::new);
        post.addLike();
        post = postRepository.save(post);
    }

    @Override
    public Iterable<PostDto> findPostByAuthor(String author) {
        return postRepository.findPostByAuthorIgnoreCase(author)
                .map(p -> modelMapper.map(p, PostDto.class))
                .toList();
    }

    @Override
    public Iterable<PostDto> findPostByTags(Set<String> tags) {
        return postRepository.findByTagsInIgnoreCase(tags)
                .map(p -> modelMapper.map(p, PostDto.class))
                .toList();
    }

    @Override
    public Iterable<PostDto> findPostByPeriod(DatePeriodDto datePeriodDto) {
        return postRepository.findByDateCreatedBetween(datePeriodDto.getDateFrom(), datePeriodDto.getDateTo())
                .map(p -> modelMapper.map(p, PostDto.class))
                .toList();
    }
}
