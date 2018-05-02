package com.cpu.post;

import java.util.List;

public interface PostService {
    Post findById(long id);
    List<Post> findByDepartment(String department);
    Post save(Post post);
}
