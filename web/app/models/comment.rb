class Comment < ActiveRecord::Base
  belongs_to :commenter , class_name: 'User' 
  belongs_to :post

  validates :content , :event , :post  , presence: true
  validates :commenter , presence: true , on: :create 

  def self.comment (user , event , post , content)
    Comment.create(commenter: user , event: event , post: post , content: content)
  end
end

