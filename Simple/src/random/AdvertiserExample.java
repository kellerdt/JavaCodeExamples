package random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdvertiserExample {

	public static void main(String[] args) {
		List<Advertiser> list = new ArrayList<Advertiser>();
		list.add(new Advertiser(1,2));
		list.add(new Advertiser(1,3));
		list.add(new Advertiser(2,4));
		list.add(new Advertiser(3,4));
		list.add(new Advertiser(5,1));
		System.out.println(new AdvertiserExample().isAnyoneTheirOwnParent(list));
	}
	
	public boolean isAnyoneTheirOwnParent(List<Advertiser> advertisers) {
		HashMap<Integer, NoName> dict = new HashMap<Integer, NoName>();
		
		for(Advertiser advertiser : advertisers) {
			NoName adv = this.getNoName(dict, advertiser.id);
			NoName otherAdv = this.getNoName(dict, advertiser.parentId);
			
			adv.other = otherAdv.get();
			if(adv.other == adv)
				return true;
		}
		return false;
	}
	
	public NoName getNoName(HashMap<Integer, NoName> dict, Integer i) {
		if(!dict.containsKey(i)) {
			dict.put(i, new NoName());
		}
		return dict.get(i);
	}
	
	public static class Advertiser {
		int id;
		int parentId;
		
		public Advertiser(int id, int parentId) {
			this.id = id;
			this.parentId = parentId;
		}
	}
	
	public static class NoName {
		NoName other = null;
		
		public NoName get() {
			if(other == null)
				return this;
			else {
				this.other = this.other.get();
				return this.other;
			}
		}
	}
}
