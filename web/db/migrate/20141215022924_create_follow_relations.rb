class CreateFollowRelations < ActiveRecord::Migration
  def change
    create_table :follow_relations, :id => false do |t|
      t.references :follower, index: true
      t.references :followee, index: true
      t.string :status , default: 'pending'

      t.timestamps
    end
    execute 'alter table follow_relations add primary key(follower_id , followee_id)'
  end
end
