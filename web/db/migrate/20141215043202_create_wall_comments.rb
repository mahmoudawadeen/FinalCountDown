class CreateWallComments < ActiveRecord::Migration
  def change
    create_table :wall_comments do |t|
      t.string :content , null: false
      t.references :commenter, index: true
      t.references :wall_post, index: true

      t.timestamps
    end
  end
end
