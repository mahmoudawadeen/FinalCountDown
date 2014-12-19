class Api::BaseController < ApplicationController
  #TODO: User authentication
  skip_before_filter  :verify_authenticity_token

  respond_to :json
end