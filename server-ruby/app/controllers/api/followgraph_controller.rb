class Api::FollowgraphController < ApplicationController
  
  def followers
    # todo: consider persistent storage, possibly lower level cache
    user_followers = Rails.cache.fetch("twitter:followers:#{params[:id]}") {
      api.followers(params[:id].to_i).take(10)
    }
    render json: {followers: user_followers}
  end
  
  def user
    # todo: consider persistent storage
    user_info = Rails.cache.fetch("twitter:user:#{params[:screen_name]}") {
      api.user(params[:screen_name])
    }
    render json: user_info
  end
  
  protected
  
  def api
    auth = Authentication.find_by(user_id: current_user.id, provider: "twitter")
    if not auth
      redirect_to root_path, notice: "Sign in with Twitter before using followgraph API"
    end
    
    Twitter::REST::Client.new do |config|
      config.consumer_key = ENV["TWITTER_CONSUMER_KEY"]
      config.consumer_secret = ENV["TWITTER_CONSUMER_SECRET"]
      config.access_token = auth.token
      config.access_token_secret = auth.token_secret
    end
  end
end