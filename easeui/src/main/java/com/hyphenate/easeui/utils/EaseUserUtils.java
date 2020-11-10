package com.hyphenate.easeui.utils;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.EaseUI.EaseUserProfileProvider;
import com.hyphenate.easeui.domain.EaseUser;

public class EaseUserUtils {
    
    static EaseUserProfileProvider userProvider;
    
    static {
        userProvider = EaseUI.getInstance().getUserProfileProvider();
    }
    
    /**
     * get EaseUser according username
     * @param username
     * @return
     */
    public static EaseUser getUserInfo(String username){
        if(userProvider != null)
            return userProvider.getUser(username);
        
        return null;
    }
    
    /**
     * set user avatar
     * @param username
     */
    public static void setUserAvatar(Context context, String username, ImageView imageView){
    	EaseUser user = getUserInfo(username);
        if(user != null && user.getAvatar() != null){
            try {
                if (!user.getAvatar().isEmpty()){
                    int avatarResId = Integer.parseInt(user.getAvatar());
                    Glide.with(context).load(avatarResId).into(imageView);
                }

            } catch (Exception e) {
                //use default avatar
                Glide.with(context).load(user.getAvatar())
                        .apply(RequestOptions.placeholderOf(R.drawable.ease_default_avatar)
                                .diskCacheStrategy(DiskCacheStrategy.ALL))
                        .into(imageView);
            }
        }else{
            Log.i("环信----------",user.getAvatar()+"+++++++++++");
            //从服务器中获取头像连接来设置
            Glide.with(context).load(R.drawable.ease_default_avatar).into(imageView);
            Glide.with(context).load("http://jjw-personnel.oss-cn-shenzhen.aliyuncs.com/Image/Personnel/20191115/2WZhplWsZaMEZWYZctSQgo.png").into(imageView);
        }
    }
    
    /**
     * set user's nickname
     */
    public static void setUserNick(String username,TextView textView){
        if(textView != null){
        	EaseUser user = getUserInfo(username);
        	if(user != null && user.getNickname() != null){
        		textView.setText(user.getNickname());
        	}else{
        		textView.setText(username);
        	}
        }
    }
    
}
