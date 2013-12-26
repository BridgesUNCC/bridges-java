class WelcomeController < ApplicationController
  def home
    if current_user
      redirect_to dashboard_path
    end
  end
  
  def dashboard
    if not current_user
      redirect_to root_path
    end
  end
end
