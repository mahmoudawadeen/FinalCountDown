class AddKeys < ActiveRecord::Migration
  def change
    add_foreign_key "follow_relations", "users", name: "follow_relations_followee_id_fk" , column: "followee_id"
    add_foreign_key "follow_relations", "users", name: "follow_relations_follower_id_fk" , column: "follower_id"
    add_foreign_key "comments", "users", name: "comments_commenter_id_fk", column: "commenter_id"
    add_foreign_key "comments", "posts", name: "comments_post_id_fk"
    add_foreign_key "events", "users", name: "events_organizer_id_fk", column: "organizer_id"
    add_foreign_key "guest_lists", "events", name: "guest_lists_event_id_fk"
    add_foreign_key "guest_lists", "users", name: "guest_lists_guests_id_fk", column: "guest_id"
    add_foreign_key "invitations", "events", name: "invitations_event_id_fk"
    add_foreign_key "invitations", "users", name: "invitations_receiver_id_fk", column: "receiver_id"
    add_foreign_key "invitations", "users", name: "invitations_sender_id_fk", column: "sender_id"
    add_foreign_key "posts", "events", name: "posts_event_id_fk"
    add_foreign_key "posts", "users", name: "posts_poster_id_fk", column: "poster_id"
    add_foreign_key "wall_comments", "users", name: "wall_comments_commenter_id_fk", column: "commenter_id"
    add_foreign_key "wall_comments", "wall_posts", name: "wall_comments_wall_post_id_fk"
    add_foreign_key "wall_posts", "users", name: "wall_posts_poster_id_fk", column: "poster_id"
    add_foreign_key "wall_posts", "users", name: "wall_posts_wall_id_fk", column: "wall_id"
  end
end