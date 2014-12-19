class User < ActiveRecord::Base


  has_many :invitations , inverse_of: :receiver
  has_many :senders , through: :invitations 

  has_many :posts 
  has_many :comments 
  has_many :created_events , class_name: 'Event' , inverse_of: :organizer

  has_one :posts_commented_on , class_name: 'Post' , through: :comments , inverse_of: :post

  has_many :guest_lists
  has_many :events , through: :guest_lists

  has_many :following , class_name: 'FollowRelation' ,foreign_key: :follower_id
  has_many :followers , class_name: 'FollowRelation' ,foreign_key: :followee_id

  has_many :wall_posts , foreign_key: :wall_id
  has_many :wall_comments , through: :wall_posts

  has_many :posted_wall_posts , class_name: 'WallPost' , foreign_key: :poster_id

  validates :username , :email , :password , presence: true
  validates :password , confirmation: true , length: {in: 6..15}  
  validates :email , uniqueness: {case_sensitive: false}
  validates :age , numericality: {only_integer: true} , inclusion:{in: 12..90}


  # Class Methods
  def self.authenticate(email, password)
    user = find_by(email: email)
    if user.present? && user.password==password
      user
    else
      User.new.tap do |user|
        user.errors.add :base, 'Invalid email or password'
      end
    end
  end
end