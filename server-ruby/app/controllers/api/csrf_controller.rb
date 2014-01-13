class Api::CsrfController < ApplicationController
  
  def generate
    render json: {csrf_token: form_authenticity_token,
                  user_signed_in: user_signed_in? }
  end
  
end