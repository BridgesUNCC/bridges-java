class ApplicationController < ActionController::Base
  # Prevent CSRF attacks by raising an exception.
  # For APIs, you may want to use :null_session instead.
  protect_from_forgery with: :exception
  
  def authenticate_api
    if current_user.nil?
      authenticate_or_request_with_http_basic do |email, password|
        user = User.find_by(email: email)
        not user.nil? and user.valid_password?(password)
      end
      warden.custom_failure! if performed?
    end
  end
end
