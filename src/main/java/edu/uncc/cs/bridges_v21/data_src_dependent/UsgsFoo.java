package bridges.data_src_dependent;


public class UsgsFoo {
	public Geometry geometry;
	public Properties properties;
	public class Geometry{
		public String type;
		public Coordinates coordinates;
		public class Coordinates{
				public String latitude;
				public String longitude; 
				public String depth;
			}
		}
	
	public class Properties{
		public String mag;
		public String place;
		public String time;
		public String title;
		public String url;
	}

}
