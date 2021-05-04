package com.b0ve.patronobserverpractica.difusores;

import com.b0ve.patronobserverpractica.IMedioDifusion;
import com.b0ve.patronobserverpractica.Noticia;
import com.b0ve.patronobserverpractica.Logger;
import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Blog;
import com.tumblr.jumblr.types.TextPost;
import com.tumblr.jumblr.types.User;
import java.util.logging.Level;

public class Tumblr implements IMedioDifusion {

    @Override
    public void actualizar(Noticia noticia) {
        new Thread() {
            public void run() {
                Logger logger = Logger.getLogger();
                JumblrClient client = new JumblrClient("HmInbPzM6WnkOC7IzHhEnW6r31QiyPGRoEXVoozShu7uyzLeOa", "Z8DGKnRwb19SjjhjKiLhaknRPO7AnBulP3cRTnQHVjRjoighMV");
                client.setToken("uIvbFnR5Y8Kvdc7B42gaJ1shHN4zG7RH9VUioLXkoyFiH1nypr", "AoUoO0pAP85jto0l7oYpEALTuX4R0EmMnDMxB0gR2AsczV5ECs");
                User user = client.user();
                try {
                    Blog blog = user.getBlogs().get(0);
                    TextPost post = client.newPost(blog.getName(), TextPost.class);
                    post.setTitle(noticia.getTitulo());
                    post.setBody(noticia.getCuerpo());
                    post.save();
                    String url = "https://" + blog.getName() + ".tumblr.com";
                    logger.log("Noticia publicada en Tumblr: "+ url);
                } catch (IllegalAccessException | InstantiationException ex) {
                    java.util.logging.Logger.getLogger(Tumblr.class.getName()).log(Level.SEVERE, null, ex);
                    logger.log("Error al publicar noticia en Tumblr");
                }
            }
        }.start();
    }

    @Override
    public String toString() {
        return "Tumblr";
    }

}
