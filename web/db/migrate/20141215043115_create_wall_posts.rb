class CreateWallPosts < ActiveRecord::Migration
  def change
    create_table :wall_posts do |t|
      t.string :content , null: false
      t.references :poster, index: true
      t.references :wall, index: true

      t.timestamps
    end
  end
end
