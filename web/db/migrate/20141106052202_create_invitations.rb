class CreateInvitations < ActiveRecord::Migration
  def change
    create_table :invitations , :id => false do |t|
      t.references :sender, index: true
      t.references :receiver, index: true
      t.references :event, index: true

      t.timestamps
    end
    execute 'alter table invitations add primary key(sender_id , receiver_id , event_id)'
  end
end
