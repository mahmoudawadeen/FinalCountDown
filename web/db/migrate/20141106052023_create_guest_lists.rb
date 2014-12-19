class CreateGuestLists < ActiveRecord::Migration
  def change
    create_table :guest_lists , :id => false do |t|
      t.references :event , index: true
      t.references :guest , index: true
      t.string :status , null: false , default: 'pending'

      t.timestamps
    end
    execute 'alter table guest_lists add primary key(event_id , guest_id)'
  end
end
