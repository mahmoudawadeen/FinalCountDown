class GuestList < ActiveRecord::Base
  belongs_to :event
  belongs_to :guests , class_name: 'User' , foreign_key: :guest_id

  validates :event , :guests , presence: true 
  

  def self.rsvp(user , event , answer)
    GuestList.create(guest_id: user , event_id: event , status: answer)
  end
end