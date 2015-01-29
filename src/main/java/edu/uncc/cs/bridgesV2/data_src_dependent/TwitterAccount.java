package edu.uncc.cs.bridges_v2.data_src_dependent;
/**
 * 
 * @author mihai mehedint
 *
 */
public class TwitterAccount implements Comparable<TwitterAccount> {
		protected String aTwitterAccount;
		
		public TwitterAccount(String aTwitterAccount){
			this.aTwitterAccount=aTwitterAccount;
		}
		
		/**
		 * This method returns the account name
		 */
		public String getName(){
			return aTwitterAccount;
		}
		
		/**
		 * This method implements compareTo for the Follower
		 */
		public int compareTo(TwitterAccount anotherAccount){
			return aTwitterAccount.compareTo(anotherAccount.getName());
		}
		
		
		/**
		 * This method returns the account value
		 */
		public String toString(){
			return aTwitterAccount;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((aTwitterAccount == null) ? 0 : aTwitterAccount.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TwitterAccount other = (TwitterAccount) obj;
			if (aTwitterAccount == null) {
				if (other.aTwitterAccount != null)
					return false;
			} else if (!aTwitterAccount.equals(other.aTwitterAccount))
				return false;
			return true;
		}

}
