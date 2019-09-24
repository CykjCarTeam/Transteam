package cn.bus.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PageUtil {

	public static int totalPage(int total)//计算总页数
	{
		int totalPage=0;
		if (total%3==0) {
			totalPage=total/3;
		}else {
			totalPage=total/3+1;
		}
		if (totalPage==0) {
			totalPage=1;
		}
		return totalPage;
	}
	//生成现在年份，计算现在减去另一个输入的年份得出 到现在过了多少年
	public static String getYear(String date){
		Date date1=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy");
		String now=format.format(date1);
		int yearInt=Integer.parseInt(now)-Integer.parseInt(date);
		String yearString=String.valueOf(yearInt)+"年";
		return yearString;
	}
}
