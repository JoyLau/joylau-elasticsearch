package cn.joylau.code.joylau.dao;

import cn.joylau.code.joylau.model.Song;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by JoyLau on 2017/10/20.
 * cn.joylau.code.joylau.dao
 * 2587038142@qq.com
 */
public interface SongDao extends ElasticsearchRepository<Song,Integer> {
}
