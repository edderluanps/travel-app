package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Post;
import com.eluanps.travelapp.repository.PostRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto não encontrado!"));
    }

    public Post salvar(Post post) {
        return postRepository.save(post);
    }

    public void atualizar(Long id, Post post) {
        postRepository.findById(id).map(obj -> {
            post.setId(obj.getId());
            return postRepository.save(post);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto não encontrado!"));
    }

    public void delete(Long id) {
        postRepository.findById(id).map(obj -> {
            postRepository.delete(obj);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto não encontrado!"));
    }

}
