package scan_mine;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeCounter {

	public static void main(String[] args) {

		Screen sc = new Screen();
		
		while(true) {
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
			String strNow = sdf.format(now);
			sc.setT(strNow);
		}
	}

}
