package cn.joylau.code.joylau;

import cn.joylau.code.joylau.dao.CommentDAO;
import cn.joylau.code.joylau.dao.PlaylistDAO;
import cn.joylau.code.joylau.dao.SongDAO;
import cn.joylau.code.joylau.model.Comment;
import cn.joylau.code.joylau.model.Playlist;
import cn.joylau.code.joylau.model.Song;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JoylauElasticsearchApplication.class,webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class JoylauElasticsearchApplicationTests {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	private PlaylistDAO playlistDAO;

	@Autowired
	private SongDAO songDAO;

	@Autowired
	private CommentDAO commentDAO;
	@Test
	public void createData() {
		String personalizeds = restTemplate.getForObject("http://localhost:3003/apis/v1"+"/personalized",String.class);
		JSONObject perJSON = JSONObject.parseObject(personalizeds);
		JSONArray perArr = perJSON.getJSONArray("result");
		List<Playlist> list = new ArrayList<>();
		List<Integer> playListIds = new ArrayList<>();
		for (Object o : perArr) {
			JSONObject playListJSON = JSONObject.parseObject(o.toString());
			Playlist playlist = new Playlist();
			playlist.setId(playListJSON.getIntValue("id"));
			playListIds.add(playlist.getId());
			playlist.setName(playListJSON.getString("name"));
			playlist.setPicURL(playListJSON.getString("picUrl"));
			playlist.setPlayCount(playListJSON.getIntValue("playCount"));
			playlist.setBookCount(playListJSON.getIntValue("bookCount"));
			playlist.setTrackCount(playListJSON.getIntValue("trackCount"));
			list.add(playlist);
		}
		playlistDAO.saveAll(list);



		/*存储歌曲*/
		List<Integer> songIds = new ArrayList<>();
		List<Song> songList = new ArrayList<>();
		for (Integer playListId : playListIds) {
			String res = restTemplate.getForObject("http://localhost:3003/apis/v1"+"/playlist/detail?id="+playListId,String.class);
			JSONArray songJSONArr = JSONObject.parseObject(res).getJSONObject("playlist").getJSONArray("tracks");
			for (Object o : songJSONArr) {
				JSONObject songJSON = JSONObject.parseObject(o.toString());
				Song song = new Song();
				song.setId(songJSON.getIntValue("id"));
				songIds.add(song.getId());
				song.setName(songJSON.getString("name"));
				song.setAuthor(getSongAuthor(songJSON.getJSONArray("ar")));
				song.setTime(songJSON.getLong("dt"));
				song.setPlaylistId(playListId);
				song.setPicURL(songJSON.getJSONObject("al").getString("picUrl"));
				song.setAlbum(songJSON.getJSONObject("al").getString("name"));
				songList.add(song);
			}
		}
		songDAO.saveAll(songList);



		/*存储评论*/
		List<Comment> commentList = new ArrayList<>();
		for (Integer songId : songIds) {
			String res = restTemplate.getForObject("http://localhost:3003/apis/v1"+"/comment/music?id="+songId+"&offset="+300,String.class);
			JSONArray commentArr = JSONObject.parseObject(res).getJSONArray("comments");
			for (Object o : commentArr) {
				JSONObject commentJSON = JSONObject.parseObject(o.toString());
				Comment comment = new Comment();
				comment.setId(commentJSON.getIntValue("commentId"));
				comment.setSongId(songId);
				comment.setContent(commentJSON.getString("content"));
				comment.setAuthor(commentJSON.getJSONObject("user").getString("nickname"));
				comment.setPicUrl(commentJSON.getJSONObject("user").getString("avatarUrl"));
				comment.setTime(commentJSON.getLong("time"));
				comment.setSupport(commentJSON.getIntValue("likedCount"));
				commentList.add(comment);
			}

		}

		commentDAO.saveAll(commentList);
	}

	/**
	 * 获取歌曲作者名
	 * @param arr arr
	 * @return String
	 */
	private String getSongAuthor(JSONArray arr){
		StringBuilder author = new StringBuilder();
		for (Object o : arr) {
			JSONObject json = JSONObject.parseObject(o.toString());
			author.append(json.getString("name"));
			if (arr.size() > 1){
				author.append(",");
			}
		}
		return author.toString();
	}
}
