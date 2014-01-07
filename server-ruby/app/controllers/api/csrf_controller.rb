class Api::CsrfController < ApplicationController
  
  def generate
    # todo: caching!
    render json: {csrf_token: form_authenticity_token}
  end
  
end