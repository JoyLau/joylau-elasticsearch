package cn.joylau.code.joylau;

import cn.joylau.code.joylau.dao.CommentDAO;
import cn.joylau.code.joylau.dao.PlaylistDAO;
import cn.joylau.code.joylau.dao.SongDAO;
import cn.joylau.code.joylau.model.Comment;
import cn.joylau.code.joylau.model.Playlist;
import cn.joylau.code.joylau.model.Song;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.springframework.data.domain.PageRequest.of;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JoylauElasticsearchApplication.class,webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ElasticsearchTest {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	private SongDAO songDAO;

	@Test
	public void searchSong(){
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery("name","Time")).withPageable(of(0,100)).build();
		System.out.println(searchQuery.getQuery().toString());
		List<Song> songList = songDAO.search(searchQuery).getContent();
		for (Song song : songList) {
			System.out.println(JSONObject.toJSONString(song));
		}
	}

}
