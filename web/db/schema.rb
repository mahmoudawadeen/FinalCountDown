# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20141106054406) do

  create_table "comments", force: true do |t|
    t.string   "content",      null: false
    t.integer  "commenter_id", null: false
    t.integer  "event_id",     null: false
    t.integer  "post_id",      null: false
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "comments", ["commenter_id"], name: "index_comments_on_commenter_id", using: :btree
  add_index "comments", ["event_id"], name: "index_comments_on_event_id", using: :btree
  add_index "comments", ["post_id"], name: "index_comments_on_post_id", using: :btree

  create_table "events", force: true do |t|
    t.string   "title",                        null: false
    t.datetime "start_date",                   null: false
    t.datetime "end_date",                     null: false
    t.string   "category"
    t.string   "location",                     null: false
    t.boolean  "updated",      default: false
    t.string   "image_url"
    t.integer  "organizer_id",                 null: false
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "events", ["organizer_id"], name: "index_events_on_organizer_id", using: :btree

  create_table "guest_lists", id: false, force: true do |t|
    t.integer  "event_id",   default: 0, null: false
    t.integer  "guest_id",   default: 0, null: false
    t.string   "status",                 null: false
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "guest_lists", ["event_id"], name: "index_guest_lists_on_event_id", using: :btree
  add_index "guest_lists", ["guest_id"], name: "index_guest_lists_on_guest_id", using: :btree

  create_table "invitations", id: false, force: true do |t|
    t.integer  "sender_id",   default: 0, null: false
    t.integer  "receiver_id", default: 0, null: false
    t.integer  "event_id",    default: 0, null: false
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "invitations", ["event_id"], name: "index_invitations_on_event_id", using: :btree
  add_index "invitations", ["receiver_id"], name: "index_invitations_on_receiver_id", using: :btree
  add_index "invitations", ["sender_id"], name: "index_invitations_on_sender_id", using: :btree

  create_table "posts", force: true do |t|
    t.string   "content",    null: false
    t.integer  "poster_id",  null: false
    t.integer  "event_id",   null: false
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "posts", ["event_id"], name: "index_posts_on_event_id", using: :btree
  add_index "posts", ["poster_id"], name: "index_posts_on_poster_id", using: :btree

  create_table "users", force: true do |t|
    t.string   "username",   null: false
    t.string   "email",      null: false
    t.string   "password",   null: false
    t.integer  "age"
    t.string   "image_url"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_foreign_key "comments", "events", name: "comments_event_id_fk"
  add_foreign_key "comments", "posts", name: "comments_post_id_fk"
  add_foreign_key "comments", "users", name: "comments_commenter_id_fk", column: "commenter_id"

  add_foreign_key "events", "users", name: "events_organizer_id_fk", column: "organizer_id"

  add_foreign_key "guest_lists", "events", name: "guest_lists_event_id_fk"
  add_foreign_key "guest_lists", "users", name: "guest_lists_guests_id_fk", column: "guest_id"

  add_foreign_key "invitations", "events", name: "invitations_event_id_fk"
  add_foreign_key "invitations", "users", name: "invitations_receiver_id_fk", column: "receiver_id"
  add_foreign_key "invitations", "users", name: "invitations_sender_id_fk", column: "sender_id"

  add_foreign_key "posts", "events", name: "posts_event_id_fk"
  add_foreign_key "posts", "users", name: "posts_poster_id_fk", column: "poster_id"

end
