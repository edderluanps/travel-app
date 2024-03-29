package com.eluanps.travelapp.repository;

import com.eluanps.travelapp.entity.Post;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT p FROM Post p ORDER BY p.dataPostagem")
    List<Post> findPost();

    @Transactional(readOnly = true)
    @Query("SELECT p FROM Post p WHERE p.titulo LIKE %:titulo%")
    List<Post> findPostByKeyword(@Param("titulo") String titulo);

    @Transactional(readOnly = true)
    @Query("SELECT p FROM Post p ORDER BY p.dataPostagem")
    List<Post> findLastThree(Pageable pageable);

}
