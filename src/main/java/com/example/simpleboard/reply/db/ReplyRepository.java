package com.example.simpleboard.reply.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity,Long> {

    //select * from reply where post_id=? and status= ? order by id desc
    List<ReplyEntity> findAllByPostIdAndStatusOrderByIdDesc(Long postId, String status);

}
