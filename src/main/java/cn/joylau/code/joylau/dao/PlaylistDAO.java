package cn.joylau.code.joylau.dao;

import cn.joylau.code.joylau.model.Playlist;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PlaylistDAO extends ElasticsearchRepository<Playlist,Integer>{
}
