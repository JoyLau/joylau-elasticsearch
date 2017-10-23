package cn.joylau.code.joylau.controller;

import cn.joylau.code.joylau.dao.SongDao;
import cn.joylau.code.joylau.model.Song;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by JoyLau on 2017/10/20.
 * cn.joylau.code.joylau.controller
 * 2587038142@qq.com
 */
@RestController
@RequestMapping("/songs")
@AllArgsConstructor
public class SongController {
    private SongDao songDao;
    private RestTemplate restTemplate;
    @GetMapping("/putSong")
    public void putSong(){
        String songJson = restTemplate.getForObject("http://localhost:6001/apis/v1/playList/detail/307243535",String.class);
        JSONObject songJSON = JSONObject.parseObject(songJson);
        JSONArray array = songJSON.getJSONObject("result").getJSONArray("tracks");
        for (Object o : array) {
            JSONObject json = JSONObject.parseObject(o.toString());
            Song song = new Song();
            song.setId(json.getIntValue("id"));
            song.setName(json.getString("name"));
            song.setAlbum(json.getJSONObject("album").getString("name"));
            song.setAuthor(((JSONObject)json.getJSONArray("artists").get(0)).getString("name"));
            songDao.save(song);
        }

        Iterable<Song> songList = songDao.findAll();
        for (Song song : songList) {
            System.out.println(song.getName());
        }
    }
}
