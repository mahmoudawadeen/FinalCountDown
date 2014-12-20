class Api::EventsController < Api::BaseController

  def index
    respond_with @events = Event.all
  end

  def new
    @event = Event.new
  end

  def create
    @event = Event.create(event_params)
    # @event.save

    respond_with @event

  end

  def update
    @event = Event.find(params[:id])
    @event.update(event_params)

    respond_with @event
  end


  def posts
    arr = Array.new
    arr2 = Array.new
    Event.find_by(params[:id]).posts.each do |post|
      arr.push(post.comments)
      arr2.push(post.poster)
    end
    respond_with [Event.find_by(params[:id]).posts,arr,arr2]
  end

  protected

  def event_params
    params.require(:event).permit(:title , :start_date , :end_date , :category , :image_url ,:location, :organizer_id)
  end
end