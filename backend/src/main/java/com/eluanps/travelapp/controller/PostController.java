package com.eluanps.travelapp.controller;

import com.eluanps.travelapp.entity.Post;
import com.eluanps.travelapp.service.PostService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @ApiOperation(value = "Busca Posts")
    @GetMapping
    public List<Post> getAll() {
        return postService.getAll();
    }

    @ApiOperation(value = "Pesquisa Post")
    @GetMapping("/resultados-pesquisa")
    public List<Post> getByKeyword(@RequestParam(value = "titulo", defaultValue = "") String titulo) {
        return postService.getByKeyword(titulo);
    }

    @ApiOperation(value = "Busca os ultimos três Posts")
    @GetMapping("/ultimos-posts")
    public List<Post> getLastThree() {
        return postService.getLastThree();
    }

    @ApiOperation(value = "Busca Post por ID")
    @GetMapping("/{id}")
    public Post findById(@PathVariable Long id) {
        return postService.findById(id);
    }

    @ApiOperation(value = "Cadastra Post")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post salvar(@RequestBody @Validated Post post) {
        return postService.salvar(post);
    }

    @ApiOperation(value = "Edita Post")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody Post post) {
        postService.atualizar(id, post);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Não é possível excluir o Post"),
        @ApiResponse(code = 400, message = "Post inexistente.")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }

}
