package com.cpu.post;

import com.cpu.comments.Comment;
import com.cpu.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Post save(Post post) {
        if(post.getId()==null){
            post.setDateCreated(System.currentTimeMillis());
        }
        return repository.save(post);
    }

    @Override
    public List<Post> findAll() {
        return repository.findAll();
    }

    @Override
    public Post findOne(long id) {
        return repository.getOne(id);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public Post addComment(Comment comment) {
        Post post = repository.getOne(comment.getArticleId());
        post.getComments().add(comment);
        return repository.save(post);
    }
}
