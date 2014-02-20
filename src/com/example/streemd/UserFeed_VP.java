package com.example.streemd;

import java.util.ArrayList;

import com.example.streemd.Post;
import com.example.streemd.PostListAdapter;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

public class UserFeed_VP extends YouTubeBaseActivity implements OnInitializedListener {
   
   protected ArrayList<Post> m_arrPostList;
   
   protected PostListAdapter m_postAdapter;
   
   protected ListView m_vwPostLayout;
   protected YouTubePlayerView m_vwYouTubeView;
   protected static YouTubePlayer m_youTubePlayer;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      
      this.m_arrPostList = new ArrayList<Post>();
      this.m_postAdapter = new PostListAdapter(this, this.m_arrPostList);
      
      initLayout();
      //TODO Pull in posts from DB
      addPost(new Post("wKJ9KzGQq0w", "Youtube Test Video", "This is a video to be used in YouTube Player API demos."));
      addPost(new Post("d8i-H6MVl18","Introduction to android - android tutorial for bigginers to advanced", "Hi i am posting all the videos that helped me in learning android. now i am having 3 years of mobile experience and any one want to learn android subscribe for my channel and ask any doubts in android.i will explain you.Please subscribe for channel."));
      addPost(new Post("d2ZNaLQD60Y", "Game of Thrones Trailer #2 - Vengeance (HBO)", "Check out the new GOT trailer!! Only 6 more weeks I can't wait!"));
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }
   
   protected void initLayout() {
      this.setContentView(R.layout.user_feed);
      
      this.m_vwPostLayout = (ListView) this.findViewById(R.id.postListView);
      this.m_vwPostLayout.setAdapter(m_postAdapter);
      
      this.m_vwYouTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
      this.m_vwYouTubeView.initialize(DeveloperKey.DEVELOPER_KEY, this);
   }
   
   public void addPost(Post post) {
      this.m_arrPostList.add(post);
      this.m_postAdapter.notifyDataSetChanged();
   }

   @Override
   public void onInitializationFailure(Provider arg0,
      YouTubeInitializationResult arg1) {
      String toastText = "Video Player initialization failed....";
      Toast toast = Toast.makeText(this, toastText, Toast.LENGTH_LONG);
      toast.show();
      
   }

   @Override
   public void onInitializationSuccess(Provider provider, YouTubePlayer player,
      boolean wasRestored) {
      if (!wasRestored) {
         this.m_youTubePlayer = player;
         String toastText = "Video Player initialization suceeded....";
         Toast toast = Toast.makeText(this, toastText, Toast.LENGTH_LONG);
         toast.show();
      }
      
   }

   public static YouTubePlayer getYouTubePlayer() {
      return m_youTubePlayer;
   }

}
