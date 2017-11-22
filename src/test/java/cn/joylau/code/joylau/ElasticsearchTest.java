package cn.joylau.code.joylau;

import cn.joylau.code.joylau.dao.SongDAO;
import cn.joylau.code.joylau.model.Song;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.index.query.Operator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.springframework.data.domain.PageRequest.of;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JoylauElasticsearchApplication.class,webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ElasticsearchTest {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	private SongDAO songDAO;

	@Test
	public void queryStringQuerySong(){
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryStringQuery("Time")).withPageable(of(0,100)).build();
		System.out.println(searchQuery.getQuery().toString());
		List<Song> songList = songDAO.search(searchQuery).getContent();
		for (Song song : songList) {
			System.out.println(JSONObject.toJSONString(song));
		}
	}

	@Test
	public void queryStringQueryWeightSong(){
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryStringQuery("Time")).withPageable(of(0,100,new Sort(Sort.Direction.DESC,"id"))).build();
		System.out.println(searchQuery.getQuery().toString());
		List<Song> songList = songDAO.search(searchQuery).getContent();
		for (Song song : songList) {
			System.out.println(JSONObject.toJSONString(song));
		}
	}

	@Test
	public void matchQuerySong(){
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery("name","Time")).withPageable(of(0,100)).build();
		System.out.println(searchQuery.getQuery().toString());
		List<Song> songList = songDAO.search(searchQuery).getContent();
		for (Song song : songList) {
			System.out.println(JSONObject.toJSONString(song));
		}
	}

	@Test
	public void phraseMatchSong(){
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchPhraseQuery("name","Time")).withPageable(of(0,100)).build();
		System.out.println(searchQuery.getQuery().toString());
		List<Song> songList = songDAO.search(searchQuery).getContent();
		for (Song song : songList) {
			System.out.println(JSONObject.toJSONString(song));
		}
	}

	@Test
	public void termQuerySong(){
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(termQuery("name","Time")).withPageable(of(0,100)).build();
		System.out.println(searchQuery.getQuery().toString());
		List<Song> songList = songDAO.search(searchQuery).getContent();
		for (Song song : songList) {
			System.out.println(JSONObject.toJSONString(song));
		}
	}

	@Test
	public void multiMatchQuerySong(){
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(multiMatchQuery("time","name","author")).withPageable(of(0,100)).build();
		System.out.println(searchQuery.getQuery().toString());
		List<Song> songList = songDAO.search(searchQuery).getContent();
		for (Song song : songList) {
			System.out.println(JSONObject.toJSONString(song));
		}
	}

	@Test
	public void matchQueryOperatorSong(){
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery("name","time").operator(Operator.AND)).withPageable(of(0,100)).build();
		System.out.println(searchQuery.getQuery().toString());
		List<Song> songList = songDAO.search(searchQuery).getContent();
		for (Song song : songList) {
			System.out.println(JSONObject.toJSONString(song));
		}
	}

	@Test
	public void matchQueryOperatorWithMinimumShouldMatchSong(){
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery("name","time").operator(Operator.AND).minimumShouldMatch("80%")).withPageable(of(0,100)).build();
		System.out.println(searchQuery.getQuery().toString());
		List<Song> songList = songDAO.search(searchQuery).getContent();
		for (Song song : songList) {
			System.out.println(JSONObject.toJSONString(song));
		}
	}

	@Test
	public void boolQuerySong(){
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(boolQuery().must(termQuery("userId", "2345098"))
				.should(rangeQuery("userid").lt(10000)).must(matchQuery("title", "time"))).build();
		System.out.println(searchQuery.getQuery().toString());
		List<Song> songList = songDAO.search(searchQuery).getContent();
		for (Song song : songList) {
			System.out.println(JSONObject.toJSONString(song));
		}
	}
}
