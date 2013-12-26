class AddAuthTokens < ActiveRecord::Migration
  def change
    create_table(:authentications) do |t|
      ## Auth Tokens necessary for logins with Facebook, Twitter, etc
      t.string :user_id,            :null => false, :default => ""
      t.string :provider,           :null => false, :default => ""

      # user_id is a foreign key with the User table
      # uid is whatever their handle is on the social net site
      t.string   :uid
      t.string   :token
      t.string   :token_secret

      t.timestamps
    end

    add_index :authentications, :user_id
  end
end
