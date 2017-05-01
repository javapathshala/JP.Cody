/*
 * Copyright (c) Java Pathshala.
 * All rights reserved.
 *
 * This program is protected by copyright law but you are authorise to learn
 * & gain ideas from it. Its unauthorised use is explicitly prohibited &
 * any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction.
 * www.javapathshala.com
 */
package com.jp.app.facebook;

import facebook4j.Comment;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Notification;
import facebook4j.PagableList;
import facebook4j.Post;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;

/**
 *
 *
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class FaceBookApp
{

    private static Facebook facebook;

    public static void main(String[] args)
    {
        try
        {
            FaceBookApp faceBookApp = new FaceBookApp();

            // Make the configuration builder 
            ConfigurationBuilder confBuilder = new ConfigurationBuilder();
            confBuilder.setDebugEnabled(true);

            // Set application id, secret key and access token 
            confBuilder.setOAuthAppId("1903536829922395"); // Get from developers.facebook.com by just creating a new app
            confBuilder.setOAuthAppSecret("364c030c610a516eaae64a9193ac76d9"); // Get from developers.facebook.com by just creating a new app 
            confBuilder.setOAuthAccessToken("1903536829922395|I1lJv4YoA8_smByn7wFQtLVBQic"); // Get from developers.facebook.com by just creating a new app

            // Set permission 
            confBuilder.setOAuthPermissions("pages_messaging,manage_notifications,email,publish_stream, id, name, first_name, last_name, generic,publish_actions,manage_pages,read_stream");
            confBuilder.setUseSSL(true);
            confBuilder.setJSONStoreEnabled(true);

            // Create configuration object  
            Configuration configuration = confBuilder.build();

            // Create facebook instance  
            FacebookFactory ff = new FacebookFactory(configuration);
            facebook = ff.getInstance();

            faceBookApp.getLatestPost();
            //faceBookApp.postNewPost();
            faceBookApp.getLatestPost();
        }
        catch (FacebookException fe)
        {
            fe.printStackTrace();
        }
    }

    private void getLatestPost() throws FacebookException
    {
        // Get posts for a particular search 
        ResponseList<Post> results = facebook.getFeed("pathshalajava");
        Post post = results.get(0);

        // Get more stuff...
//        PagableList<Comment> comments = post.getComments();
        System.out.println(post.getCreatedTime().toString());
        System.out.println(post.getName());
        System.out.println(post.getId());
//        System.out.println(comments.get(0));
        System.out.println(post.getMessage());
        System.out.println(post.getSource());
System.out.println(post.getCaption());
//        ResponseList<Notification> notifications = facebook.getNotifications();
//        Notification notification = notifications.get(0);
//        System.out.println(notification.getCreatedTime());
//        System.out.println(notification.getFrom().getName());
//        System.out.println(notification.getTitle());
    }

    private void postNewPost() throws FacebookException
    {
        facebook.postStatusMessage("I am done with Testing");
    }
}
