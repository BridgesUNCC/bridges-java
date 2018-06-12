package bridges.data_src_dependent;


public class UsgsFoo {
	public Geometry geometry;
	public Properties properties;
	public class Geometry{
		public String type;
		public Coordinates coordinates;
		public class Coordinates{
				public String longitude;
				public String latitude; 
				public String depth;
			}
		}
	
	public class Properties{
		public String mag;
		public String place;
		public String time;
		public String title;
		public String url;
		public String getMag() {
			return mag;
		}
		public void setMag(String mag) {
			this.mag = mag;
		}
		public String getPlace() {
			return place;
		}
		public void setPlace(String place) {
			this.place = place;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		
		public String toString(){
			
			StringBuilder s = new StringBuilder();
			s.append("Title: " + this.title)
			.append(" Magnitude: " + this.mag)
			.append(" Time: " + this.time)
			.append(" Place: " + this.place)
			.append(" URL: " + this.url)
			;
			return s.toString();
		}
	}

}
