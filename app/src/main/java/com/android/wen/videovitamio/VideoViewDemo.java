/*
 * Copyright (C) 2013 yixia.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.wen.videovitamio;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

//这里有个视频链接 需要改的（这个视频就是那个唯一能播放的小视频）
public class VideoViewDemo extends Activity {

    /**
     * TODO: 将路径变量设置为流式视频URL或本地媒体文件
     * path.
     */

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        Vitamio.isInitialized(getApplicationContext());
        setContentView(R.layout.videoview);
        playfunction();

    }


    void playfunction() {

        //String path = "http://gslb.miaopai.com/stream/3D~8BM-7CZqjZscVBEYr5g__.mp4";
        //String path = "mms://202.107.35.51/";
        String path = "http://www.modrails.com/videos/passenger_nginx.mov";
        //http://www.modrails.com/videos/passenger_nginx.mov
        VideoView mVideoView;
        EditText mEditText;
        mEditText = (EditText) findViewById(R.id.url);
        mVideoView = (VideoView) findViewById(R.id.surface_view);
        if (path == "") {
            // Tell the user to provide a media file URL/path.
            Toast.makeText(VideoViewDemo.this, "请编辑VideoViewDemo Activity, 和设置路径" + " 变量到您的媒体文件URL /路径", Toast.LENGTH_LONG).show();
            return;
        } else {
            /*
             * 或者，对于流媒体，您可以使用
			 * mVideoView.setVideoURI(Uri.parse(URLstring));
			 */
            mVideoView.setVideoPath(path);
            mVideoView.setMediaController(new MediaController(this));
            mVideoView.requestFocus();

            mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    // 可选需要Vitamio 4.0
                    mediaPlayer.setPlaybackSpeed(1.0f);
                }
            });
        }
    }

}
