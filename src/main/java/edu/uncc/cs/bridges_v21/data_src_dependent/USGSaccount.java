package bridges.data_src_dependent;
/**
 * 
 * @author mihai mehedint
 *
 */
public class USGSaccount extends DataSource{
		
		/**
		 * Constructor
		 * @param aTwitterAccount
		 */
		public USGSaccount(String aTwitterAccount){
			super();
			super.setLabel(aTwitterAccount);
		}
		
		/**
		 * This method returns the account name
		 */
		public String getName(){
			return this.getLabel();
		}
		
		/**
		 * This method returns the account value
		 */
		public String toString(){
			return this.getName();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((this.getName() == null) ? 0 : this.getName().hashCode());
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
			USGSaccount other = (USGSaccount) obj;
			if (this.getName() == null) {
				if (other.getName() != null)
					return false;
			} else if (!this.getName().equals(other.getName()))
				return false;
			return true;
		}
}
