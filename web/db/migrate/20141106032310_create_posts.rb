class CreatePosts < ActiveRecord::Migration
  def change
    create_table :posts do |t|
      t.string :content , null: false
      t.references :poster , index: true , null: false
      t.references :event, index: true ,null: false

      t.timestamps
    end
  end
end
