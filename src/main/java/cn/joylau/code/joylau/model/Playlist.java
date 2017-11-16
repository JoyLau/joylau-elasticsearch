package cn.joylau.code.joylau.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by JoyLau on 2017/8/16.
 * cn.joylau.code.model
 * 2587038142@qq.com
 */
@Data
@NoArgsConstructor
@Document(indexName = "playlist",type = "joylau-music")
public class Playlist {
    @Id
    private int id;
    private String name;
    private String picURL;
    private int playCount;
    /*收藏数*/
    private int bookCount;
    /*收藏的歌曲数目*/
    private int trackCount;

    public Playlist(int id, String name, String picURL, int playCount,int bookCount, int trackCount) {
        this.id = id;
        this.name = name;
        this.picURL = picURL;
        this.playCount = playCount;
        this.bookCount = bookCount;
        this.trackCount = trackCount;
    }
}
