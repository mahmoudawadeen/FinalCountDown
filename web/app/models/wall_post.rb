class WallPost < ActiveRecord::Base
  belongs_to :poster , class_name: 'User' , foreign_key: 'poster_id'
  belongs_to :wall , class_name: 'User' , foreign_key: 'wall_id'

  has_many :wall_comments , inverse_of: :wall_post , dependent: :destroy
end
