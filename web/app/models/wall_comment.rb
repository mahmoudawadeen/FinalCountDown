class WallComment < ActiveRecord::Base
  belongs_to :commenter , class_name: 'User' , foreign_key: 'commenter_id'
  belongs_to :wall_post , foreign_key: 'wall_post_id'
end
