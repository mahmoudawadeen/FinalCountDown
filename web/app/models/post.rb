class Post < ActiveRecord::Base
  belongs_to :poster , class_name: 'User' 
  belongs_to :event 

  has_many :comments , inverse_of: :post , dependent: :destroy

  validates :poster , presence: true , on: :create
  validates :content , :event , presence: true  
  validates :content  , length: {maximum:600} 

  def self.post(user , event , post)
    Post.create(poster: user , event: event , content: post)
  end
end
