class Invitation < ActiveRecord::Base
  belongs_to :sender , class_name: 'User'
  belongs_to :receiver , class_name: 'User'
  belongs_to :event

  validates :receiver , :event , presence: true 
  validates :sender , presence: true , on: :create 
end
