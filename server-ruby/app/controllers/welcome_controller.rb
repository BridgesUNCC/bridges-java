class WelcomeController < ApplicationController
  def home
    if current_user
      redirect_to authentications_path
    end
  end
end
