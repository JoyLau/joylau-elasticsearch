package cn.joylau.code.joylau.dao;

import cn.joylau.code.joylau.model.Song;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SongDAO extends ElasticsearchRepository<Song,Integer>{
}
