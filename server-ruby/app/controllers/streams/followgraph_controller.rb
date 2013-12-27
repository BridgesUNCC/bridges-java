class Streams::FollowgraphController < ApplicationController
  before_filter :authenticate_user!
  
  def followers
    auth = Authentication.find_by(user_id: current_user.id, provider: "twitter")
    if not auth
      redirect_to root_path, notice: "Sign in with Twitter before using followgraph API"
    end
    
    twit_api = Twitter::REST::Client.new do |config|
      config.consumer_key = ENV["TWITTER_CONSUMER_KEY"]
      config.consumer_secret = ENV["TWITTER_CONSUMER_SECRET"]
      config.access_token = auth.token
      config.access_token_secret = auth.token_secret
    end
    # todo: caching!
    render json: twit_api.followers.take(10)
  end
end