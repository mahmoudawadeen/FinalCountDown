class Event < ActiveRecord::Base

  has_one :poster, class_name: 'User' , through: :posts

  belongs_to :organizer , class_name: 'User' , foreign_key: 'organizer_id'

  has_many :posts , inverse_of: :event
  has_many :comments , through: :posts


  has_many :invitations , inverse_of: :event
  has_many :senders , through: :invitations 
  has_many :receivers , through: :invitations , inverse_of:  :receiver

  has_many :guest_lists , inverse_of: :event
  has_many :guests , through: :guest_lists , inverse_of: :guests


  validates :start_date , date: {before: :end_date}
  validates :start_date , :end_date , date: {after: Proc.new{Date.today} , message: 'event must be after today'} , on: :create


  def show_page
    [Post.find_by_event_id(self),
    Comment.find_by_event_id(self),
    GuestList.find_by_event_id(self)]
  end
end
