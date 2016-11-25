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

import java.io.IOException;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.MediaPlayer.OnTimedTextListener;
import io.vov.vitamio.Vitamio;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.graphics.PixelFormat;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

public class MediaPlayerSubtitle extends Activity implements Callback, OnPreparedListener, OnTimedTextListener {

	SurfaceView splayer;
	SurfaceHolder sholder;
	TextView tv;
	private MediaPlayer mediaPlayer;
	private String path = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Vitamio.isInitialized(getApplicationContext());
		setContentView(R.layout.subtitle1);
		tv = (TextView) findViewById(R.id.sub1);
		splayer = (SurfaceView) findViewById(R.id.surface);
		sholder = splayer.getHolder();
		sholder.setFormat(PixelFormat.RGBA_8888);
		sholder.addCallback(this);
	}

	private void playVideo() {
		try {
			if (path == "") {
				// Tell the user to provide an audio file URL.
				Toast.makeText(MediaPlayerSubtitle.this, "请编辑MediaPlayer Activity, " + "并将路径变量设置为您的媒体文件路径。" + " 您的媒体文件必须存储在SD卡上。", Toast.LENGTH_LONG).show();
				return;
			}
			mediaPlayer = new MediaPlayer(this);
			mediaPlayer.setDataSource(path);
			mediaPlayer.setDisplay(sholder);
			mediaPlayer.prepareAsync();
			mediaPlayer.setOnPreparedListener(this);

			mediaPlayer.setOnTimedTextListener(this);

			// TODO 自动生成的catch块
		} catch (IllegalArgumentException e) {
			// TODO 自动生成的catch块
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO 自动生成的catch块
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO 自动生成的catch块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的catch块
			e.printStackTrace();
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO 自动生成方法存根

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO 自动生成方法存根
		playVideo();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO 自动生成方法存根

	}

	private void startVPback() {

		mediaPlayer.start();
	}

	@Override
	public void onPrepared(MediaPlayer arg0) {

		// TODO 自动生成方法存根
		startVPback();
		mediaPlayer.addTimedTextSource(Environment.getExternalStorageDirectory() + "/12.srt");
		mediaPlayer.setTimedTextShown(true);
	}

	@Override
	protected void onPause() {
		// TODO 自动生成方法存根
		super.onPause();
		relaMediaPlay();
	}

	private void relaMediaPlay() {
		// TODO 自动生成方法存根
		if (mediaPlayer != null) {
			mediaPlayer.release();
			mediaPlayer = null;
		}

	}

	@Override
	protected void onDestroy() {
		// TODO 自动生成方法存根
		super.onDestroy();
		relaMediaPlay();

	}

	@Override
	public void onTimedText(String text) {
		// TODO 自动生成方法存根
		tv.setText(text);
	}

	@Override
	public void onTimedTextUpdate(byte[] pixels, int width, int height) {
		// TODO 自动生成方法存根

	}

}
