package com.b0ve.patronobserverpractica.difusores;

import com.b0ve.patronobserverpractica.IMedioDifusion;
import com.b0ve.patronobserverpractica.Noticia;
import com.b0ve.patronobserverpractica.Logger;
import com.github.redouane59.twitter.TwitterClient;
import com.github.redouane59.twitter.dto.tweet.Tweet;
import com.github.redouane59.twitter.signature.TwitterCredentials;

public class Twitter implements IMedioDifusion {

    @Override
    public void actualizar(Noticia noticia) {
        new Thread() {
            public void run() {
                Logger logger = Logger.getLogger();

                TwitterClient twitterClient = new TwitterClient(TwitterCredentials.builder()
                        .accessToken("1491086923-CkO8FfvWoIDdtI6BjVkLvrEIdEHmdtVkbRSP6jd")
                        .accessTokenSecret("RM3A8ChwrEP5QgzVkRBwEZXMfEZGOKZJGQaFLct5ncSzZ")
                        .apiKey("PEnThSicnJwkXc6xH0swsug0h")
                        .apiSecretKey("ba8GDbu5MeGDbetNiThGPNxE3uEKpqJ27CA0Lqp58e6jvpZA9g")
                        .build());
                String text = noticia.getTitulo()+"\n"+noticia.getCuerpo();
                Tweet tweet = twitterClient.postTweet(text);
                String url = "https://twitter.com/" + tweet.getUser().getName() + "/status/" + tweet.getId();
                logger.log("Noticia publicada en Twitter: "+ url);
            }
        }.start();
    }

    @Override
    public String toString() {
        return "Twitter";
    }

}
