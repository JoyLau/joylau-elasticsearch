package cn.joylau.code.joylau.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by joylau on 2017/7/17.
 * cn.joylau.code.model
 * 2587038142@qq.com
 */
@Data
@NoArgsConstructor
@Document(indexName = "song",type = "joylau-music")
public class Song {
    @Id
    private int id;

    private String name;

    private String author;

    private long time;

    private int playlistId;

    /*歌曲封面地址*/
    private String picURL;

    /*专辑*/
    private String album;

}
