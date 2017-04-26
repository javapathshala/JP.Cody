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
package com.jp.app.twitter;

import java.util.Arrays;
import twitter4j.FilterQuery;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;

/**
 *
 * This class demonstrate how you can post a Tweet in Java using the
 * Twitter REST API and an open source third party
 * twitter integration library in java called Twitter4J
 *
 * @author Dimit Chadha
 * @since 1.0
 * @version 1.0
 */
public class TwitterApp
{

    //Your Twitter App's Consumer Key
    private static final String consumerKey = "YmdbbNv9B7IuqzcreukcOhdHQ";

    //Your Twitter App's Consumer Secret
    private static final String consumerSecret = "yR1qsBOgfC1eamv1JoUCfg2XgfGHHygeHgRtTAL3IsfUpOJhun";

    //Your Twitter Access Token
    private static final String accessToken = "536107544-YvpajR5FGohLQrrfAGykNVOLHD45wl6g7O4PTvwR";

    //Your Twitter Access Token Secret
    private static final String accessTokenSecret = "JQySIesgQB6P8htnw8bSVNSYqSOg1W0ruRs1SaZVlJXo4";

    private static Twitter twitter;

    public static void main(String[] args)
    {
        TwitterApp twitterApp = new TwitterApp();

        //Instantiate a re-usable and thread-safe factory
        TwitterFactory twitterFactory = new TwitterFactory();

        //Instantiate a new Twitter instance
        twitter = twitterFactory.getInstance();

        //setup OAuth Consumer Credentials
        twitter.setOAuthConsumer(consumerKey, consumerSecret);

        //setup OAuth Access Token
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));

        try
        {
            //twitterApp.postTweet();
            //twitterApp.grabTweet();
           twitterApp.getTweetListner();

            twitterApp.postReply();
            twitterApp.postDirectTweet();
        }
        catch (TwitterException ex)
        {
            ex.printStackTrace();
        }

    }

    private void postTweet() throws TwitterException
    {
        //post tweet
        //Instantiate and initialize a new twitter status update
        StatusUpdate statusUpdate = new StatusUpdate("Please follow JavaPathshala");
        Status status = twitter.updateStatus(statusUpdate);
        //response from twitter server
        System.out.println("status.toString() = " + status.toString());
        System.out.println("status.getInReplyToScreenName() = " + status.getInReplyToScreenName());
        System.out.println("status.getSource() = " + status.getSource());
        System.out.println("status.getText() = " + status.getText());
        System.out.println("status.getContributors() = " + Arrays.toString(status.getContributors()));
        System.out.println("status.getCreatedAt() = " + status.getCreatedAt());
        System.out.println("status.getCurrentUserRetweetId() = " + status.getCurrentUserRetweetId());
        System.out.println("status.getGeoLocation() = " + status.getGeoLocation());
        System.out.println("status.getId() = " + status.getId());
        System.out.println("status.getInReplyToStatusId() = " + status.getInReplyToStatusId());
        System.out.println("status.getInReplyToUserId() = " + status.getInReplyToUserId());
        System.out.println("status.getPlace() = " + status.getPlace());
        System.out.println("status.getRetweetCount() = " + status.getRetweetCount());
        System.out.println("status.getRetweetedStatus() = " + status.getRetweetedStatus());
        System.out.println("status.getUser() = " + status.getUser());
        System.out.println("status.getAccessLevel() = " + status.getAccessLevel());
        System.out.println("status.getHashtagEntities() = " + Arrays.toString(status.getHashtagEntities()));
        System.out.println("status.getMediaEntities() = " + Arrays.toString(status.getMediaEntities()));
        if (status.getRateLimitStatus() != null)
        {
            System.out.println("status.getRateLimitStatus().getLimit() = " + status.getRateLimitStatus().getLimit());
            System.out.println("status.getRateLimitStatus().getRemaining() = " + status.getRateLimitStatus().getRemaining());
            System.out.println("status.getRateLimitStatus().getResetTimeInSeconds() = " + status.getRateLimitStatus().getResetTimeInSeconds());
            System.out.println("status.getRateLimitStatus().getSecondsUntilReset() = " + status.getRateLimitStatus().getSecondsUntilReset());
            System.out.println("status.getRateLimitStatus().getRemainingHits() = " + status.getRateLimitStatus().getRemaining());
        }
        System.out.println("status.getURLEntities() = " + Arrays.toString(status.getURLEntities()));
        System.out.println("status.getUserMentionEntities() = " + Arrays.toString(status.getUserMentionEntities()));

    }

    private void grabTweet() throws TwitterException
    {
        ResponseList<Status> homeTimeline = twitter.getUserTimeline("");

        for (Status status : homeTimeline)
        {
            System.out.println(status.getUser().getName() + ": " + status.getText());
        }
    }

    private void getTweetListner() throws TwitterException
    {
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        //setup OAuth Consumer Credentials

        twitterStream.setOAuthConsumer(consumerKey, consumerSecret);

        //setup OAuth Access Token
        twitterStream.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));

        StatusListener listener = new StatusListener()
        {

            @Override
            public void onException(Exception e)
            {
                System.out.println("Exception occured:" + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onTrackLimitationNotice(int n)
            {
                System.out.println("Track limitation notice for " + n);
            }

            @Override
            public void onStatus(Status status)
            {
//                System.out.println("Got twit:" + status.getText());
//                           TwitterStreamBean bean = new TwitterStreamBean();
                String username = status.getUser().getScreenName();
                System.out.println("USER NAME - " + username);
//                           bean.setUsername(username);
                long tweetId = status.getId();
                System.out.println("TWEET ID - " + tweetId);
//                           bean.setId(tweetId);
                // bean.setInReplyUserName(status.getInReplyToScreenName());
//                           if (status != null && status.getRetweetedStatus() != null
//                                         && status.getRetweetedStatus().getUser() != null) {
////                                  bean.setRetwitUserName(status.getRetweetedStatus()
//                                                .getUser().getScreenName());
//                           }
                String content = status.getText();
                System.out.println("CONTENT -- " + content);
//                           bean.setContent(content);
            }

            @Override
            public void onStallWarning(StallWarning arg0)
            {
                System.out.println("Stall warning");
            }

            @Override
            public void onScrubGeo(long arg0, long arg1)
            {
                System.out.println("Scrub geo with:" + arg0 + ":" + arg1);
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice arg0)
            {
                System.out.println("Status deletion notice");
            }
        };

//        twitterStream.addListener(listener);
        FilterQuery qry = new FilterQuery();
        String[] keywords =
        {
            "@"
        };
//        
        qry.track(keywords);
//        
        twitterStream.addListener(listener);
//        twitterStream.filter(qry);
        //twitterStream.sample();
    }

    private void postReply()
    {
    }

    private void postDirectTweet()
    {
    }

    void getSearchTweets()
    {
        try
        {
            Query query = new Query("#willsmith");
            query.getSince();
            query.setCount(100);
            QueryResult result = twitter.search(query);
            for (Status status : result.getTweets())
            {
                System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
            }
        }
        catch (TwitterException e)
        {
            System.out.println("Search tweets: " + e);
        }
    }

}
