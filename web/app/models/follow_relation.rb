class FollowRelation < ActiveRecord::Base
  belongs_to :follower , class: 'User' , foreign_key: :follower_id
  belongs_to :followee ,  class: 'User' , foreign_key: :followee_id
end
