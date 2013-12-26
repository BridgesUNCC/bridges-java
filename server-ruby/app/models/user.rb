class User < ActiveRecord::Base
  # Include default devise modules. Others available are:
  # :confirmable, :lockable, :timeoutable
  devise :database_authenticatable, :registerable,
         :recoverable, :rememberable, :trackable, :validatable, :omniauthable,
         omniauth_providers: [:twitter]
  has_many :authentications
  
    
  def apply_omniauth(omni)
    authentications.build(
      provider: omni['provider'],
      uid: omni['uid'],
      token: omni['credentials'].token,
      token_secret: omni['credentials'].secret)
  end
  
  def email_required?
    super and authentications.empty?
  end
  
  def password_required?
    super and authentications.empty?
  end
end
