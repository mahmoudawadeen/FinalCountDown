class Api::UsersController < Api::BaseController

  def new
    @user = User.new
  end

  def create
    @user = User.new(user_params)
    @user.save
    respond_with @user
  end

  def show
    respond_with  User.find_by(params[:id])
  end

  protected
  def user_params
    params.require(:user).permit(:username , :email , :password , :age , :image_url)
  end
end