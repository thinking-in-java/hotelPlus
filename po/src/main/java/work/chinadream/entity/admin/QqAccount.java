package work.chinadream.entity.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QqAccount {
    private  Integer id;
    private String openid;
    private String access_token;
    private String nickname;
    private String photoUrl;
}
