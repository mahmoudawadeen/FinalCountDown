Rails.application.routes.draw do
  # resources :tests

  resources :events
  resources :users
  resources :guest_lists
  resources :invitations
  resources :posts
  resource :comments
  get '/events/:id/guestlist' , to: 'events#guest_list' , as:'guests'
  get '/users/:id/followers' , to: 'users#followers' , as:'followers'
  get '/users/:id/following' , to: 'users#following' , as:'following'

  #API
  namespace :api, defaults: { format: :json } do
    resources :events
    resources :users
    resources :sessions

    get '/events/:id/posts' , to: 'events#posts'

  end

  root 'events#index'
end
