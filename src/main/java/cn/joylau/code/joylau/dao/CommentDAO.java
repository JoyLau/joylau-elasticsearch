package cn.joylau.code.joylau.dao;

import cn.joylau.code.joylau.model.Comment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CommentDAO extends ElasticsearchRepository<Comment,Integer>{
}
