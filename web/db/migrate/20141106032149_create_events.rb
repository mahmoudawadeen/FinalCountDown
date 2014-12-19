class CreateEvents < ActiveRecord::Migration
  def change
    create_table :events do |t|
      t.string :title , null: false
      t.datetime :start_date , null: false
      t.datetime :end_date , null: false
      t.string :category
      t.string :location , null: false
      t.boolean :updated ,default: 0
      t.string :image_url
      t.references :organizer , index: true , null: false

      t.timestamps
    end
  end
end
