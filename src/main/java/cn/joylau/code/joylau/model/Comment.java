package cn.joylau.code.joylau.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by joylau on 2017/7/17.
 * cn.joylau.code.model
 * 2587038142@qq.com
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "comment",type = "joylau-music")
public class Comment {
    private int id;

    private int songId;

    private String content;

    private String author;

    /*评论者头像*/
    private String picUrl;

    private long time;

    private int support;
}
