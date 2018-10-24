package com.cpu.post;

import com.cpu.comments.Comment;
import com.cpu.comments.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository repository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Post save(Post post) {
        if(post.getId()==null){
            post.setDateCreated(System.currentTimeMillis());
        }
        return repository.save(post);
    }

    @Override
    public List<Post> findAll() {
        List<Post> posts=repository.findAll();
        removeUnactiveComments(posts);
        return posts;
    }

    @Override
    public Post findOne(long id) {
        Post post = repository.findById(id).get();
        post.setComments(post.getComments().stream().filter(Comment::isActive).collect(Collectors.toList()));
        return post;
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public Post addComment(Comment comment) {
        Post post = repository.getOne(comment.getArticleId());
        post.getComments().add(commentRepository.save(comment));
        return repository.save(post);
    }

    private void removeUnactiveComments(List<Post> posts){
        posts.forEach(post -> post.setComments(post.getComments().stream().filter(Comment::isActive).collect(Collectors.toList())));
    }
}
