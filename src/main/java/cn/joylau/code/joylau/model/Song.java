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
@Document(indexName = "songdb",type = "song")
public class Song {
    @Id
    private int id;

    private String name;

    private String author;

    private long time;

    private String commentKeyId;

    private String mp3URL;

    /*歌曲封面地址*/
    private String picURL;

    /*歌曲描述*/
    private String describe;

    /*专辑*/
    private String album;

    /*歌词*/
    private String lyric;

    /*mvid*/
    private int mvId;
}
