SUPPORTED_PROVIDERS = Set.new ["twitter"]

class AuthenticationsController < ApplicationController
  def index
    if not current_user
      redirect_to root_path and return
    end
    @authentications = Authentication.where(user_id: current_user.id)
    @missing_providers = SUPPORTED_PROVIDERS - @authentications.map {|a| a.provider}
  end
  
  def home
  end

  def create
    @authentication = Authentication.new(params[:authentication])
    if @authentication.save
      redirect_to authentications_url, :notice => "Successfully created authentication."
    else
      render :action => 'new'
    end
  end

  def destroy
    @authentication = Authentication.find(params[:id])
    @authentication.destroy
    redirect_to authentications_url, :notice => "Successfully destroyed authentication."
  end
  
  def failure
    redirect_to authentications_url, :notice => "Authentication failed."
  end
  
  def twitter
    omni = request.env["omniauth.auth"]
    auth = Authentication.find_by(provider: omni[:provider], uid: omni[:uid])
    if auth
      sign_in_and_redirect User.find(auth.user_id)
    elsif current_user 
      current_user.authentications.create!(
        provider: omni[:provider],
        uid: omni[:uid],
        token: omni[:credentials].token,
        token_secret: omni[:credentials].secret)
      redirect_to dashboard_path
    else
      user = User.new
      user.apply_omniauth(omni)
      if user.save
        flash[:notice] = "Signed in with #{omni[:provider]}"
        sign_in_and_redirect User.find(user.id)
      else
        session[:omniauth] = omni.except('extra')
        flash[:notice] = "Create an account to associate with your twitter login"
        redirect_to new_user_registration_path
      end
    end
  end
  
end
